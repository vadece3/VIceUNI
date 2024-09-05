package dataAccessLayer_LAR;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataAccessLayer {
    Connection con;
    DBconnect dbcon = new DBconnect();

    public DataAccessLayer() throws IOException {}

    //checking login parameters in database
    public ArrayList<String> login(String user, String pass) throws SQLException{
        con = dbcon.getConnection();
        String sql = "SELECT * FROM userparams WHERE username=\""+user+"\" AND password=\""+pass+"\"" ;
        PreparedStatement params = con.prepareStatement(sql);

        ResultSet loginresult = params.executeQuery();

        ArrayList<String> result_array = new ArrayList<>();

        // Add elements to the ArrayList
        if (loginresult.next()) {
            result_array.add("1");
            result_array.add(loginresult.getString("permission"));
            result_array.add(loginresult.getString("id"));
        } else {
            result_array.add("0");
        }
        return result_array;
    }

    //adding login parameters
    public void registeration(String user, String pass) throws SQLException {

        con = dbcon.getConnection();
        String sql = "INSERT INTO userparams (username,password,permission) VALUES(\""+user+"\",\""+pass+"\", \"0\")" ;
        PreparedStatement params = con.prepareStatement(sql);

        params.execute();
    }

    //checking if user exist
    public String user_exist(String user) throws SQLException {

        con = dbcon.getConnection();
        String sql = "SELECT id FROM userparams WHERE username = \""+user+"\"" ;
        PreparedStatement params = con.prepareStatement(sql);
        String result;
        ResultSet runQuery = params.executeQuery();
        if (runQuery.next()) {
            result = "1";
        } else {
            result = "0";
        }
        return result;
    }
}
