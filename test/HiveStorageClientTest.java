import ContentDelivery.*;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Ignore;
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
 * Date: 31/01/12
 * Time: 18:51
 * To change this template use File | Settings | File Templates.
 */
// TO-DO
public class HiveStorageClientTest {


    public final JUnit4Mockery context = new JUnit4Mockery();


    @Test
    @Ignore
    public void StoreDefinitionTest() {
        try {

            final Statement statement = context.mock(Statement.class);
            final Connection con = context.mock(Connection.class);
            final Content content = context.mock(Content.class);
            final Definition definition = new HiveDefinition("marcos","query1","some query");

            final ResultSet res = context.mock(ResultSet.class);
            final ContentStorageClient client = new HiveStorageClient(con);
            context.checking(new Expectations() {{

                allowing(con).createStatement();
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

            client.storeDefinition(definition);
        } catch (SQLException e) {
            fail("SQLException thrown");
        }
    }
}
