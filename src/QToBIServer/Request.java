package QToBIServer;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public interface Request {
    String getPayload();

    String getQueryName();

    String getType();
    
    String getUser();
    
    String getPassword();

    void setUser(String user);
}
