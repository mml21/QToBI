package PubSub;

import PubSub.Exceptions.IncorrectPublicationFormatException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 25/01/12
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public interface Publisher {

    void publish(Publication publication) throws IncorrectPublicationFormatException, IOException;

    String getUsername();
}
