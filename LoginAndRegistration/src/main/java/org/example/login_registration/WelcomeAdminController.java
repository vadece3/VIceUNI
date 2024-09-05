package org.example.login_registration;

import dataAccessLayer_LAR.DataAccessLayer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class WelcomeAdminController {
    @FXML
    private Button onHelloButtonClick;
    @FXML
    private Label status;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        status.setText("Welcome to JavaFX Application!");
        DataAccessLayer dbl = new DataAccessLayer();
    }
}
