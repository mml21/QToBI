package PubSub;

/**
 * Created by IntelliJ IDEA.
 * User: marcos
 * Date: 31/01/12
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQSubscription implements Subscription {

    String bindingKey;

    public RabbitMQSubscription(String bindingKey) {
        this.bindingKey = bindingKey;
    }

    public String getBindingKey() {
        return bindingKey;
    }
}
