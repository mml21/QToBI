package ContentDelivery;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 29/01/12
 * Time: 12:36
 * To change this template use File | Settings | File Templates.
 */
public class HiveTable implements Content, Serializable {

    String owner;
    String queryName;
    String payload;

    public HiveTable() {
    }

    public HiveTable(String payload, String owner, String queryName) {
        this.payload = payload;
        this.owner = owner;
        this.queryName = queryName;
    }

    public String getDefinitionOwner() {
        return owner;
    }

    public String getQueryName() {
        return queryName;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setDefinitionOwner(String owner) {
        this.owner = owner;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

}
