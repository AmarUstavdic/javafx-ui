module com.example.fourinline {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires com.almasb.fxgl.all;

    opens com.app.fourinline to javafx.fxml;
    exports com.app.fourinline;
}