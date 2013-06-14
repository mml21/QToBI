package QToBIServer;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 29/01/12
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */
public class QToBIRequest implements Request,java.io.Serializable {

    private String payLoad;
    private String queryName;
    private String type;
    private String password ="";
    private String username="";

    public QToBIRequest(String payLoad, String queryName, String type)
    {
        this.payLoad = payLoad;
        this.queryName = queryName;
        this.type = type;
    }
    public String getPayload() {
        return payLoad;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getQueryName() {
        return queryName;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getType() {
        return type;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getUser() {
        return username;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getPassword() {
        return password;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setUser(String user)
    {
        this.username = user;
    }

}
