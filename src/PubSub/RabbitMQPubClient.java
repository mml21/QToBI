package PubSub;

import PubSub.Exceptions.IncorrectPublicationFormatException;
import PubSub.Exceptions.InvalidCredentialsException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQPubClient implements Publisher, Client {

    ConnectionFactory factory;
    Connection connection;
    Channel channel;
    String exchangeName;
    String queueName;
    Credentials credentials;

    public RabbitMQPubClient(String exchangeName, ConnectionFactory factory, Credentials credentials)
            throws IOException, InvalidCredentialsException {
        this.factory = factory;
        this.login(credentials);
        this.exchangeName = exchangeName;
        this.credentials = credentials;
        channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, "topic");
        queueName = channel.queueDeclare().getQueue();
    }

    public void publish(Publication publication) throws IncorrectPublicationFormatException, IOException {
        // Check publication format correctness, if incorrect throw IncorrectPublicationFormatException
        //BasicProperties props = new BasicProperties.Builder().build();
        //Publication must be serialized at some point, we may need comments etc
        channel.basicPublish(exchangeName, publication.getRoutingKey(), null, publication.getMessageBytes());
        //
        channel.close();
        connection.close();
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
