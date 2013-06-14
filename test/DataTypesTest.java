import ContentDelivery.Content;
import ContentDelivery.HiveTable;
import QToBIServer.QToBIRequest;
import QToBIServer.QToBIResponse;
import QToBIServer.Request;
import QToBIServer.Response;
import nQL.Program;
import nQL.Reply;
import nQL.nQLProgram;
import nQL.nQLReply;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 30/01/12
 * Time: 19:19
 * This test set needs to be maintained
 */
public class DataTypesTest {
    @Test
    public void qtobiRequestGetTest()
    {
        Request req = new QToBIRequest("payload","query","pubreq");
        assertTrue(req.getPayload().equals("payload"));
        assertTrue(req.getQueryName().equals("query"));
        assertTrue(req.getType().equals("pubreq"));

    }
    @Test
    public void qtobiResponseTest()
    {
        Response rep = new QToBIResponse("PAYLOAD");

        assertTrue(rep.getPayload().equals("PAYLOAD"));
    }
    
    @Test
    public void nqlReplyTest()
    {
        Reply rep = new nQLReply("reply");
        
        assertTrue(rep.getPayload().equals("reply"));
    }
    
    @Test
    public void nqlProgramTest()
    {
        Program prog = new nQLProgram("prog");
        
        assertTrue(prog.toString().equals("prog"));
    }

    @Test
    public void HiveTableTest()
    {
        Content con = new HiveTable();
        con.setPayload("payload");
        assertTrue(con.getPayload().equals("payload"));
    }

     @Test
    public void qtobirequest()
     {
         Request req = new QToBIRequest("payload","name","query");
         req.setUser("user");
         assertTrue(req.getPayload().equals("payload"));
         assertTrue(req.getUser().equals("user"));
         assertTrue(req.getQueryName().equals("name"));
         assertTrue(req.getPayload().equals("payload"));

     }
}
