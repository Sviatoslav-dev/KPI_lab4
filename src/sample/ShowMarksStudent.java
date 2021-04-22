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
        String m = "";
        int id = Main.db.find_student_by_username(Main.st_username);

        if (id != -1) {
            for (int i = 0; i < Main.db.students.get(id).subjects.size(); i++) {
                m += Main.db.students.get(id).subjects.get(i).name + " - ";

                if (Main.db.students.get(id).subjects.get(i).subject_marks != null) {
                    for (int j = 0; j < Main.db.students.get(id).subjects.get(i).subject_marks.size(); j++) {
                        m += Main.db.students.get(id).subjects.get(i).subject_marks.get(j) + ",";
                    }
                }

                m += "\n";
            }
        } else {
            System.out.println(id);
        }

        marks.setText(m);

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
