package QToBIServer;

import PubSub.Client;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 25/01/12
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */
public interface Worker {  //Still not clear on how data will be returned to user
    Response process(Request request);
}
