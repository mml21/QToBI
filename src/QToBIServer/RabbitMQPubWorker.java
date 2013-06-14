package QToBIServer;

import PubSub.*;
import PubSub.Exceptions.IncorrectPublicationFormatException;
import PubSub.Exceptions.InvalidCredentialsException;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQPubWorker implements Worker {
    BrokerLocator locator;

    public RabbitMQPubWorker(BrokerLocator locator) {
        this.locator = locator;
    }

    public Response process(Request request) {
        Response response = new QToBIResponse("Failed to publish");

        String brokerhost = locator.getBroker();
        //Get a connection from factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(brokerhost);
        //Get credentials from request
        Credentials creds = new RabbitMQCredentials(request.getUser(), "");

        try {
            RabbitMQPubClient client = new RabbitMQPubClient("qtobi", factory, creds);


            String queryname = request.getQueryName();
            String type = request.getType();
            if (type.equals("pubdef")) type = "definition";
            if (type.equals("pubcon")) type = "content";
            String payload = request.getPayload();

            String routingKey = client.getUsername() + "." + queryname + "." + type;
            client.publish(new RabbitMQPublication(routingKey, payload));
            ////

            ////
            response.setPayload(queryname + " " + type + " published successfuly");
        } catch (InvalidCredentialsException ice) {
            response.setPayload("Login failed, are you sure you're a publisher?");
        } catch (IncorrectPublicationFormatException ipf) {
            response.setPayload("Publication of incorrect format");
        } catch (IOException ioe) {
            response.setPayload("IOException while publishing");
        }


        return response;
    }

}
