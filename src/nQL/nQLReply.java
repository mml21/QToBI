package nQL;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 29/01/12
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */
public class nQLReply implements Reply{
    String payload;

    public nQLReply(String payload)
    {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}
