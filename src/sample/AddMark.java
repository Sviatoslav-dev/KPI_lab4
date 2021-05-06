package sample;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddMark {

    @FXML
    private TextField markField;

    @FXML
    private Button addButton;

    public static int st_id, sub_id;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            float mark = Float.parseFloat(markField.getText());

            if (mark > 0 && mark <= 100 && markField.getText().matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) {
                Main.db.add_mark(st_id, LectorerMarks.subject, mark);
                try {
                    Main.db.save_students();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addButton.getScene().getWindow().hide();
            }
            else {
                Shake addButtonAnim = new Shake(addButton);
                addButtonAnim.play();
            }
        });
    }
}
