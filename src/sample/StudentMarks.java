package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class StudentMarks {

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane scrollPane;

    ArrayList<Text> marks;
    ArrayList<Text> marks_sum;

    @FXML
    void initialize() {

        input_scrollPane();

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

    void input_scrollPane () {
        StringBuilder m = new StringBuilder();

        int size_Y = 50;

        input_column_names();

        marks = new ArrayList<>();
        marks_sum = new ArrayList<>();

        if (Main.student_id != -1) {
            for (int i = 0; i < Main.db.students.get(Main.student_id).getSubjects().size(); i++) {
                m.append(Main.db.students.get(Main.student_id).getSubjects().get(i).getName()).append(" - ");

                if (Main.db.students.get(Main.student_id).getSubjects().get(i).getSubjectMarks() != null) {
                    for (int j = 0; j < Main.db.students.get(Main.student_id).getSubjects().get(i).getSubjectMarks().size(); j++) {
                        m.append(Main.db.students.get(Main.student_id).getSubjects().get(i).getSubjectMarks().get(j)).append(", ");
                    }
                }

                input_table (size_Y, m, i);

                scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
                size_Y += 50;

                m = new StringBuilder();
            }
        } else {
            System.out.println(Main.student_id);
        }
    }

    void input_column_names () {
        Text marks_text = new Text();
        marks_text.setText("Оцінки");
        marks_text.setX(37);
        marks_text.setY(15);
        marks_text.setStroke(Color.BLACK);

        Text sum_text = new Text();
        sum_text.setText("Сума");
        sum_text.setX(445);
        sum_text.setY(15);
        sum_text.setStroke(Color.BLACK);

        scrollPane.getChildren().add(marks_text);
        scrollPane.getChildren().add(sum_text);
    }

    void input_table (int size_Y, StringBuilder m, int i) {
        marks.add(new Text());
        marks.get(marks.size() - 1).setY(size_Y);
        marks.get(marks.size() - 1).setX(10);
        marks.get(marks.size() - 1).setText(m.toString());

        marks_sum.add(new Text());
        marks_sum.get(marks_sum.size() - 1).setY(size_Y);
        marks_sum.get(marks_sum.size() - 1).setX(450);
        marks_sum.get(marks_sum.size() - 1).setText(Float.toString(Main.db.students.get(Main.student_id).getSubjects().get(i).getSum()));

        scrollPane.getChildren().add(marks.get(marks.size() - 1));
        scrollPane.getChildren().add(marks_sum.get(marks_sum.size() - 1));
    }
}
