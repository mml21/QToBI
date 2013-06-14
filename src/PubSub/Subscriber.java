package PubSub;

import PubSub.Exceptions.IncorrectSubscriptionFormatException;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 25/01/12
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public interface Subscriber {

    void subscribe(Subscription subscription) throws IncorrectSubscriptionFormatException, IOException;

    void retrieve() throws IOException, InterruptedException;


}
