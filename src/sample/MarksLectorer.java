package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
            for (int j = 0; j < Main.db.students.get(i).subjects.size(); j++) {
                if (Main.db.students.get(i).subjects.get(j).name.equals("ВМ")) {
                    for (int p = 0; p < Main.db.students.get(i).subjects.get(j).subject_marks.size(); p++) {
                        m += Main.db.students.get(i).subjects.get(j).subject_marks.get(p).toString() + ", ";
                    }
                }
            }
            m += "\n";
        }

        marks.setText(m);

        add.setOnAction(event -> {
            String s = mark.getText();
            float mar = Float.parseFloat(s);
            int id = Main.db.find_student_by_name(name.getText());

            if (id != -1) {
                if (Main.db.students.get(id).subjects.get(1).subject_marks != null) {
                    Main.db.add_mark(id, "ВМ", mar);
                } else {
                    Main.db.students.get(id).subjects.get(1).subject_marks = new ArrayList<>();
                    Main.db.add_mark(id, "ВМ", mar);
                }
                try {
                    Main.db.save_students();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(id);
            }

        });
    }
}
