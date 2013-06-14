/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 15:57
 * To change this template use File | Settings | File Templates.
 */

import ContentDelivery.Content;
import ContentDelivery.ContentRetrievalClient;
import ContentDelivery.HiveTable;
import QToBIServer.*;
import nQL.Processor;
import nQL.nQLProcessor;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;

public class QueryWorkerTest {


    public final JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void TestRequestToHive() {
        final ContentRetrievalClient client = context.mock(ContentRetrievalClient.class);
        final Processor processor = new nQLProcessor(client);
        final Worker worker = new QueryWorker(processor);
        final Request req = context.mock(Request.class);
        final Content con = context.mock(Content.class);

        context.checking(new Expectations() {{
            oneOf(req).getPayload();
            will(returnValue("List x"));
            try {
                oneOf(client).retrieveContent("SELECT * FROM x");
            } catch (SQLException e) {
                Assert.fail("Exception thrown");
            }
            will(returnValue(new HiveTable("Table x\ny z w\n1 2 3\n2 3 4", "someone", "somename")));


        }});

        System.out.println(worker.process(req).getPayload());
    }


}