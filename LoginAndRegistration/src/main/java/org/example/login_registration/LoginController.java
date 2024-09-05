package org.example.login_registration;

import dataAccessLayer_LAR.DataAccessLayer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.commentsectionuser.Launcher;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Log-In");
//        stage.setScene(scene);
//        stage.show();
//
//        //check if the resource file exist
////        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
////        stage.setScene(new Scene(parent));
////        stage.show();
//    }

    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private Label status;

    @FXML
    protected void login(ActionEvent event) throws IOException, SQLException {
        //get result from database
        DataAccessLayer dbl = new DataAccessLayer();
        ArrayList<String> result_array;
        result_array = dbl.login(user.getText(),password.getText());

        //check if user exist
        if (Objects.equals(result_array.get(0), "0")) {
            status.setText("Incorrect Username or Password ");
        }
        if (Objects.equals(result_array.get(0), "1")) {
            //send result to the next page
            AfterLoginController afterLoginController = new AfterLoginController();
            afterLoginController.getResult(result_array.get(1),user.getText(),result_array.get(2));

            FXMLLoader loader = new FXMLLoader(getClass().getResource("afterLogin.fxml"));
            Parent homepage = loader.load();
            Scene homepagescene = new Scene(homepage);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(homepagescene);
            stage.setTitle("");
            stage.show();
        }
    }
    @FXML
    protected void register(ActionEvent event) throws IOException { // going to registration page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
        Parent homepage = loader.load();
        Scene homepagescene = new Scene(homepage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homepagescene);
        stage.setTitle("Registeration");
        stage.show();
    }

}