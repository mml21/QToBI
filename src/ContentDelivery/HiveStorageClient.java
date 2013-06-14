package ContentDelivery;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: Alexander
 * Date: 28/01/12
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public class HiveStorageClient implements ContentStorageClient {

    Connection con;

    // hiveServer = IP:port   "82.11.202.59:7070"    // Antonio's IP and port
    public HiveStorageClient(Connection connection) throws SQLException {
        this.con = connection;
        createPublishersTable();
    }

    public void storeDefinition(Definition queryDefinition) {
        // Get the query owner which will be table name
        String tableName = queryDefinition.getDefinitionOwner();
        String definitionName = queryDefinition.getQueryName();
        String definition = queryDefinition.getDefinition();

        // Check if table exist already, if it does then check if the query exists, in which case update it.
        // If it doesn't then add definition row with key = definitionName and column = definition
        if (definitionTableExists(tableName)) {
          if (definitionExists(tableName, definitionName))  {
              updateDefinition(tableName, definitionName, definition);
          }
          else createDefinition(tableName, definitionName, definition);
        }
        // If the table does exist then create it and insert definition row,
        else {
           createDefinitionTable(tableName);
           createDefinition(tableName, definitionName, definition);
        }

        // We store all the publishers in table "publishers" to enable us to go through all the stored queries
        addPublisher(tableName);
    }

    public void storeContent(Content result) {
        String contentsTableName = result.getDefinitionOwner() + "." +
                result.getQueryName();
        createContentsTable(contentsTableName);
        saveContents(contentsTableName, result.getPayload());
    }

    private void addPublisher(String publisher) {
        String sql = "insert overwrite into publishers " + "values = (" + publisher + ")";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            // Publisher already in table
            e.printStackTrace();
        }
    }

    private void createPublishersTable() {
        String sql = "create table publishers" + " (publisher string)";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveContents(String contentsTableName, String payload) {
        /* TO-DO: payload will contain just one result or many separated by for e.g spaces or commas? */
        String[] contents = payload.split("\\.");
        String sql = "insert into " + contentsTableName + " (content)" + " values = (" + payload + ")";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean definitionExists(String owner, String definitionName) {
        String sql = "select * from " + owner + " where definitionName = " + definitionName;

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean definitionTableExists(String owner) {
        String sql = "select * from " + owner;

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private void createDefinition(String owner, String definitionName, String definition) {
        String sql = "insert overwrite into " + owner + " (definitionName, definition)" +
                " values = (" + definitionName + "," + definition + ")";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateDefinition(String owner, String definitionName, String definition) {
        String sql = "update " + owner + " set definitionName=" + definitionName + "," +
                " definition= " + definition;

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createDefinitionTable(String tableName) {
        String sql = "create table " + tableName + " (definitionName string, definition string)";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createContentsTable(String tableName) {
        String sql = "create table " + tableName + " (content string)";

        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
