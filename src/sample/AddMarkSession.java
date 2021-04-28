package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddMarkSession {
    @FXML
    private TextField markField;

    @FXML
    private Button addButton;

    public static int st_id, sub_id;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            if (Main.db.students.get(st_id).subjects.get(Main.db.subjects_id(st_id, LectorerSession.subject)).session <= 0) {
                Main.db.students.get(st_id).subjects.get(Main.db.subjects_id(st_id, LectorerSession.subject)).session = Float.parseFloat(markField.getText());
            } else if (Main.db.students.get(st_id).subjects.get(Main.db.subjects_id(st_id, LectorerSession.subject)).first_dopka <= 0) {
                Main.db.students.get(st_id).subjects.get(Main.db.subjects_id(st_id, LectorerSession.subject)).first_dopka = Float.parseFloat(markField.getText());
            } else if (Main.db.students.get(st_id).subjects.get(Main.db.subjects_id(st_id, LectorerSession.subject)).second_dopka <= 0) {
                Main.db.students.get(st_id).subjects.get(Main.db.subjects_id(st_id, LectorerSession.subject)).second_dopka = Float.parseFloat(markField.getText());
            }

            //Main.db.add_mark(st_id, MarksLectorer.subject, Float.parseFloat(markField.getText()));
            try {
                Main.db.save_students();
            } catch (IOException e) {
                e.printStackTrace();
            }
            addButton.getScene().getWindow().hide();
        });
    }
}
