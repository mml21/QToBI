package ContentDelivery;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */
public interface ContentRetrievalClient {

    Content retrieveContent(String query) throws SQLException;

    List<Definition> retrievePublishersDefinitions(String publisher) throws SQLException;
    
    List<Definition> retrieveAllDefinitions() throws SQLException;

}
