/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 29/01/12
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
import nQL.Exceptions.SyntaxErrorException;
import nQL.Interpreter.Parser;
import nQL.Interpreter.nQLHiveParser;
import nQL.Program;
import nQL.nQLProgram;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class nQLSyntaxErrorTest {

    public final JUnit4Mockery context = new JUnit4Mockery();

    @Test
    public void TestListSyntaxError()
    {
        final Parser parser = new nQLHiveParser();
        final Program prog = new nQLProgram("Lost x");
        try {
            parser.parse(prog);
            fail( "SyntaxErrorException not thrown!" );
        } catch (SyntaxErrorException  expectedException) {
            assertTrue(true);
        }

    }
}
