package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentMarks {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    void initialize() {
        String m = "";

        int size_Y = 50;

        Text marks_text = new Text();
        marks_text.setText("Оцінки");
        marks_text.setX(50);
        marks_text.setY(15);
        marks_text.setStroke(Color.BLUE);

        Text sum_text = new Text();
        sum_text.setText("Сума");
        sum_text.setX(450);
        sum_text.setY(15);
        sum_text.setStroke(Color.BLUE);

        scrollPane.getChildren().add(marks_text);
        scrollPane.getChildren().add(sum_text);

        ArrayList<Text> marks = new ArrayList<>();
        ArrayList<Text> marks_sum = new ArrayList<>();

        if (Main.student_id != -1) {
            for (int i = 0; i < Main.db.students.get(Main.student_id).subjects.size(); i++) {
                m += Main.db.students.get(Main.student_id).subjects.get(i).name + " - ";

                if (Main.db.students.get(Main.student_id).subjects.get(i).subject_marks != null) {
                    for (int j = 0; j < Main.db.students.get(Main.student_id).subjects.get(i).subject_marks.size(); j++) {
                        m += Main.db.students.get(Main.student_id).subjects.get(i).subject_marks.get(j) + ", ";
                    }
                }

                marks.add(new Text());
                marks.get(marks.size() - 1).setY(size_Y);
                marks.get(marks.size() - 1).setX(10);
                marks.get(marks.size() - 1).setText(m);

                marks_sum.add(new Text());
                marks_sum.get(marks_sum.size() - 1).setY(size_Y);
                marks_sum.get(marks_sum.size() - 1).setX(450);
                marks_sum.get(marks_sum.size() - 1).setText(Float.toString(Main.db.students.get(Main.student_id).subjects.get(i).sum));

                scrollPane.getChildren().add(marks.get(marks.size() - 1));
                scrollPane.getChildren().add(marks_sum.get(marks_sum.size() - 1));

                scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
                size_Y += 50;

                m = "";
            }
        } else {
            System.out.println(Main.student_id);
        }

        goBack.setOnAction(event -> {
            goBack.getScene().getWindow().hide();

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

        });
    }
}
