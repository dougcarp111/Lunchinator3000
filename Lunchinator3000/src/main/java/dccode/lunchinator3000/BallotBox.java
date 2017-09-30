package dccode.lunchinator3000;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import dccode.lunchinator3000.models.Ballot;
import dccode.lunchinator3000.models.Restaurant;
import dccode.lunchinator3000.models.Review;
import dccode.lunchinator3000.models.Vote;
import dccode.lunchinator3000.models.Voter;

/**
 * This class is the Data Access Object for Ballots.
 * @author Doug
 *
 */
public class BallotBox {

	/**
	 * This field represents the data store for the application.  This should be linked to a database.
	 */
	private static Map<String, Ballot> ballots = new HashMap<String, Ballot>();
	private static Map<String, Vote> recordedVotes = new HashMap<String, Vote>();
	private static final int RESTAURANT_COUNT = 5; 
	
	/**
	 * This method creates the ballot and pulls in all the required restaurant data.
	 * @param endTime When voting ends.
	 * @param voters Who will be voting.
	 * @return The ballot id in the form of a GUID.
	 */
	public static String createBallot(Date endTime, List<Voter> voters) {
		Ballot ballot = new Ballot();
		ballot.setBallotId(java.util.UUID.randomUUID().toString());
		ballot.setEndTime(endTime);
		ballot.setVoters(voters);
		
		// setup restaurants, get a random list of restaurants
		List<Restaurant> selected = RestaurantData.getRandomListOfRestaurants(RESTAURANT_COUNT);
		// Get the Review information
		for (Restaurant restaurant : selected) {
			List<Review> reviews = RestaurantData.getReviewsByRestaurant(restaurant.getName());
			int addedRatings = 0;
			int highestRating = 0;
			String highestReviewer = "";
			String highestReview = "";
			// Add up the reviews for average and get the first highest review
			for (Review review : reviews) {
				int rating = Integer.parseInt(review.getRating());
				addedRatings = addedRatings + rating;
				if (rating > highestRating) {
					highestRating = rating;
					highestReviewer = review.getReviewer();
					highestReview = review.getReview();
				}
			}
			int average = addedRatings/reviews.size();
			restaurant.setAverageReview(Integer.toString(average));
			restaurant.setTopReviewer(highestReviewer);
			restaurant.setReview(highestReview);		
		}
		ballot.setRestaurants(selected);
		
		ballots.put(ballot.getBallotId(), ballot);
		return ballot.getBallotId();
	}
	
	/**
	 * This method retrieves the requested ballot.
	 * @param ballotId The id of the ballot to retrieve.
	 * @return Ballot
	 */
	public static Ballot getBallot(String ballotId) {
		if (!ballots.containsKey(ballotId)) {
			// Throw Exception Ballot does not exist
			throw new WebApplicationException(Util.buildErrorResponse(Status.NOT_FOUND, "Ballot was not found."));
		}
		return ballots.get(ballotId);
	}
	
	/**
	 * This method gets the results of the voting for a ballot.
	 * @param ballotId Ballot Id
	 * @return Map of restaurant Ids and vote counts.
	 */
	public static Map<Long, Integer> getVotingResults(String ballotId) {
		Map<Long, Integer> votingResults = new HashMap<Long, Integer>();
		Set<String> keys = recordedVotes.keySet();
		for (String key : keys) {
			if (key.contains(ballotId)) {
				Vote vote = recordedVotes.get(key);
				if (votingResults.containsKey(vote.getId())) {
					int count = votingResults.get(vote.getId());
					count++;
					votingResults.put(vote.getId(), count);
				} else {
					votingResults.put(vote.getId(), 1);
				}
			}
		}
		
		return votingResults;
	}
	
	/**
	 * This method records the Vote of a user. The vote is rejected if it is past the endTime.
	 * @param vote The vote by a user for a restaurant.
	 * @return returns False if the voting has closed, true if the vote was recored.
	 */
	public static boolean recordVote(Vote vote) {
		if (ballots.containsKey(vote.getBallotId())) {
			Ballot ballot = ballots.get(vote.getBallotId());
			// Date check current time must be before endTime
			Date now = new Date();
			if (now.before(ballot.getEndTime())) {
				// Allow the vote
				recordedVotes.put(vote.getBallotId() + vote.getEmalAddress(), vote);
			} else {
				// Vote not allowed return false
				return false;
			}

		} else {
			// Throw Exception Ballot does not exist
			throw new WebApplicationException(Util.buildErrorResponse(Status.NOT_FOUND, "Ballot was not found."));
		}
		
		return true;
	}
}
