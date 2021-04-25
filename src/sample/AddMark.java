package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMark {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField markField;

    @FXML
    private Button addButton;

    public static int st_id, sub_id;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            Main.db.add_mark(st_id, MarksLectorer.subject, Float.parseFloat(markField.getText()));
            try {
                Main.db.save_students();
            } catch (IOException e) {
                e.printStackTrace();
            }
            addButton.getScene().getWindow().hide();
        });
    }
}
