package org.example.commentsectionuser;

import dataAccessLayerCSU.DataAccessLayer;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.addcomment.AddCommentController;
import org.example.addcomment.Launcher;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CommentController implements Initializable {

    @FXML
    private Label status;
    @FXML
    private ListView<AnchorPane> ListCommentFXML;
    ObservableList<CommentList> commentListObservation = FXCollections.observableArrayList();
    static Stage loginStage;
    static Integer userID;

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(CommentController.class.getResource("comment.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Welcome");
//        stage.setScene(scene);
//        stage.show();
//    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            commentDisplayCard();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        launch();
//    }

    //getting the stage of the login module
    public void getLoginStage(Stage loginStage){
        CommentController.loginStage = loginStage;
    }

    //getting the current active user's id
    public void getUserID(Integer userID) {
        CommentController.userID = userID;
    }

    @FXML
    void addComment(ActionEvent event) throws IOException {
        Launcher.getUserID(userID);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Launcher.getStage(stage); //sending the active stage to the comment module
        stage.hide();
        String[] arg = {};
        Launcher.main(arg); //launching the module AddComment
    }

    public ObservableList<CommentList> fillCommentListAdapter() throws SQLException {
        DataAccessLayer dbl  = new DataAccessLayer();
        ResultSet allCommentsRS = dbl.allComments();
        CommentList commentlist;
        while(allCommentsRS.next()) {
          commentlist = new CommentList(
                  allCommentsRS.getString("date"),
                  allCommentsRS.getString("userComment"),
                  allCommentsRS.getString("interestValue"),
                  allCommentsRS.getInt("id"),
                  allCommentsRS.getInt("user_id")
          );

            commentListObservation.add(commentlist);
        }

            return commentListObservation;
    }

    public void commentDisplayCard() throws SQLException, IOException {
        int listSize = fillCommentListAdapter().size();
        for(int r = 0; r < listSize; r++) {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("singleComment.fxml"));
            AnchorPane pane = load.load();
            SingleCommentController scc = load.getController();
            scc.setCommentList(fillCommentListAdapter().get(r));

            ListCommentFXML.getItems().add(pane);
        }
    }

    @FXML
    void close(ActionEvent event) throws IOException { //closing the comment section and opening the Login module
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setOnHidden(windowEvent -> loginStage.show());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("comment.fxml"));
        Parent homepage = loader.load();
        Scene homepagescene = new Scene(homepage);
        Stage stageOnShow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageOnShow.setScene(homepagescene);
        stageOnShow.setTitle("Welcome");

        stage.setOnShown(windowEvent -> stageOnShow.show());
        stage.hide();
    }

    @FXML
    void reload(ActionEvent event) throws IOException {// reloading the page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("comment.fxml"));
        Parent homepage = loader.load();
        Scene homepagescene = new Scene(homepage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(homepagescene);
        stage.setTitle("Welcome");
        stage.show();
    }
}