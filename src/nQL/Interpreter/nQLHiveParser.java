package nQL.Interpreter;

import nQL.Exceptions.SyntaxErrorException;
import nQL.Program;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 29/01/12
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
public class nQLHiveParser implements Parser
{
    String hivequery;
    public void parse(Program program) throws SyntaxErrorException
    {
        String prog = program.toString();
        String[] parts = prog.split(" ");
        if(parts[0].equals("List"))
        {
            hivequery = "SELECT * FROM "+ parts[1];
        }
        else throw new SyntaxErrorException();

    }

    public String getHiveQuery()
    {
        return hivequery;
    }
}
