package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignInButton;

    @FXML
    private TextField LoginField;

    @FXML
    void initialize() {
        SignInButton.setOnAction(event -> {
            System.out.println(Main.db.log_in(LoginField.getText(), PasswordField.getText()));
        });
    }
}
