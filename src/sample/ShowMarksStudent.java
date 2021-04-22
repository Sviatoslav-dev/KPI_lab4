package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        String m = "";

        for (int i = 0; i < Main.db.students.size(); i++) {
            m += Main.db.students.get(i).name + " - ";
            for (int j = 0; j < Main.db.students.get(i).subjects.size(); j++) {
                if (Main.db.students.get(i).subjects.get(j).name.equals(Main.subject)) {
                    if (Main.db.students.get(i).subjects.get(j).subject_marks != null) {
                        for (int p = 0; p < Main.db.students.get(i).subjects.get(j).subject_marks.size(); p++) {
                            m += Main.db.students.get(i).subjects.get(j).subject_marks.get(p).toString() + ", ";
                        }
                    }
                }
            }
            m += "\n";
        }

        marks.setText(m);

    }
}
