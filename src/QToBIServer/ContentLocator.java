package QToBIServer;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 25/01/12
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public interface ContentLocator {
    String getHiveServer();
    
    Connection getConnection() throws SQLException;
}
