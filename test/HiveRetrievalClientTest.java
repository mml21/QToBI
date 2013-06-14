import ContentDelivery.Content;
import ContentDelivery.ContentRetrievalClient;
import ContentDelivery.HiveRetrievalClient;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 30/01/12
 * Time: 18:59
 * To change this template use File | Settings | File Templates.
 */
public class HiveRetrievalClientTest {

    public final JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void RetrieveContentTest() {
        try {

            final Statement statement = context.mock(Statement.class);
            final Connection con = context.mock(Connection.class);
            final Content content = context.mock(Content.class);
            final ResultSet res = context.mock(ResultSet.class);
            final ContentRetrievalClient client = new HiveRetrievalClient(con);
            context.checking(new Expectations() {{

                oneOf(con).createStatement();
                will(returnValue(statement));
                oneOf(statement).executeQuery("some query");
                will(returnValue(res));
                oneOf(res).next();
                will(returnValue(true));
                oneOf(res).getString(1);
                will(returnValue("some result"));
                oneOf(res).next();
                will(returnValue(false));
            }});

            client.retrieveContent("some query");
        } catch (SQLException e) {
            fail("SQLException thrown");
        }

    }
}
