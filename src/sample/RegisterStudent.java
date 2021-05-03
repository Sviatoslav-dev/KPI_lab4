package sample;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterStudent {

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField GroupField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField SubjectsFild;

    @FXML
    private Button goBack;

    @FXML
    void initialize() {
        goBack.setOnAction(event -> {
            goBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxmls/register.fxml"));
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

        RegisterButton.setOnAction(event -> AddToDataBase ());
    }

    void AddToDataBase () {
        String name = NameField.getText();
        String group = GroupField.getText();
        String username = LoginField.getText();
        String password = PasswordField.getText();
        ArrayList<String> subjects = new ArrayList<>();

        String s = SubjectsFild.getText();
        int k = 0;

        if (name.equals("") || group.equals("") || username.equals("") || password.equals("")){
            Shake regButtonAnim = new Shake(RegisterButton);
            regButtonAnim.play();
        }
        else {
            subjects.add("");

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',') {
                    k++;
                    subjects.add("");
                } else {
                    subjects.set(k, subjects.get(k) + s.charAt(i));
                }
            }

            Main.db.add_student(name, group, username, password, subjects);
            try {
                Main.db.save_students();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Main.db.save_accaunts();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Back();
        }
    }

    void Back () {
        RegisterButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmls/register.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
