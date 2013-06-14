import ContentDelivery.*;
import PubSub.*;
import PubSub.Exceptions.IncorrectSubscriptionFormatException;
import PubSub.Exceptions.InvalidCredentialsException;
import com.rabbitmq.client.ConnectionFactory;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: ap4111
 * Date: 01/02/12
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public class SubscriberSystemTest {

	    public final JUnit4Mockery context = new JUnit4Mockery();
    @Test
    public void subSystemTest() throws InterruptedException {
        Connection con = context.mock(Connection.class);
        Credentials creds = new RabbitMQCredentials("","");
        ConnectionFactory cf = new ConnectionFactory();

        cf.setHost("129.31.196.128");
        final ContentStorageClient client = context.mock(ContentStorageClient.class);


        context.checking(new Expectations() {{
    		oneOf(client).storeDefinition(with(any(Definition.class)));
            oneOf(client).storeContent(with(any(Content.class)));


    	}});

        try {

            Subscriber sub = new RabbitMQSubClient(client, "qtobi",cf,creds);
            sub.subscribe(new RabbitMQSubscription("*.*.definition"));
            sub.subscribe(new RabbitMQSubscription("*.*.content"));
             sub.retrieve();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IncorrectSubscriptionFormatException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
