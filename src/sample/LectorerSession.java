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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LectorerSession {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private ChoiceBox groupsBox;

    @FXML
    private ChoiceBox subjectsBox;

    public static String subject;

    private ArrayList<Text> names;
    private ArrayList<Button> add_buttons;
    private ArrayList<Text> main_session;
    private ArrayList<Text> first_dopka;
    private ArrayList<Text> second_dopka;

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

        goBack.setOnAction(event -> {
            goBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxmls/Lecturer.fxml"));
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
        //String m = "";

        int size_Y = 50;
        int sub_id = -1;

        names = new ArrayList<>();
        add_buttons = new ArrayList<>();
        main_session = new ArrayList<>();
        first_dopka = new ArrayList<>();
        second_dopka = new ArrayList<>();

        ArrayList<Integer> students_id;

        students_id = Main.db.get_students_by_group_and_subject((String) groupsBox.getValue(), (String) subjectsBox.getValue());

        Text names_text = new Text();
        names_text.setText("Ім'я");
        names_text.setX(50);
        names_text.setY(15);
        names_text.setStroke(Color.BLUE);

        Text session_text = new Text();
        session_text.setText("Сесія");
        session_text.setX(250);
        session_text.setY(15);
        session_text.setStroke(Color.BLUE);

        Text first_dopka_text = new Text();
        first_dopka_text.setText("Допка");
        first_dopka_text.setX(350);
        first_dopka_text.setY(15);
        first_dopka_text.setStroke(Color.BLUE);

        Text second_dopka_text = new Text();
        second_dopka_text.setText("Дрyга допка");
        second_dopka_text.setX(450);
        second_dopka_text.setY(15);
        second_dopka_text.setStroke(Color.BLUE);

        scrollPane.getChildren().add(names_text);
        scrollPane.getChildren().add(session_text);
        scrollPane.getChildren().add(first_dopka_text);
        scrollPane.getChildren().add(second_dopka_text);


        for (int i = 0; i < students_id.size(); i++) {
            //m += Main.db.students.get(students_id.get(i)).name + " - ";
            //System.out.println(Main.db.students.get(i).name);

            for (int j = 0; j < Main.db.students.get(students_id.get(i)).subjects.size(); j++) {
                if (Main.db.students.get(students_id.get(i)).subjects.get(j).name.equals(subject)) {
                    sub_id = j;
                    AddMark.sub_id = sub_id;
                    //if (Main.db.students.get(students_id.get(i)).subjects.get(j).subject_marks != null) {
                        //for (int p = 0; p < Main.db.students.get(students_id.get(i)).subjects.get(j).subject_marks.size(); p++) {
                            //m += Main.db.students.get(students_id.get(i)).subjects.get(j).subject_marks.get(p).toString() + ", ";
                        //}
                    //}
                }
            }

            names.add(new Text());
            names.get(names.size() - 1).setY(size_Y);
            names.get(names.size() - 1).setX(10);
            names.get(names.size() - 1).setText(Main.db.students.get(students_id.get(i)).name);

            main_session.add(new Text());
            main_session.get(main_session.size() - 1).setY(size_Y);
            main_session.get(main_session.size() - 1).setX(250);

            if (Main.db.students.get(students_id.get(i)).subjects.get(sub_id).session != -1)
                main_session.get(main_session.size() - 1).setText(Float.toString(Main.db.students.get(students_id.get(i)).subjects.get(sub_id).session));
            else
                main_session.get(main_session.size() - 1).setText("-");


            first_dopka.add(new Text());
            first_dopka.get(first_dopka.size() - 1).setY(size_Y);
            first_dopka.get(first_dopka.size() - 1).setX(350);
            if (Main.db.students.get(students_id.get(i)).subjects.get(sub_id).first_dopka != -1)

                first_dopka.get(first_dopka.size() - 1).setText(Float.toString(Main.db.students.get(students_id.get(i)).subjects.get(sub_id).first_dopka));
            else
                first_dopka.get(first_dopka.size() - 1).setText("-");


            second_dopka.add(new Text());
            second_dopka.get(second_dopka.size() - 1).setY(size_Y);
            second_dopka.get(second_dopka.size() - 1).setX(450);
            if (Main.db.students.get(students_id.get(i)).subjects.get(sub_id).second_dopka != -1)
                second_dopka.get(second_dopka.size() - 1).setText(Float.toString(Main.db.students.get(students_id.get(i)).subjects.get(sub_id).second_dopka));
            else
                second_dopka.get(second_dopka.size() - 1).setText("-");

            if (Main.db.students.get(students_id.get(i)).subjects.get(sub_id).second_dopka <= 0 && Main.db.students.get(students_id.get(i)).subjects.get(sub_id).session < 60 && Main.db.students.get(students_id.get(i)).subjects.get(sub_id).first_dopka < 60) {
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

                scrollPane.getChildren().add(add_buttons.get(add_buttons.size() - 1));
            }

            scrollPane.getChildren().add(names.get(names.size() - 1));
            scrollPane.getChildren().add(main_session.get(main_session.size() - 1));
            scrollPane.getChildren().add(first_dopka.get(first_dopka.size() - 1));
            scrollPane.getChildren().add(second_dopka.get(second_dopka.size() - 1));


            scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
            size_Y += 50;

            //m = "";
        }
    }

    void add_mark (int student_id) {
        System.out.println(student_id);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxmls/add_mark_session.fxml"));
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

        AddMarkSession.st_id = student_id;
    }

    void clear_scrollPane () {
        names.clear();
        main_session.clear();
        first_dopka.clear();
        second_dopka.clear();
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
