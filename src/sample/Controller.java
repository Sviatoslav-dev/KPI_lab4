package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private PasswordField Pass_field;

    @FXML
    private Button login_but;

    @FXML
    void initialize() {
        login_but.setOnAction(event -> {
            name_field.setText("Hello");
        });
    }
}
