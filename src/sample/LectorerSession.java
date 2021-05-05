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

public class LectorerSession {

    @FXML
    private Button goBack;

    @FXML
    private Button update;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private ChoiceBox<String> groupsBox;

    @FXML
    private ChoiceBox<String> subjectsBox;

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

        update.setOnAction(event -> {
            clear_scrollPane ();
            input_scrollPane ();
        });
    }

    void input_scrollPane () {
        subject = subjectsBox.getValue();

        int size_Y = 50;
        int sub_id = -1;

        names = new ArrayList<>();
        add_buttons = new ArrayList<>();
        main_session = new ArrayList<>();
        first_dopka = new ArrayList<>();
        second_dopka = new ArrayList<>();

        ArrayList<Integer> students_id;

        students_id = Main.db.get_students_by_group_and_subject(groupsBox.getValue(), subjectsBox.getValue());

        input_column_names();

        for (Integer integer : students_id) {

            for (int j = 0; j < Main.db.students.get(integer).getSubjects().size(); j++) {
                if (Main.db.students.get(integer).getSubjects().get(j).getName().equals(subject)) {
                    sub_id = j;
                    AddMark.sub_id = sub_id;
                }
            }

            input_table (integer, size_Y, sub_id);

            scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
            size_Y += 50;
        }
    }

    void input_column_names () {
        Text names_text = new Text();
        names_text.setText("Прізвище та імʼя");
        names_text.setX(35);
        names_text.setY(15);
        names_text.setStroke(Color.BLACK);

        Text session_text = new Text();
        session_text.setText("Сесія");
        session_text.setX(240);
        session_text.setY(15);
        session_text.setStroke(Color.BLACK);

        Text first_dopka_text = new Text();
        first_dopka_text.setText("Перша перездача");
        first_dopka_text.setX(298);
        first_dopka_text.setY(15);
        first_dopka_text.setStroke(Color.BLACK);

        Text second_dopka_text = new Text();
        second_dopka_text.setText("Дрyга перездача");
        second_dopka_text.setX(413);
        second_dopka_text.setY(15);
        second_dopka_text.setStroke(Color.BLACK);

        scrollPane.getChildren().add(names_text);
        scrollPane.getChildren().add(session_text);
        scrollPane.getChildren().add(first_dopka_text);
        scrollPane.getChildren().add(second_dopka_text);
    }

    void input_table (int integer, int size_Y, int sub_id) {
        names.add(new Text());
        names.get(names.size() - 1).setY(size_Y);
        names.get(names.size() - 1).setX(10);
        names.get(names.size() - 1).setText(Main.db.students.get(integer).getName());

        main_session.add(new Text());
        main_session.get(main_session.size() - 1).setY(size_Y);
        main_session.get(main_session.size() - 1).setX(250);

        if (Main.db.students.get(integer).getSubjects().get(sub_id).getSession() > 0)
            main_session.get(main_session.size() - 1).setText(Float.toString(Main.db.students.get(integer).getSubjects().get(sub_id).getSession()));
        else
            main_session.get(main_session.size() - 1).setText("-");


        first_dopka.add(new Text());
        first_dopka.get(first_dopka.size() - 1).setY(size_Y);
        first_dopka.get(first_dopka.size() - 1).setX(350);

        if (Main.db.students.get(integer).getSubjects().get(sub_id).getFirstAddSession() > 0)
            first_dopka.get(first_dopka.size() - 1).setText(Float.toString(Main.db.students.get(integer).getSubjects().get(sub_id).getFirstAddSession()));
        else
            first_dopka.get(first_dopka.size() - 1).setText("-");


        second_dopka.add(new Text());
        second_dopka.get(second_dopka.size() - 1).setY(size_Y);
        second_dopka.get(second_dopka.size() - 1).setX(450);
        if (Main.db.students.get(integer).getSubjects().get(sub_id).getSecondAddSession() > 0)
            second_dopka.get(second_dopka.size() - 1).setText(Float.toString(Main.db.students.get(integer).getSubjects().get(sub_id).getSecondAddSession()));
        else
            second_dopka.get(second_dopka.size() - 1).setText("-");

        if (Main.db.students.get(integer).getSubjects().get(sub_id).getSecondAddSession() <= 0 && Main.db.students.get(integer).getSubjects().get(sub_id).getSession() < 60 && Main.db.students.get(integer).getSubjects().get(sub_id).getFirstAddSession() < 60) {
            add_buttons.add(new Button());
            add_buttons.get(add_buttons.size() - 1).setLayoutX(550);
            add_buttons.get(add_buttons.size() - 1).setLayoutY(size_Y - 20);
            add_buttons.get(add_buttons.size() - 1).setPrefHeight(15);
            add_buttons.get(add_buttons.size() - 1).setPrefWidth(15);
            add_buttons.get(add_buttons.size() - 1).setText("+");

            int student_id = integer;
            add_buttons.get(add_buttons.size() - 1).setOnAction(event -> add_mark(student_id));

            scrollPane.getChildren().add(add_buttons.get(add_buttons.size() - 1));
        }

        scrollPane.getChildren().add(names.get(names.size() - 1));
        scrollPane.getChildren().add(main_session.get(main_session.size() - 1));
        scrollPane.getChildren().add(first_dopka.get(first_dopka.size() - 1));
        scrollPane.getChildren().add(second_dopka.get(second_dopka.size() - 1));
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
        newWindow.setTitle(Main.db.students.get(student_id).getName());
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
