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

    public static DataBase db;

    @FXML
    void initialize() {
        RegisterButton.setOnAction(event -> {
            String name = NameField.getText();
            String username = LoginField.getText();
            String password = PasswordField.getText();
            String subject = SubjectField.getText();
            ArrayList<String> groups = null;
            try {
                db = new DataBase();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            db.add_lecturer(name, username, password, groups, subject);
            try {
                db.save_lecturers();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                db.save_accaunts();
            } catch (IOException e) {
                e.printStackTrace();
            }

            RegisterButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("LoginUser.fxml"));
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
