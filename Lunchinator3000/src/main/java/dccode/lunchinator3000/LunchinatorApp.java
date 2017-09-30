package dccode.lunchinator3000;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This class sets up Jersey without having to put entries into the web.xml.
 * @author Doug
 *
 */
@ApplicationPath("api")
public class LunchinatorApp extends Application {

}
