package ContentDelivery;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public interface Content {

    String getDefinitionOwner();  // publisher name

    String getQueryName(); // the query definition name will be used as a Key in the table

    String getPayload();  // query results

    void setPayload(String payload);

    void setDefinitionOwner(String owner);

    void setQueryName(String queryName);

}
