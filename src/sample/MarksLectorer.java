package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MarksLectorer {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    Label marks;

    @FXML
    Label sum;

    @FXML
    TextField name;

    @FXML
    TextField mark;

    @FXML
    Button add;

    @FXML
    void initialize() {
        String m = "";

        for (int i = 0; i < Main.db.students.size(); i++) {
            m += Main.db.students.get(i).name + " - ";
            for (int j = 0; j < Main.db.students.get(i).subjects.size(); i++) {
                if (Main.db.students.get(i).subjects.get(j).equals("ВМ")) {
                    for (int p = 0; p < Main.db.students.get(i).subjects.get(j).subject_marks.size(); i++) {
                        m += Main.db.students.get(i).subjects.get(j).subject_marks.get(p).toString() + ", ";
                    }
                }
            }
            m += "\n";
        }
        add.setOnAction(event -> {

        });
    }
}
