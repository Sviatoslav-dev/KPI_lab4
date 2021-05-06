package sample;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DecanatSession {

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private AnchorPane Pane;

    @FXML
    private TextField NameField;

    @FXML
    private Button Search;

    public static String name;
    private boolean deduction;

    private ArrayList<Text> names;
    private ArrayList<Text> status;
    private ArrayList<Text> main_session;
    private ArrayList<Text> first_dopka;
    private ArrayList<Text> second_dopka;

    @FXML
    void initialize() {
        deduction = false;
        Search.setOnAction(event -> input_scrollPane());

        goBack.setOnAction(event -> {
            goBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxmls/decanat_menu.fxml"));
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
        name = NameField.getText();

        clear_scrollPane ();

        int st_id = Main.db.find_student_by_name(name);

        if (st_id == -1) {
            Text names_text = new Text();
            names_text.setText("Студента не знайдено");
            names_text.setX(50);
            names_text.setY(15);
            scrollPane.getChildren().add(names_text);
            Shake SearchAnim = new Shake(Search);
            SearchAnim.play();
        } else {
            ArrayList<Subject> subjects = Main.db.students.get(st_id).getSubjects();

            input_column_names();

            int size_Y = 50;

            names = new ArrayList<>();
            status = new ArrayList<>();
            main_session = new ArrayList<>();
            first_dopka = new ArrayList<>();
            second_dopka = new ArrayList<>();

            for (Subject subject : subjects) {

                input_line (size_Y, subject);

                scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
                size_Y += 70;
            }

            if (deduction)
                crateDeleteButton (st_id);
        }
    }

    void input_column_names () {
        Text names_text = new Text();
        names_text.setText("Предмет");
        names_text.setX(10);
        names_text.setY(15);
        names_text.setStroke(Color.BLUE);

        Text session_text = new Text();
        session_text.setText("Сесія");
        session_text.setX(150);
        session_text.setY(15);
        session_text.setStroke(Color.BLUE);

        Text first_dopka_text = new Text();
        first_dopka_text.setText("  Перша \n перездача");
        first_dopka_text.setX(230);
        first_dopka_text.setY(15);
        first_dopka_text.setStroke(Color.BLUE);

        Text second_dopka_text = new Text();
        second_dopka_text.setText("  Дрyга \n перездача");
        second_dopka_text.setX(330);
        second_dopka_text.setY(15);
        second_dopka_text.setStroke(Color.BLUE);

        scrollPane.getChildren().add(names_text);
        scrollPane.getChildren().add(session_text);
        scrollPane.getChildren().add(first_dopka_text);
        scrollPane.getChildren().add(second_dopka_text);
    }

    void input_line (int size_Y, Subject subject) {
        names.add(new Text());
        names.get(names.size() - 1).setY(size_Y);
        names.get(names.size() - 1).setX(10);
        names.get(names.size() - 1).setText(subject.getName());
        names.get(names.size() - 1).setWrappingWidth(100);

        main_session.add(new Text());
        main_session.get(main_session.size() - 1).setY(size_Y);
        main_session.get(main_session.size() - 1).setX(150);

        if (subject.getSession()  > 0)
            main_session.get(main_session.size() - 1).setText(Float.toString(subject.getSession()));
        else
            main_session.get(main_session.size() - 1).setText("-");

        first_dopka.add(new Text());
        first_dopka.get(first_dopka.size() - 1).setY(size_Y);
        first_dopka.get(first_dopka.size() - 1).setX(250);

        if (subject.getFirstAddSession()  > 0)
            first_dopka.get(first_dopka.size() - 1).setText(Float.toString(subject.getFirstAddSession()));
        else
            first_dopka.get(first_dopka.size() - 1).setText("-");

        second_dopka.add(new Text());
        second_dopka.get(second_dopka.size() - 1).setY(size_Y);
        second_dopka.get(second_dopka.size() - 1).setX(350);

        if (subject.getSecondAddSession()  > 0)
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
            deduction = true;
            status.get(status.size() - 1).setText("Відрахування");
        }

        scrollPane.getChildren().add(names.get(names.size() - 1));
        scrollPane.getChildren().add(main_session.get(main_session.size() - 1));
        scrollPane.getChildren().add(first_dopka.get(first_dopka.size() - 1));
        scrollPane.getChildren().add(second_dopka.get(second_dopka.size() - 1));
        scrollPane.getChildren().add(status.get(status.size() - 1));
    }

    void crateDeleteButton (int st_id) {
        Button deleleteBut = new Button();
        deleleteBut.setText("Видалити акаунт");
        deleleteBut.setLayoutX(470);
        deleleteBut.setLayoutY(340);

        deleleteBut.setOnAction(event -> {
            try {
                Main.db.DeleteStudent(st_id);
            } catch (IOException e) {
                e.printStackTrace();
            }

            goBack.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("fxmls/decanat_menu.fxml"));
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

        Pane.getChildren().add(deleleteBut);
    }

    void clear_scrollPane () {
        if (names != null)
            names.clear();

        if (main_session != null)
            main_session.clear();

        if (first_dopka != null)
            first_dopka.clear();

        if (second_dopka != null)
            second_dopka.clear();

        if (status != null)
            status.clear();

        scrollPane.getChildren().clear();
    }
}
