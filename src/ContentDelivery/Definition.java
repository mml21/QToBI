package ContentDelivery;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public interface Definition {

    String getDefinitionOwner();  // publisher name

    String getQueryName(); // the query definition name will be used as a Key in the table

    String getDefinition(); // the query description which will be a column in the table

    void setDefinitionOwner(String owner);

    void setQueryName(String queryName);

    void setDefinition(String query);

}
