package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterLecturer {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField SubjectField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField GroupsField;

    @FXML
    void initialize() {
        RegisterButton.setOnAction(event -> {
            String name = NameField.getText();
            String username = LoginField.getText();
            String password = PasswordField.getText();
            String subject = SubjectField.getText();
            ArrayList<String> groups = new ArrayList<>();

            String s = GroupsField.getText();
            int k = 0;

            groups.add("");

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',') {
                    k++;
                    groups.add("");
                } else {
                    groups.set(k, groups.get(k) + s.charAt(i));
                }
            }

            Main.db.add_lecturer(name, username, password, groups, subject);
            try {
                Main.db.save_lecturers();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Main.db.save_accaunts();
            } catch (IOException e) {
                e.printStackTrace();
            }

            RegisterButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Register.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
