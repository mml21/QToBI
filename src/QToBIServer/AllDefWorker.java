package QToBIServer;

import ContentDelivery.ContentRetrievalClient;
import ContentDelivery.Definition;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 31/01/12
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class AllDefWorker implements Worker {
    ContentRetrievalClient hiveclient;

    public AllDefWorker(ContentRetrievalClient client)
    {
        this.hiveclient = client;
    }

    public Response process(Request request)
    {
        Response response = new QToBIResponse("SQL Exception");
        String payload ="";
        try {
            List<Definition> definitions = hiveclient.retrieveAllDefinitions();
            for(Definition def : definitions)
            {
                payload += def.getQueryName() + " " +  def.getDefinitionOwner() + " " + def.getDefinition() + "\n";
            }
        } catch (SQLException e) {
            return response;
        }
        response.setPayload(payload);
        //hiveclient.closeConnection()
        return response;
        
    }

}
