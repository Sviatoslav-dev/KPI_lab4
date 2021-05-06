package sample;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SignInButton;

    @FXML
    private TextField LoginField;

    @FXML
    void initialize() {

        SignInButton.setOnAction(event -> {
            String user = (Main.db.log_in(LoginField.getText(), PasswordField.getText()));
            System.out.println(user);
            switch (user) {
                case "Decanat" -> {
                    SignInButton.getScene().getWindow().hide();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("fxmls/decanat_menu.fxml"));
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
                case "Lecturer" -> {
                    Main.lectorer_id = Main.db.find_lectorer_by_username(LoginField.getText());

                    SignInButton.getScene().getWindow().hide();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("fxmls/lecturer_menu.fxml"));
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
                case "Student" -> {
                    Main.student_id = Main.db.find_student_by_username(LoginField.getText());

                    SignInButton.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("fxmls/student_menu.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    System.out.println("student");
                }
                default -> {
                    Shake SignInButtonAnim = new Shake(SignInButton);
                    SignInButtonAnim.play();

                    //JFXSnackbar snackbar = new JFXSnackbar(root);
                }
            }
        });
    }
}
