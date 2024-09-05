module org.example.login_registration {
    requires org.example.commentsectionuser;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.controls;

    opens org.example.login_registration to javafx.fxml;
    exports org.example.login_registration;
}