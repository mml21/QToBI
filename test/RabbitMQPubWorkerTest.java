import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 31/01/12
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public class RabbitMQPubWorkerTest {

	public final JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void allDefRequest() throws RemoteException
    {
         /*
        final BrokerLocator brokers = context.mock(BrokerLocator.class);


        list.add(new HiveDefinition("owner","defname","def"));
        list.add(new HiveDefinition("owner","defname","def"));

        context.checking(new Expectations() {{
            try {
                oneOf(client).retrieveAllDefinitions();  will(returnValue(list));

            } catch (SQLException e) {
                fail("SqlException thrown");
            }



    	}});

        response = handler.process(req);

        assertTrue(response.getPayload().equals("defname owner def\ndefname owner def\n"));

        */
    }
}
