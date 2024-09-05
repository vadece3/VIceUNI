package org.example.commentsectionuser;

import dataAccessLayerCSU.DataAccessLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.JSONObject;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SingleCommentController implements Initializable {

    @FXML
    private Label commentText;

    @FXML
    private Label commentDate;

    @FXML
    private Label commentInterestValue;

    @FXML
    private Button interestButtonStatus;

    @FXML
    private Label author;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO
    }

    /*************************************************
     * WHAT IS TO BE DONE WHEN THE BUTTON IS CLICKED??
     *
     * 1. REPLACE THE user_id's values with the actual userId's variable from the connection status from the
     * column "id" found in the table "userparams". It will be collected from the information
     * of the personne presently connected.
     *
     * 2. this information will be stored in the JSON data as an array. e.g {"user_id":[1,2]}
     *
     * 3. A simmilar process will be done for comments. e.g {"user_id":[1,2],"comments": []}
     *
     * 4. The array will contain as key the user_id of the user who will comment, and as key "comment"
     * for the comment.
     */

    //fill the commentlist class's data (getters and setters)
    private CommentList commentlist;
    public void setCommentList(CommentList commentlist) throws SQLException {
        this.commentlist = commentlist;
        DataAccessLayer dbl = new DataAccessLayer();
        ResultSet userName = dbl.getUserName(commentlist.getUser_id());
        userName.next();

        commentDate.setText(commentlist.getDate());
        commentText.setText(commentlist.getComments());
        commentInterestValue.setText(commentlist.getInterestValue());
        author.setText("Published By: "+userName.getString("username"));
        setButtonStatus(commentlist.getId());

    }

    //set intrestedButton status (either "WAS IT INTERESTING???" or "INTERESTING")
    public void setButtonStatus(Integer id) throws SQLException {
        DataAccessLayer dbl = new DataAccessLayer();
        ResultSet resultJSON = dbl.getJSON(id);
        resultJSON.next();

        String jsonData = resultJSON.getString("userInterestedList");
        if (!resultJSON.wasNull()) {
            JSONObject jsonObject = new JSONObject(jsonData);
            if (jsonObject.has("user_id")) {
                interestButtonStatus.setText("INTERESTING");
                interestButtonStatus.setStyle("-fx-background-color: green;");
            } else {
                interestButtonStatus.setText("WAS IT INTERESTING???");
                interestButtonStatus.setStyle("");
            }
        } else {
            interestButtonStatus.setText("WAS IT INTERESTING???");
            interestButtonStatus.setStyle("");
        }
    }

    @FXML
    void interestButton(ActionEvent event) throws SQLException {
        DataAccessLayer dbl = new DataAccessLayer();
        ResultSet result = dbl.getInterestValue(commentlist.getId());
        result.next();
        ResultSet resultJSON = dbl.getJSON(commentlist.getId());
        resultJSON.next();

        if (resultJSON.getString("userInterestedList") == null) {// check if the value in database is NULL
            String jo = "{\"user_id\": \"1\"}";
            dbl.updateJSON(commentlist.getId(), jo);

            String NewInterestValue = String.valueOf(Integer.parseInt(commentlist.getInterestValue()) + 1);
            dbl.updateInterestValue(commentlist.getId(), NewInterestValue);
        } else {
            String jsonData = resultJSON.getString("userInterestedList");
            JSONObject jsonObject = new JSONObject(jsonData);
            if (jsonObject.has("user_id")) {//check if the interest already exist
                jsonObject.remove("user_id");
                dbl.updateJSON(commentlist.getId(), jsonObject.toString());

                //remove interested
                String NewInterestValue = String.valueOf(Integer.parseInt(result.getString("interestValue")) - 1);
                dbl.updateInterestValue(commentlist.getId(), NewInterestValue);
            } else {
                String jo = "{\"user_id\": \"1\"}";
                dbl.updateJSON(commentlist.getId(), jo);

                //add interested
                String NewInterestValue = String.valueOf(Integer.parseInt(result.getString("interestValue")) + 1);
                dbl.updateInterestValue(commentlist.getId(), NewInterestValue);
            }
        }

        //print new interestedvalue
        ResultSet newResult = dbl.getInterestValue(commentlist.getId());
        newResult.next();
        commentInterestValue.setText(newResult.getString("interestValue"));

        //update button status
        setButtonStatus(commentlist.getId());
    }
}
