package PubSub;

import ContentDelivery.ContentStorageClient;
import ContentDelivery.HiveTable;
import ContentDelivery.HiveDefinition;
import PubSub.Exceptions.IncorrectSubscriptionFormatException;
import PubSub.Exceptions.InvalidCredentialsException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: mm111
 * Date: 26/01/12
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQSubClient extends Thread implements Subscriber, Client {    //Subscriber client does not need to run on a different thread

    ConnectionFactory factory;
    Connection connection;
    Channel channel;
    String EXCHANGE_NAME;
    String queueName;
    ContentStorageClient contentClient;
    Credentials credentials;

    /*
      We inject the ConnectionFactory with the host (and credentials) already set
     */
    public RabbitMQSubClient(ContentStorageClient contentClient, String exchangeName,
                             ConnectionFactory factory, Credentials credentials)
            throws IOException, InvalidCredentialsException {
        this.factory = factory;
        this.credentials = credentials;
        this.login(this.credentials);

        EXCHANGE_NAME = exchangeName;
        this.contentClient = contentClient;
        channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, "topic");
        queueName = channel.queueDeclare().getQueue();
    }

    public void subscribe(Subscription subscription) throws
            IncorrectSubscriptionFormatException, IOException {
        //Over-engineered , will subscribe to everything anyway.
        channel.queueBind(queueName, EXCHANGE_NAME, subscription.getBindingKey());
    }

    /**
     * Retrieve all newly published queries/results and use ContentStorageClient to store them
     *
     * @throws IOException
     * @throws InterruptedException
     */
    /* TO-DO: Use publication/subscription instead of Data */
    public void retrieve() throws IOException, InterruptedException {
        QueueingConsumer consumer = new QueueingConsumer(channel);
        boolean autoAck = true;
        channel.basicConsume(queueName, autoAck, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            // Of the form: "portfolioN.queryJ.{content|definition}"
            String routingKey = delivery.getEnvelope().getRoutingKey();
            System.out.println(" [x] Received '" + routingKey + "':'"
                    + message + "'");
            String routingKeys[] = routingKey.split("\\.");
            System.out.println(routingKeys.length);

            // If it's a definition, make a table for each client.
            if (routingKeys[2] != null && routingKeys[2].compareTo("definition") == 0) {
                //interact with a ContentStorage client (get stuff and save it on hive)
                HiveDefinition definition = new HiveDefinition(routingKeys[0], routingKeys[1], message);
                contentClient.storeDefinition(definition);
            }
            if (routingKeys[2] != null && routingKeys[2].compareTo("content") == 0) {
                //interact with a ContentStorage client (get stuff and save it on hive)
                HiveTable content = new HiveTable(routingKeys[0], routingKeys[1], message);
                contentClient.storeContent(content);
            }
        }
    }

    public void login(Credentials credentials) throws InvalidCredentialsException, IOException {
        //factory.setUsername(credentials.getUsername());
        //factory.setPassword(credentials.getPassword());
        connection = factory.newConnection();
    }

    public String getUsername() {
        return this.credentials.getUsername();
    }
}
