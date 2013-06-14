package nQL;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 29/01/12
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class nQLProgram implements Program
{
    String program;
    public nQLProgram(String program)
    {
       this.program = program;
    }

    public String toString()
    {
        return program;
    }
}
