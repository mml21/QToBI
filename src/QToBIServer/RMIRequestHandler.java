package QToBIServer;

import ContentDelivery.ContentStorageClient;
import ContentDelivery.HiveRetrievalClient;
import ContentDelivery.HiveStorageClient;
import PubSub.*;
import PubSub.Exceptions.InvalidCredentialsException;
import com.rabbitmq.client.ConnectionFactory;
import nQL.Processor;
import nQL.nQLProcessor;

import javax.management.remote.rmi.RMIServer;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class RMIRequestHandler extends UnicastRemoteObject implements RequestHandler
{
    static BrokerLocator brokers;
    static ContentLocator hive;
    private Worker worker;
    private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    //De-serialize request
    //Establish request type
    //Create worker thread and pass reply info
    public RMIRequestHandler() throws RemoteException
    {

    }
    //Need to implement callbacks in the future!

    public Response receive(Request req) throws RemoteException {
        String type = req.getType();
        worker = null;
        Response response = new QToBIResponse("BAD REQUEST");
        if(type.equals("pubdef") || type.equals("pubcon"))
        {
            worker = new RabbitMQPubWorker(brokers);
            
        }
        else if(type.equals("nqlprog"))
        {

            try {
                Processor nqlProc = new nQLProcessor(new HiveRetrievalClient(hive.getConnection()));
                worker = new QueryWorker(nqlProc);
            } catch (SQLException e) {
                response.setPayload("SQLException when creating HiveRetrievalClient");
            }

        }
        else if(type.equals("alldef"))
        {
            try {
                worker = new AllDefWorker(new HiveRetrievalClient(hive.getConnection()));
            } catch (SQLException e) {
                response.setPayload("SQLException when creating HiveRetrievalClient");
            }
        }
        if(worker != null)
        {
            response = worker.process(req);
        }
        return  response;
    }

    public static void main(String[] args)
    {
        if(args.length != 2) System.exit(1);
        
        brokers = new RabbitMQBrokerLocator(args[0]);
        hive = new HiveServerLocator(args[1]);
        loadHiveDriver();
        runSubClient();


        RMIRequestHandler rmis = null;
        //Initialise Security Manager
        if(System.getSecurityManager() == null)
        {
            System.setSecurityManager(new SecurityManager());
        }

        // Instantiate the server class
        try
        {
            rmis = new RMIRequestHandler();
        }
        catch (RemoteException e)
        {
            System.out.println("Could not create server instance");

        }

        //Bind to RMI registry
        rebindServer("QToBIServer",rmis);
           
    }

    private static void loadHiveDriver() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void runSubClient()
    {
        try 
        {
            ContentStorageClient substoreclient = new HiveStorageClient(hive.getConnection());
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(brokers.getBroker());
            //user,pass need to be inserted via argument in the future, these are only needed bu QToBIServer since its the only subscriber
            Credentials creds = new RabbitMQCredentials("","");
            //Subscription definition = new
            Subscription defsub = new RabbitMQSubscription("*.*.definition");
            Subscription consub = new RabbitMQSubscription("*.*.content");
            ArrayList<Subscription> subs = new ArrayList<Subscription>();
            subs.add(defsub);
            subs.add(consub);
            Subscriber subClient = new RabbitMQSubClient(substoreclient,"qtobi",factory, creds);
            SubWorkerThread worker = new SubWorkerThread(subClient,subs);
            worker.start();

        } 
        catch (SQLException e) 
        {
            System.out.println("Cannot create StorageClient");
        } 
        catch (IOException ioException) 
        {
            System.out.println("I/O Exception");
        }
        catch (InvalidCredentialsException ic)
        {
            System.out.println("Invalid credentials");
        }

    }

    protected static void rebindServer(String serverURL, RMIRequestHandler server)
    {
        try
        {
            // Start / find the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            
            //Rebind the server to the registry
            registry.rebind(serverURL, server);
        }
        catch (RemoteException e)
        {
            System.out.println("Registry could not be exported");
            System.exit(1);
        }
        catch (NullPointerException e)
        {
            System.out.println("URL or server object is null");
            System.exit(1);
        }


    }

    public Worker getWorker()
    {
        return worker;
    }

}
