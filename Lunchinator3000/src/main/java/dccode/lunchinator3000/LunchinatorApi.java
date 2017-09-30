package dccode.lunchinator3000;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dccode.lunchinator3000.models.Ballot;
import dccode.lunchinator3000.models.ClosedVotingChoice;
import dccode.lunchinator3000.models.ClosedVotingResponse;
import dccode.lunchinator3000.models.ClosedVotingWinner;
import dccode.lunchinator3000.models.CreateBallotRequest;
import dccode.lunchinator3000.models.CreateBallotResponse;
import dccode.lunchinator3000.models.OpenVotingChoice;
import dccode.lunchinator3000.models.OpenVotingResposne;
import dccode.lunchinator3000.models.OpenVotingSuggestion;
import dccode.lunchinator3000.models.Restaurant;
import dccode.lunchinator3000.models.Vote;

/**
 * The API class for Lunchinator 3000. 
 * @author Doug
 *
 */
@Path("")
public class LunchinatorApi {

	private static final String DATE_FORMAT = "M/d/yy h:mm";
	private static final int DEFAULT_HOUR = 11;
	private static final int DEFAULT_MINUTES = 45;
	
	/**
	 * This method creates an API call to start a new ballot. 
	 * @param newBallot 
	 * @return The JSON that is created from the return object contains the ballot id.
	 */
	@POST
	@Path("create-ballot")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBallot(CreateBallotRequest newBallot) {
		// Date is optional and comes in as a String, if a string date was passed convert it or build default date
		Date endTime = null;
		if (newBallot.getEndTime() == null || newBallot.getEndTime().trim().isEmpty()) {
			// Default Date
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, DEFAULT_HOUR);
			cal.set(Calendar.MINUTE, DEFAULT_MINUTES);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			endTime = cal.getTime();
		}
		else {
			// do date conversion
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			try {
				endTime = sdf.parse(newBallot.getEndTime());
				
			} catch (ParseException e) {
				throw new WebApplicationException(Util.buildErrorResponse(Status.BAD_REQUEST, "End Time was not in the correct format."));
			}
		}

		String ballotId = BallotBox.createBallot(endTime, newBallot.getVoters());
		CreateBallotResponse resp = new CreateBallotResponse(ballotId);
		return Response.status(Status.OK).entity(resp).build();
	}
	
	/**
	 * This method displays the open voting information or closed voting results of a ballot.
	 * @param ballotId Ballot id to retrieve.
	 * @return The JSON that is created from the return object contains the open or closed voting response.
	 */
	@GET
	@Path("ballot/{ballotId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBallot(@PathParam("ballotId") String ballotId) {
		Ballot ballot = BallotBox.getBallot(ballotId);
		// If the current time is before the endTime show the suggestion and choices, else the winner and choices
		Date now = new Date();
		Object entity = null;
		if (now.before(ballot.getEndTime())) {
			entity = buildOpenVotingResponse(ballot);
		}
		else {
			entity = buildClosedVotingResponse(ballot);
		}
		return Response.status(Status.OK).entity(entity).build();
	}
	
	/**
	 * This method records the vote of a user. If the voting is closed it sends a 409 status code.
	 * @param id Id of the restaurant voted for.
	 * @param ballotId Id of the ballot.
	 * @param voterName Name of the user who voted.
	 * @param emailAddress Email of the user who voted.
	 * @return
	 */
	@POST
	@Path("vote")
	@Produces(MediaType.APPLICATION_JSON)
	public Response vote(@QueryParam("id") long id, 
						 @QueryParam("ballotId") String ballotId, 
						 @QueryParam("voterName") String voterName, 
						 @QueryParam("emailAddress") String emailAddress) {
		Vote vote = new Vote(id, ballotId, voterName, emailAddress);
		boolean success = BallotBox.recordVote(vote);
		
		if (success) {
			return Response.status(Status.OK).build();
		}
		else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	/**
	 * Helper method to provide a restaurant suggestion and choices for the ballot.
	 * @param ballot Selected ballot.
	 * @return OpenVotingResposne that will be converted to JSON.
	 */
	private OpenVotingResposne buildOpenVotingResponse(Ballot ballot) {
		OpenVotingResposne response = new OpenVotingResposne();
		// Find the suggestion
		int selectedRestaurantPosition = 0;
		int maxAverageReview = 0;
		for (int i = 0; i < ballot.getRestaurants().size(); i++) {
			Restaurant restaurant = ballot.getRestaurants().get(i);
			int aveReview = Integer.parseInt(restaurant.getAverageReview());
			if (aveReview > maxAverageReview) {
				selectedRestaurantPosition = i;
			}
		}
		response.setSuggestion(new OpenVotingSuggestion(ballot.getRestaurants().get(selectedRestaurantPosition)));
		
		// Put in the choices
		for (Restaurant restaurant : ballot.getRestaurants()) {
			response.getChoices().add(new OpenVotingChoice(restaurant));
		}
		
		return response;
	}
	
	/**
	 * Helper method to provide a restaurant winner and choices for the ballot.
	 * @param ballot Selected ballot.
	 * @return OpenVotingResposne that will be converted to JSON.
	 */
	private ClosedVotingResponse buildClosedVotingResponse(Ballot ballot) {
		ClosedVotingResponse response = new ClosedVotingResponse();
		
		// Get the votes
		Map<Long, Integer> votingResults = BallotBox.getVotingResults(ballot.getBallotId());
		for (Restaurant restaurant : ballot.getRestaurants()) {
			if (votingResults.containsKey(restaurant.getId())) {
				int votes = votingResults.get(restaurant.getId());
				restaurant.setVotes(votes);
			}
		}
		
		// Find the winner, first highest vote getter
		int winningRestaurantPosition = 0;
		int maxVotes = 0;
		for (int i = 0; i < ballot.getRestaurants().size(); i++) {
			Restaurant restaurant = ballot.getRestaurants().get(i);
			int votes = restaurant.getVotes();
			if (votes > maxVotes) {
				winningRestaurantPosition = i;
			}
		}
		// Assumed that the date in winner is the current time
		response.setWinner(new ClosedVotingWinner(ballot.getRestaurants().get(winningRestaurantPosition), new Date()));
		
		
		// Put in the choices
		for (Restaurant restaurant : ballot.getRestaurants()) {
			response.getChoices().add(new ClosedVotingChoice(restaurant));
		}
		
		return response;
	}
}
