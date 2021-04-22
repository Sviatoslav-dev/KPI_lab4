package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShowMarksStudent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label marks;

    @FXML
    private Label sum;

    @FXML
    private Button goBack;

    @FXML
    private Label showMarks;

    @FXML
    void initialize() {
        goBack.setOnAction(event -> {
            goBack.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StudentMenu.fxml"));
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
