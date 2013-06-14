package QToBIServer;

import nQL.Processor;
import nQL.Program;
import nQL.Reply;
import nQL.nQLProgram;


/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 16:29
 * To change this template use File | Settings | File Templates.
 */
public class QueryWorker implements Worker {

    Processor processor;

    public QueryWorker(Processor processor)
    {
       this.processor = processor;
    }

    public Response process(Request request)
    {
        String query = request.getPayload();
        Program program = new nQLProgram(query);
        Reply out = processor.interpret(program);
        Response response = new QToBIResponse(out.getPayload());
        return response;
    }
}
