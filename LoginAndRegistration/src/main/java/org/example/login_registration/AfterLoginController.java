package org.example.login_registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.commentsectionuser.Launcher;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AfterLoginController implements Initializable {
    static String result;
    static String user;
    static Integer userID;
    public void getResult(String result, String user, String userID){
        AfterLoginController.result = result;
        AfterLoginController.user = user;
        AfterLoginController.userID = Integer.parseInt(userID);
    }

    @FXML
    private Label welcomeStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeStatus.setText("Welcome "+user);

    }

    @FXML
    void GoToComment(ActionEvent event) throws IOException {
            //check if the user is a simple user
            if (Objects.equals(result, "0")) {
                //print user's page
//                //close the page before opening the new one
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                stage.close();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Launcher.getStage(stage); //sending the active stage to the comment module
                Launcher.getUserID(userID); //sending the current active user's id to the comment module
                stage.hide();
                String[] arg = {};
                Launcher.main(arg); //launching the module CommentSectionUser

            }
            //check if the user is an admin
            if (Objects.equals(result, "1")) {
                //print admin's page
//                String[] arg = {};
//                Launcher.main(arg);
            }
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent homepage = loader.load();
        Scene homepagescene = new Scene(homepage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homepagescene);
        stage.setTitle("WELCOME!!!");
        stage.show();
    }
}
