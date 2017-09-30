package dccode.lunchinator3000;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dccode.lunchinator3000.models.Restaurant;
import dccode.lunchinator3000.models.Review;

/**
 * This class is the Data Access Object for Restaurant information.
 * @author Doug
 *
 */
public class RestaurantData {

	private static final String RESTAURANT_URL = "https://interview-project-17987.herokuapp.com/api/restaurants";
	private static final String RESTAURANT_REVIEW_URL = "https://interview-project-17987.herokuapp.com/api/reviews/";
	
	/**
	 * This method gets a specified number of Restaurants that are randomly selected from the list.
	 * @param count The number of restaurants to return.
	 * @return List of random restaurants.
	 */
	public static List<Restaurant> getRandomListOfRestaurants(int count) {
		List<Restaurant> selected = new ArrayList<Restaurant>();

		Client client = ClientBuilder.newClient();
		Response response = client.target(RESTAURANT_URL).request().get();
		
		if(response.getStatus() != 200 ) {
			throw new WebApplicationException(Util.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, "Unable to retrieve Restaurants."));
		}
		
		List<Restaurant> data = response.readEntity(new GenericType<List<Restaurant>>(){});
		if (data == null) {
			// Issue error
		}
		Collections.shuffle(data);
		
		for (int i = 0; i < count; i++) {
			selected.add(data.get(i));
		}
		
		return selected;
	}
	
	/**
	 * This method get the reviews for a restaurant.
	 * @param name The name of the restaurant to get reviews for.
	 * @return List of all reviews for a restaurant.
	 */
	public static List<Review> getReviewsByRestaurant(String name) {
		List<Review> reviews = new ArrayList<Review>();
		
		try {
			// Encode Restaurant name, it might have spaces.
			URI uri = new URI(null, null, name, null);
			
			Client client = ClientBuilder.newClient();
			Response response = client.target(RESTAURANT_REVIEW_URL + uri.toASCIIString()).request().get();
			
			if(response.getStatus() != 200 ) {
				throw new WebApplicationException(Util.buildErrorResponse
						(Status.INTERNAL_SERVER_ERROR, "Unable to retrieve Restaurant Reviews."));
			}
			
			reviews = response.readEntity(new GenericType<List<Review>>(){});

		} catch (URISyntaxException e) {
			throw new WebApplicationException(Util.buildErrorResponse
					(Status.INTERNAL_SERVER_ERROR, "Unable to retrieve Restaurant Reviews."));
		}

		return reviews;
	}
}
