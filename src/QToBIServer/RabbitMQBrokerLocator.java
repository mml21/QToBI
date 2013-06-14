package QToBIServer;

/**
 * Created by IntelliJ IDEA.
 * User: mm111
 * Date: 30/01/12
 * Time: 14:56
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQBrokerLocator implements BrokerLocator {
    String singleBroker;

    public RabbitMQBrokerLocator(String brokerHost)
    {
        this.singleBroker = brokerHost;
    }

    public String getBroker() {
        return singleBroker;
    }
}
