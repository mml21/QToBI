import ContentDelivery.*;
import QToBIServer.ContentLocator;
import QToBIServer.HiveServerLocator;
import junit.framework.Assert;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 31/01/12
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class HiveSystemTest {
    private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";


	    public final JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void hiveSystemTest() throws RemoteException
    {
         try {
            Class.forName(driverName).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InstantiationException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         } catch (IllegalAccessException e) {
             e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
         }


        ContentLocator hive = new HiveServerLocator("82.11.202.59");
        try {

            Connection con = hive.getConnection();
             System.out.println("--------Connected to HiveServer------");
            ContentStorageClient client = new HiveStorageClient(con);

            client.storeDefinition(new HiveDefinition("alex","test1","this is a test query"));
            con.commit();
            con.close();
            Connection con2 = hive.getConnection();
            ContentRetrievalClient client2 = new HiveRetrievalClient(con2);
            List<Definition> defs = client2.retrievePublishersDefinitions("alex");
            System.out.println(defs.get(1).getDefinition());
            con.commit();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            Assert.fail("Exception thrown");
        }

    }
}
