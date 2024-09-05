package org.example.login_registration;

import dataAccessLayer_LAR.DataAccessLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class RegistrationController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label status;

    @FXML
    protected void back_to_login(ActionEvent event) throws IOException {// going back to login page without registering
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent homepage = loader.load();
        Scene homepagescene = new Scene(homepage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homepagescene);
        stage.setTitle("Log-in");
        stage.show();
    }

    @FXML
    protected void submit(ActionEvent event) throws IOException, SQLException { // going back to login page after registration
        DataAccessLayer dl = new DataAccessLayer();
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            status.setText("The username or password should not be empty");
        } else {
            if (Objects.equals(dl.user_exist(username.getText()), "0")) {
                dl.registeration(username.getText(), password.getText());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
                Parent homepage = loader.load();
                Scene homepagescene = new Scene(homepage);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(homepagescene);
                stage.setTitle("Log-in");
                stage.show();
            }else {
                status.setText("Username already exist. Enter a new one");
            }
        }
    }
}