package org.example.addcomment;

import dataAccessLayerAC.DataAccessLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddCommentController implements Initializable {
    @FXML
    private TextArea newComment;
    @FXML
    private Label submitStatus;

    static Integer userID;
    static Stage commentStage;

    public void getCommentStage(Stage commentStage){
        AddCommentController.commentStage = commentStage;
    }

    //getting the current active user's id
    public void getUserID(Integer userID) {
        AddCommentController.userID = userID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected void submitComment(ActionEvent event) throws SQLException {

        String newCom = newComment.getText();

        //get current date
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = simpleDateFormat.format(date);

        if (Objects.equals(newCom, "")) {
            submitStatus.setText("Comment should not be empty");
        } else {
            DataAccessLayer dal = new DataAccessLayer();
            dal.insertNewComment(newCom,currentDate,userID);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOnHidden(windowEvent -> commentStage.show());
            stage.hide();
        }
    }

    @FXML
    protected void cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setOnHidden(windowEvent -> commentStage.show());
        stage.hide();
    }
}