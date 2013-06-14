package PubSub;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQPublication implements Publication, Serializable {
    String routingKey;
    String payLoad;

    public RabbitMQPublication(String routingKey, String payload) {
        this.routingKey = routingKey;
        this.payLoad = payload;
    }

    public byte[] getMessageBytes()
    {
        return payLoad.getBytes();
    }

    public String getRoutingKey()
    {
        return routingKey;
    }
}
