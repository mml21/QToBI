package nQL.Interpreter;

import nQL.Exceptions.SyntaxErrorException;
import nQL.Program;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 25/01/12
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public interface Parser
{
    void parse(Program program) throws SyntaxErrorException;


    String getHiveQuery();
}
