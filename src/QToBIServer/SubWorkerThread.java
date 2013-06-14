package QToBIServer;

import PubSub.Exceptions.IncorrectSubscriptionFormatException;
import PubSub.Subscriber;
import PubSub.Subscription;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 31/01/12
 * Time: 12:29
 * To change this template use File | Settings | File Templates.
 */
public class SubWorkerThread extends Thread {
    Subscriber subclient;
    ArrayList<Subscription> subs;

    public SubWorkerThread(Subscriber subclient, ArrayList<Subscription> subs) {
        this.subclient = subclient;
        this.subs = subs;
    }

    public void run() {
        for (Subscription sub : subs) {
            try {
                subclient.subscribe(sub);
            } catch (IncorrectSubscriptionFormatException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        try {
            subclient.retrieve();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
