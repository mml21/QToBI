package PubSub;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 25/01/12
 * Time: 18:32
 * To change this template use File | Settings | File Templates.
 */
public interface Publication {
    String getRoutingKey();

    byte[] getMessageBytes();
}
