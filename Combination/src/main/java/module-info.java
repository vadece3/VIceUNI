module org.example.combination {

    requires org.example.commentsectionuser;
    requires org.example.login_registration;
    requires javafx.fxml;
    requires javafx.graphics;

    opens org.example.combination to javafx.fxml;
    exports org.example.combination;
}