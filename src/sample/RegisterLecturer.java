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

public class RegisterLecturer {

    @FXML
    private Button RegisterButton;

    @FXML
    private TextField LoginField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField GroupsField;

    @FXML
    private TextField subjectsField;

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
        String username = LoginField.getText();
        String password = PasswordField.getText();
        ArrayList<String> groups = new ArrayList<>();
        ArrayList<String> subjects = new ArrayList<>();

        String s = GroupsField.getText();
        int k = 0;

        groups.add("");

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',') {
                if (groups.size() > 0 && groups.get(groups.size()  - 1).equals("")) {
                    groups.remove(groups.size()  - 1);
                }
                k++;
                groups.add("");
            } else {
                groups.set(k, groups.get(k) + s.charAt(i));
            }
        }

        s = subjectsField.getText();
        k = 0;

        if (name.equals("") || s.equals("") || username.equals("") || password.equals("")){
            Shake regButtonAnim = new Shake(RegisterButton);
            regButtonAnim.play();
        }
        else {
            subjects.add("");

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ',') {
                    if (subjects.size() > 0 && subjects.get(subjects.size()  - 1).equals("")) {
                        subjects.remove(subjects.size()  - 1);
                    }

                    k++;
                    subjects.add("");
                } else {
                    subjects.set(k, subjects.get(k) + s.charAt(i));
                }
            }

            Main.db.add_lecturer(name, username, password, groups, subjects);

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
