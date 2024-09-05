package dataAccessLayerCSU;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnect {

    Connection con;

    public DBconnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.print("Search for Connection class...");
        }
        catch(ClassNotFoundException cnf){
            System.out.println("Class not Found and unable to connect ..."+cnf.getLocalizedMessage());
        }
        try{
            /*
            Connection to database
           */
            Properties properties = new Properties();
            InputStream inputStream = DBconnect.class.getClassLoader().getResourceAsStream("config.properties");

            // Load the properties file
            properties.load(inputStream);

            // Access properties
            String url = properties.getProperty("server");
            String user = properties.getProperty("username");
            String pass = properties.getProperty("password");

            con = DriverManager.getConnection(url,user,pass);
        }
        catch(SQLException e2){
            e2.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return con;
    }

}
