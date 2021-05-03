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

    public static int st_id;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            if (Main.db.students.get(st_id).getSubjects().get(Main.db.subjects_id(st_id, LectorerSession.subject)).getSession() <= 0) {
                Main.db.students.get(st_id).getSubjects().get(Main.db.subjects_id(st_id, LectorerSession.subject)).setSession(Float.parseFloat(markField.getText()));
            } else if (Main.db.students.get(st_id).getSubjects().get(Main.db.subjects_id(st_id, LectorerSession.subject)).getFirstAddSession() <= 0) {
                Main.db.students.get(st_id).getSubjects().get(Main.db.subjects_id(st_id, LectorerSession.subject)).setFirstAddSession(Float.parseFloat(markField.getText()));
            } else if (Main.db.students.get(st_id).getSubjects().get(Main.db.subjects_id(st_id, LectorerSession.subject)).getSecondAddSession() <= 0) {
                Main.db.students.get(st_id).getSubjects().get(Main.db.subjects_id(st_id, LectorerSession.subject)).setSecondAddSession(Float.parseFloat(markField.getText()));
            }

            try {
                Main.db.save_students();
            } catch (IOException e) {
                e.printStackTrace();
            }
            addButton.getScene().getWindow().hide();
        });
    }
}
