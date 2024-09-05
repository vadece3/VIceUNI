package org.example.addcomment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher {
    static Stage commentStage;
    static Integer userID;
    public static void main(String[] args) throws IOException {
        AddCommentController addCommentController = new AddCommentController();
        addCommentController.getCommentStage(commentStage);
        addCommentController.getUserID(userID);

        FXMLLoader fxmlLoader = new FXMLLoader(AddCommentController.class.getResource("addComment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Add New Comment");
        stage.setScene(scene);
        stage.show();
    }
    public static void getStage(Stage commentStage){
        Launcher.commentStage = commentStage;
    }
    //getting the current active user's id
    public static void getUserID(Integer userID) {
        Launcher.userID = userID;
    }
}