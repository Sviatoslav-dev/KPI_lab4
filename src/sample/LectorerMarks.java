package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LectorerMarks {

    @FXML
    private Button Save;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private ChoiceBox<String> groupsBox;

    @FXML
    private ChoiceBox<String> subjectsBox;

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
        subject = subjectsBox.getValue();
        StringBuilder m = new StringBuilder();

        int size_Y = 50;
        int sub_id = -1;

        marks = new ArrayList<>();
        add_buttons = new ArrayList<>();
        marks_sum = new ArrayList<>();

        ArrayList<Integer> students_id;

        students_id = Main.db.get_students_by_group_and_subject(groupsBox.getValue(), subjectsBox.getValue());

        input_column_names();

        for (Integer integer : students_id) {
            m.append(Main.db.students.get(integer).getName()).append(" - ");

            for (int j = 0; j < Main.db.students.get(integer).getSubjects().size(); j++) {
                if (Main.db.students.get(integer).getSubjects().get(j).getName().equals(subject)) {
                    sub_id = j;
                    AddMark.sub_id = sub_id;
                    if (Main.db.students.get(integer).getSubjects().get(j).getSubjectMarks() != null) {
                        for (int p = 0; p < Main.db.students.get(integer).getSubjects().get(j).getSubjectMarks().size(); p++) {
                            m.append(Main.db.students.get(integer).getSubjects().get(j).getSubjectMarks().get(p).toString()).append(", ");
                        }
                    }
                }
            }

            input_table(size_Y, m, integer, sub_id);

            size_Y += 50;

            m = new StringBuilder();
        }
    }

    void input_column_names () {
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
    }

    void input_table (int size_Y, StringBuilder m, int student_id, int sub_id) {
        marks.add(new Text());
        marks.get(marks.size() - 1).setY(size_Y);
        marks.get(marks.size() - 1).setX(10);
        marks.get(marks.size() - 1).setText(m.toString());

        marks_sum.add(new Text());
        marks_sum.get(marks_sum.size() - 1).setY(size_Y);
        marks_sum.get(marks_sum.size() - 1).setX(450);
        marks_sum.get(marks_sum.size() - 1).setText(Float.toString(Main.db.students.get(student_id).getSubjects().get(sub_id).getSum()));

        add_buttons.add(new Button());
        add_buttons.get(add_buttons.size() - 1).setLayoutX(550);
        add_buttons.get(add_buttons.size() - 1).setLayoutY(size_Y - 20);
        add_buttons.get(add_buttons.size() - 1).setPrefHeight(15);
        add_buttons.get(add_buttons.size() - 1).setPrefWidth(15);
        add_buttons.get(add_buttons.size() - 1).setText("+");

        add_buttons.get(add_buttons.size() - 1).setOnAction(event -> add_mark(student_id));

        scrollPane.getChildren().add(marks.get(marks.size() - 1));
        scrollPane.getChildren().add(marks_sum.get(marks_sum.size() - 1));
        scrollPane.getChildren().add(add_buttons.get(add_buttons.size() - 1));

        scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
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
        groupsBox.setValue(Main.db.lecturers.get(Main.lectorer_id).getGroups().get(0));
        subjectsBox.setValue(Main.db.lecturers.get(Main.lectorer_id).getSubjects().get(0));

        for (int i = 0; i < Main.db.lecturers.get(Main.lectorer_id).getGroups().size(); i++) {
            groupsBox.getItems().add(Main.db.lecturers.get(Main.lectorer_id).getGroups().get(i));
        }

        for (int i = 0; i < Main.db.lecturers.get(Main.lectorer_id).getSubjects().size(); i++) {
            subjectsBox.getItems().add(Main.db.lecturers.get(Main.lectorer_id).getSubjects().get(i));
        }
    }
}
