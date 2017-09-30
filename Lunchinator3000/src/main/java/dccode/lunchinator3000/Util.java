package dccode.lunchinator3000;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dccode.lunchinator3000.models.ErrorMessage;

/**
 * Utility class for handy code needed in multiple locations.
 * @author Doug
 *
 */
public class Util {

	/**
	 * This method puts together the status code and error message to be returned to the user.
	 * @param status Status code to return to the user.
	 * @param message Error message to return to the user.
	 * @return Response object that will be sent to the user containing error status and message.
	 */
	public static Response buildErrorResponse(Status status, String message) {
		
		ErrorMessage msg = new ErrorMessage(message);
		
		return Response.status(status).entity(msg).build();
	}
}
