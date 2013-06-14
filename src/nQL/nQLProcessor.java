package nQL;

import ContentDelivery.Content;
import ContentDelivery.ContentRetrievalClient;
import nQL.Exceptions.SyntaxErrorException;
import nQL.Interpreter.Parser;
import nQL.Interpreter.nQLHiveParser;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 29/01/12
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class nQLProcessor implements Processor {
    ContentRetrievalClient client;
    Parser parser;
    public nQLProcessor(ContentRetrievalClient client)
    {
        this.client = client;
        this.parser = new nQLHiveParser();
    }


    public Reply interpret(Program program)
    {
        Reply out;
        try{
            parser.parse(program);     //Will transform it to a HiveQL query
        }
        catch(SyntaxErrorException e)
        {
          return new nQLReply(e.toString());
        }
       String hivequery = parser.getHiveQuery();
        Content output = null;
        try {
            output = client.retrieveContent(hivequery);
            //client.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        Reply reply = new nQLReply(output.getPayload());
       return reply;
    }
}
