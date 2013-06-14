import ContentDelivery.ContentRetrievalClient;
import ContentDelivery.Definition;
import ContentDelivery.HiveDefinition;
import QToBIServer.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 31/01/12
 * Time: 18:53
 * To change this template use File | Settings | File Templates.
 */
public class allDefWorkerTest {

    public final JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void allDefWorkerTest() throws RemoteException {

        final Request req = new QToBIRequest("payload", "name", "alldef");
        final ContentLocator hive = context.mock(ContentLocator.class);
        final Connection con = context.mock(Connection.class);
        final ContentRetrievalClient client = context.mock(ContentRetrievalClient.class);
        final Worker handler = new AllDefWorker(client);
        Response response;
        final List<Definition> list = new ArrayList<Definition>();

        list.add(new HiveDefinition("owner", "defname", "def"));
        list.add(new HiveDefinition("owner", "defname", "def"));

        context.checking(new Expectations() {{
            try {
                oneOf(client).retrieveAllDefinitions();
                will(returnValue(list));

            } catch (SQLException e) {
                fail("SqlException thrown");
            }


        }});

        response = handler.process(req);

        assertTrue(response.getPayload().equals("defname owner def\ndefname owner def\n"));


    }
}
