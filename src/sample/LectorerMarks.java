package sample;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LectorerMarks {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Save;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private ChoiceBox groupsBox;

    @FXML
    private ChoiceBox subjectsBox;

    public static String subject;

    private ArrayList<Text> marks;
    private ArrayList<Button> add_buttons;
    private ArrayList<Text> marks_sum;

    @FXML
    void initialize() {

        inputChoiseBoxes ();

        input_scrollPane ();

        groupsBox.setOnAction(evant -> {
            clear_scrollPane ();
            input_scrollPane ();
        });

        subjectsBox.setOnAction(evant -> {
            clear_scrollPane ();
            input_scrollPane ();
        });

        Save.setOnAction(event -> {
            Save.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxmls/lecturer_menu.fxml"));
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
        subject = (String) subjectsBox.getValue();
        String m = "";

        int size_Y = 50;
        int sub_id = -1;

        marks = new ArrayList<>();
        add_buttons = new ArrayList<>();
        marks_sum = new ArrayList<>();

        ArrayList<Integer> students_id;

        students_id = Main.db.get_students_by_group_and_subject((String) groupsBox.getValue(), (String) subjectsBox.getValue());

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


        for (int i = 0; i < students_id.size(); i++) {
            m += Main.db.students.get(students_id.get(i)).name + " - ";

            for (int j = 0; j < Main.db.students.get(students_id.get(i)).subjects.size(); j++) {
                if (Main.db.students.get(students_id.get(i)).subjects.get(j).name.equals(subject)) {
                    sub_id = j;
                    AddMark.sub_id = sub_id;
                    if (Main.db.students.get(students_id.get(i)).subjects.get(j).subject_marks != null) {
                        for (int p = 0; p < Main.db.students.get(students_id.get(i)).subjects.get(j).subject_marks.size(); p++) {
                            m += Main.db.students.get(students_id.get(i)).subjects.get(j).subject_marks.get(p).toString() + ", ";
                        }
                    }
                }
            }

            marks.add(new Text());
            marks.get(marks.size() - 1).setY(size_Y);
            marks.get(marks.size() - 1).setX(10);
            marks.get(marks.size() - 1).setText(m);

            marks_sum.add(new Text());
            marks_sum.get(marks_sum.size() - 1).setY(size_Y);
            marks_sum.get(marks_sum.size() - 1).setX(450);
            marks_sum.get(marks_sum.size() - 1).setText(Float.toString(Main.db.students.get(i).subjects.get(sub_id).sum));

            add_buttons.add(new Button());
            add_buttons.get(add_buttons.size() - 1).setLayoutX(550);
            add_buttons.get(add_buttons.size() - 1).setLayoutY(size_Y - 20);
            add_buttons.get(add_buttons.size() - 1).setPrefHeight(15);
            add_buttons.get(add_buttons.size() - 1).setPrefWidth(15);
            add_buttons.get(add_buttons.size() - 1).setText("+");

            int student_id = students_id.get(i);
            add_buttons.get(add_buttons.size() - 1).setOnAction(event -> {
                add_mark(student_id);
            });

            scrollPane.getChildren().add(marks.get(marks.size() - 1));
            scrollPane.getChildren().add(marks_sum.get(marks_sum.size() - 1));
            scrollPane.getChildren().add(add_buttons.get(add_buttons.size() - 1));

            scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
            size_Y += 50;

            m = "";
        }
    }

    void add_mark (int student_id) {
        System.out.println(student_id);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmls/add_mark.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();

        Scene secondScene = new Scene(root);

        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        newWindow.show();

        AddMark.st_id = student_id;
    }

    void clear_scrollPane () {
        marks.clear();
        marks_sum.clear();
        add_buttons.clear();

        scrollPane.getChildren().clear();
    }

    void inputChoiseBoxes () {
        groupsBox.setValue(Main.db.lecturers.get(Main.lectorer_id).groups.get(0));
        subjectsBox.setValue(Main.db.lecturers.get(Main.lectorer_id).subjects.get(0));

        for (int i = 0; i < Main.db.lecturers.get(Main.lectorer_id).groups.size(); i++) {
            groupsBox.getItems().add(Main.db.lecturers.get(Main.lectorer_id).groups.get(i));
        }

        for (int i = 0; i < Main.db.lecturers.get(Main.lectorer_id).subjects.size(); i++) {
            subjectsBox.getItems().add(Main.db.lecturers.get(Main.lectorer_id).subjects.get(i));
        }
    }
}
