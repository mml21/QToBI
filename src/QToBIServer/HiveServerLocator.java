package QToBIServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: mm111
 * Date: 30/01/12
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class HiveServerLocator implements ContentLocator {

    String server;
    String username = "";
    String password = "";
    String port= "5671";

    public HiveServerLocator(String server)
    {
        this.server = server;
    }

    public String getHiveServer() {
        return server;
    }

    public Connection getConnection() throws SQLException {
        DriverManager.setLoginTimeout(5);
        return  DriverManager.getConnection("jdbc:hive://" + server + ":" + port + "/default", username, password);
    }
}
