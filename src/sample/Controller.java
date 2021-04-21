package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            if (Main.db.log_in(LoginField.getText(), PasswordField.getText()).equals("Decanat")) {
                Stage stage = (Stage) SignInButton.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Decanat_menu.fxml"));
                Parent root1 = null;
                try {
                    root1 = (Parent) fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Деканат");
                stage.setScene(new Scene(root1));
                stage.show();
            }
        });
    }
}
