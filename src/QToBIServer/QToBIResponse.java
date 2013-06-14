package QToBIServer;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
public class QToBIResponse implements Response,java.io.Serializable {
    String payload;

    public QToBIResponse(String payload)
    {
        this.payload = payload;
    }

    public String getPayload(){
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}
