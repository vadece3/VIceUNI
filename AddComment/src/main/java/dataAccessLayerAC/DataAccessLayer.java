package dataAccessLayerAC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataAccessLayer {
    Connection con;
    DBconnect db = new DBconnect();

    public DataAccessLayer() {}


    //insert new comment
    public void insertNewComment(String nc, String date, Integer userID) throws SQLException {
        con = db.getConnection();
        String sql = "INSERT INTO comments (userComment,date,user_id) VALUES (?,?,?)";
        PreparedStatement addComment = con.prepareStatement(sql);
        addComment.setString(1,nc);
        addComment.setString(2,date);
        addComment.setInt(3,userID);
        addComment.execute();

    }


}
