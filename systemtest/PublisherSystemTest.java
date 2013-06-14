import PubSub.*;
import PubSub.Exceptions.IncorrectPublicationFormatException;
import PubSub.Exceptions.InvalidCredentialsException;
import com.rabbitmq.client.ConnectionFactory;
import junit.framework.Assert;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: ap4111
 * Date: 01/02/12
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class PublisherSystemTest {

    @Test
    public void publisherSystemTest()
    {

        Credentials creds = new RabbitMQCredentials("","");
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("82.11.202.59");
        try {
            Publisher publisher = new RabbitMQPubClient("qtobi",cf,creds);
            try {
                publisher.publish(new RabbitMQPublication("alex.test.definition","this is a test query"));
            } catch (IncorrectPublicationFormatException e) {
                Assert.fail("Exception thrown");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Assert.fail("exception thrown");
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Assert.fail("exception thrown");
        }
    }
}
