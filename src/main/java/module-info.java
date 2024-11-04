module br.edu.ifsp {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens br.edu.ifsp.app to javafx.fxml;
    opens br.edu.ifsp.controller to javafx.fxml;
    opens br.edu.ifsp.model to javafx.base;

    exports br.edu.ifsp.app;
}
