package QToBIServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 25/01/12
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */

//Handles RMI requests, this is where the GUI should hook up to

public interface RequestHandler extends Remote{
    public Response receive(Request req) throws RemoteException;
}
