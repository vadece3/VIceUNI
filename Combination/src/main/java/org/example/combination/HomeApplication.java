package org.example.combination;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.login_registration.Launcher;

import java.io.IOException;

public class HomeApplication extends Application {

    static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        HomeApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Welcome!!!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {// open login page from launching main class
        stage.close();
        String[] newArgs = {};
        Launcher.main(newArgs);
    }
}