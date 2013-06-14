package ContentDelivery;



/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public interface ContentStorageClient {

    void storeDefinition(Definition query);

    void storeContent(Content result);

}
