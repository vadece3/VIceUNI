package org.example.commentsectionuser;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher {
    static Stage loginStage;
    static Integer userID;
    public static void main(String[] args) throws IOException {

        CommentController commentController = new CommentController();
        commentController.getLoginStage(loginStage);
        commentController.getUserID(userID);

        FXMLLoader fxmlLoader = new FXMLLoader(CommentController.class.getResource("comment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Welcome");
        stage.setScene(scene);
        stage.show();
    }
    public static void getStage(Stage loginStage){// getting the stage of the login module to be used in case of logging out
        Launcher.loginStage = loginStage;
    }

    public static void getUserID(Integer userID){// getting the userID
        Launcher.userID = userID;
    }
}