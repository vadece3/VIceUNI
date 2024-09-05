package org.example.combination;

import javafx.application.Platform;
import javafx.fxml.FXML;
import org.example.login_registration.Launcher;

import java.io.IOException;

public class HomeController {

    @FXML
    protected void onHelloButtonClick() throws IOException {// open login page
        Platform.exit();
        String[] newArgs = {};
        Launcher.main(newArgs);
    }
}