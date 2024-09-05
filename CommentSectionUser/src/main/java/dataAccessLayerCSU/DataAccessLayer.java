package dataAccessLayerCSU;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccessLayer {
    Connection con;
    DBconnect db = new DBconnect();

    public DataAccessLayer() {}

    //get all comments from database
    public ResultSet allComments() throws SQLException {
        con = db.getConnection();
        return con.createStatement().executeQuery("SELECT * FROM comments");
    }

    //get comments of particular user from database
    public ResultSet particularComments(Integer userID) throws SQLException {
        con = db.getConnection();
        return con.createStatement().executeQuery("SELECT * FROM comments WHERE user_id = \""+userID+"\"");
    }

    //get comment's interest value
    public ResultSet getInterestValue(Integer id) throws SQLException {
        con = db.getConnection();
        return con.createStatement().executeQuery("SELECT interestValue FROM comments WHERE id = \""+id+"\"");
    }
    //get comment's JSON
    public ResultSet getJSON(Integer id) throws SQLException {
        con = db.getConnection();
        return con.createStatement().executeQuery("SELECT userInterestedList FROM comments WHERE id = \""+id+"\"");
    }
    //get user name
    public ResultSet getUserName(Integer user_id) throws SQLException {
        con = db.getConnection();
        return con.createStatement().executeQuery("SELECT username FROM userparams WHERE id = \""+user_id+"\"");
    }
    //update comment's interest value
    public void updateInterestValue(Integer id, String newInterestValue) throws SQLException {
        con = db.getConnection();
        con.createStatement().execute("UPDATE comments SET interestValue = '"+newInterestValue+"' WHERE id = \""+id+"\"");
    }
    //update comment's JSON
    public void updateJSON(Integer id, String newJSON) throws SQLException {
        con = db.getConnection();
        con.createStatement().execute("UPDATE comments SET userInterestedList = '"+newJSON+"' WHERE id = \""+id+"\"");
    }

}
