package ContentDelivery;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mm111
 * Date: 30/01/12
 * Time: 15:37
 * To change this template use File | Settings | File Templates.
 */
public class HiveDefinition implements Definition, Serializable {

    String owner;
    String queryName;
    String query;

    public HiveDefinition(String owner) {
        this.owner = owner;
    }

    public HiveDefinition(String owner, String queryName, String query) {
        this.owner = owner;
        this.queryName = queryName;
        this.query = query;
    }

    public String getDefinitionOwner() {
        return owner;
    }

    public String getQueryName() {
        return queryName;
    }

    public String getDefinition() {
        return query;
    }

    public void setDefinitionOwner(String owner) {
        this.owner = owner;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public void setDefinition(String query) {
        this.query = query;
    }
}
