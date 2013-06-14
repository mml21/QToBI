package ContentDelivery;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 14:30
 * To change this template use File | Settings | File Templates.
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class HiveRetrievalClient extends Thread implements ContentRetrievalClient {


    Connection con;

    // hiveServer = IP:port   "82.11.202.59:7070"    // Antonio's IP and port
    public HiveRetrievalClient(Connection connection) throws SQLException {
        this.con = connection;
    }

    public Content retrieveContent(String query) throws SQLException {
        // regular hive query
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(query);
        Content content = new HiveTable();

        while (res.next()) {
            content.setPayload(res.getString(1));
        }

        return content;
    }

    public List<Definition> retrievePublishersDefinitions(String publisher) throws SQLException {

        List<Definition> definitions = new ArrayList<Definition>();

        // regular hive query
        Statement stmt = con.createStatement();
        String sql = "select * from " + publisher;
        ResultSet res = stmt.executeQuery(sql);
        HiveDefinition definition = new HiveDefinition(publisher);

        while (res.next()) {
            definition.setQueryName(res.getString(1));
            definition.setDefinition(res.getString(2));
            definitions.add(definition);
        }

        return definitions;
    }

    // Retrieves all the definitions from all the publishers
    public List<Definition> retrieveAllDefinitions() throws SQLException {
        List<String> publishers = new ArrayList<String>();
        List<Definition> definitions = new ArrayList<Definition>();
        Statement stmt = con.createStatement();
        String sql = "select * from publishers";
        ResultSet res = stmt.executeQuery(sql);

        while (res.next()) {
            publishers.add(res.getString(1));
        }

        Iterator<String> it = publishers.iterator();

        while (it.hasNext()) {
            String publisher = it.next();
            sql = "select * from " + publisher;
            res = stmt.executeQuery(sql);
            HiveDefinition definition = new HiveDefinition(publisher);

            while (res.next()) {
                definition.setQueryName(res.getString(1));
                definition.setDefinition(res.getString(2));
                definitions.add(definition);
            }
        }

        return definitions;
    }


}