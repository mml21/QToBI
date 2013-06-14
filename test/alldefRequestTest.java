import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;

import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 31/01/12
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
public class alldefRequestTest {

    public final JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void allDefRequest() throws RemoteException {
        /*
        final Request req = new QToBIRequest("payload","name","alldef");
        final ContentLocator hive = context.mock(ContentLocator.class);
        final Connection con = context.mock(Connection.class);
        final RMIRequestHandler handler = new RMIRequestHandler();
        final Response response = new QToBIResponse("RESPONSE");

        context.checking(new Expectations() {{
            try {
                oneOf(hive).getConnection();  will(returnValue(con));

            } catch (SQLException e) {
                fail("SqlException thrown");
            }



    	}});
        try {

            handler.setHive(hive);
            handler.receive(req);
        } catch (RemoteException e) {
            fail("RemoteException thrown");
        }
         */

    }
}
