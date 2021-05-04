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

public class StudentSession {

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane scrollPane;

    ArrayList<Text> names;
    ArrayList<Text> status;
    ArrayList<Text> main_session;
    ArrayList<Text> first_dopka;
    ArrayList<Text> second_dopka;

    @FXML
    void initialize() {
        input_scrollPane ();

        goBack.setOnAction(event -> {
            goBack.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("student_menu.fxml"));
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
        int st_id = Main.student_id;

        ArrayList<Subject> subjects = Main.db.students.get(st_id).getSubjects();


        int size_Y = 50;

        names = new ArrayList<>();
        status = new ArrayList<>();
        main_session = new ArrayList<>();
        first_dopka = new ArrayList<>();
        second_dopka = new ArrayList<>();

        input_column_names();

        for (Subject subject : subjects) {

            input_table (size_Y, subject);

            scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
            size_Y += 50;
        }
    }

    void input_column_names () {
        Text names_text = new Text();
        names_text.setText("Предмет");
        names_text.setX(50);
        names_text.setY(15);
        names_text.setStroke(Color.BLUE);

        Text session_text = new Text();
        session_text.setText("Сесія");
        session_text.setX(150);
        session_text.setY(15);
        session_text.setStroke(Color.BLUE);

        Text first_dopka_text = new Text();
        first_dopka_text.setText("Перша перездача");
        first_dopka_text.setX(250);
        first_dopka_text.setY(15);
        first_dopka_text.setStroke(Color.BLUE);

        Text second_dopka_text = new Text();
        second_dopka_text.setText("Дрyга перездача");
        second_dopka_text.setX(350);
        second_dopka_text.setY(15);
        second_dopka_text.setStroke(Color.BLUE);

        scrollPane.getChildren().add(names_text);
        scrollPane.getChildren().add(session_text);
        scrollPane.getChildren().add(first_dopka_text);
        scrollPane.getChildren().add(second_dopka_text);
    }

    void input_table (int size_Y, Subject subject) {
        names.add(new Text());
        names.get(names.size() - 1).setY(size_Y);
        names.get(names.size() - 1).setX(10);
        names.get(names.size() - 1).setText(subject.getName());


        main_session.add(new Text());
        main_session.get(main_session.size() - 1).setY(size_Y);
        main_session.get(main_session.size() - 1).setX(150);

        if (subject.getSession() != -1)
            main_session.get(main_session.size() - 1).setText(Float.toString(subject.getSession()));
        else
            main_session.get(main_session.size() - 1).setText("-");


        first_dopka.add(new Text());
        first_dopka.get(first_dopka.size() - 1).setY(size_Y);
        first_dopka.get(first_dopka.size() - 1).setX(250);

        if (subject.getFirstAddSession() != -1)
            first_dopka.get(first_dopka.size() - 1).setText(Float.toString(subject.getFirstAddSession()));
        else
            first_dopka.get(first_dopka.size() - 1).setText("-");


        second_dopka.add(new Text());
        second_dopka.get(second_dopka.size() - 1).setY(size_Y);
        second_dopka.get(second_dopka.size() - 1).setX(350);
        if (subject.getSecondAddSession() != -1)
            second_dopka.get(second_dopka.size() - 1).setText(Float.toString(subject.getSecondAddSession()));
        else
            second_dopka.get(second_dopka.size() - 1).setText("-");

        status.add(new Text());
        status.get(status.size() - 1).setY(size_Y);
        status.get(status.size() - 1).setX(450);

        if (subject.getSession() <= 0) {
            status.get(status.size() - 1).setText("Не здавав");
        } else if (subject.getSession() >= 60) {
            status.get(status.size() - 1).setText("Здав");
        } else if (subject.getFirstAddSession() <= 0) {
            status.get(status.size() - 1).setText("Перездача");
        } else if (subject.getFirstAddSession() >= 60) {
            status.get(status.size() - 1).setText("Здав");
        } else if (subject.getSecondAddSession() <= 0) {
            status.get(status.size() - 1).setText("Перездача");
        } else if (subject.getSecondAddSession() >= 60) {
            status.get(status.size() - 1).setText("Здав");
        } else {
            status.get(status.size() - 1).setText("Відрахування");
        }

        scrollPane.getChildren().add(names.get(names.size() - 1));
        scrollPane.getChildren().add(main_session.get(main_session.size() - 1));
        scrollPane.getChildren().add(first_dopka.get(first_dopka.size() - 1));
        scrollPane.getChildren().add(second_dopka.get(second_dopka.size() - 1));
        scrollPane.getChildren().add(status.get(status.size() - 1));
    }
}
