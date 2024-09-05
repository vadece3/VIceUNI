module org.example.commentsectionuser {
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    //requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.json;
    requires java.sql;
    requires org.example.addcomment;

    opens org.example.commentsectionuser to javafx.fxml;
    exports org.example.commentsectionuser;
}