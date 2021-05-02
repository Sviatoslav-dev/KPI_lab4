package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DecanatSession {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goBack;

    @FXML
    private AnchorPane scrollPane;

    @FXML
    private TextField NameField;

    @FXML
    private Button Search;

    public static String name;

    private ArrayList<Text> names;
    private ArrayList<Text> status;
    private ArrayList<Text> main_session;
    private ArrayList<Text> first_dopka;
    private ArrayList<Text> second_dopka;

    @FXML
    void initialize() {
        //input_scrollPane ();
        Search.setOnAction(event -> {
            input_scrollPane();
        });

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

        ArrayList<Subject> subjects = Main.db.students.get(st_id).subjects;

        if (st_id == -1) {
            Text names_text = new Text();
            names_text.setText("Студента не знайдено");
            names_text.setX(50);
            names_text.setY(15);
        } else {

            int size_Y = 50;
            //int sub_id = -1;

            names = new ArrayList<>();
            status = new ArrayList<>();
            main_session = new ArrayList<>();
            first_dopka = new ArrayList<>();
            second_dopka = new ArrayList<>();

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
            first_dopka_text.setText("Допка");
            first_dopka_text.setX(250);
            first_dopka_text.setY(15);
            first_dopka_text.setStroke(Color.BLUE);

            Text second_dopka_text = new Text();
            second_dopka_text.setText("Дрyга допка");
            second_dopka_text.setX(350);
            second_dopka_text.setY(15);
            second_dopka_text.setStroke(Color.BLUE);

            scrollPane.getChildren().add(names_text);
            scrollPane.getChildren().add(session_text);
            scrollPane.getChildren().add(first_dopka_text);
            scrollPane.getChildren().add(second_dopka_text);

            for (int i = 0; i < subjects.size(); i++) {
                //m += Main.db.students.get(students_id.get(i)).name + " - ";
                //System.out.println(Main.db.students.get(i).name);

                names.add(new Text());
                names.get(names.size() - 1).setY(size_Y);
                names.get(names.size() - 1).setX(10);
                names.get(names.size() - 1).setText(subjects.get(i).name);


                main_session.add(new Text());
                main_session.get(main_session.size() - 1).setY(size_Y);
                main_session.get(main_session.size() - 1).setX(150);

                if (subjects.get(i).session != -1)
                    main_session.get(main_session.size() - 1).setText(Float.toString(subjects.get(i).session));
                else
                    main_session.get(main_session.size() - 1).setText("-");


                first_dopka.add(new Text());
                first_dopka.get(first_dopka.size() - 1).setY(size_Y);
                first_dopka.get(first_dopka.size() - 1).setX(250);

                if (subjects.get(i).first_dopka != -1)
                    first_dopka.get(first_dopka.size() - 1).setText(Float.toString(subjects.get(i).first_dopka));
                else
                    first_dopka.get(first_dopka.size() - 1).setText("-");


                second_dopka.add(new Text());
                second_dopka.get(second_dopka.size() - 1).setY(size_Y);
                second_dopka.get(second_dopka.size() - 1).setX(350);
                if (subjects.get(i).second_dopka != -1)
                    second_dopka.get(second_dopka.size() - 1).setText(Float.toString(subjects.get(i).second_dopka));
                else
                    second_dopka.get(second_dopka.size() - 1).setText("-");

                status.add(new Text());
                status.get(status.size() - 1).setY(size_Y);
                status.get(status.size() - 1).setX(450);

                if (subjects.get(i).session <= 0) {
                    status.get(status.size() - 1).setText("Не здавав");
                } else if (subjects.get(i).session >= 60) {
                    status.get(status.size() - 1).setText("Здав");
                } else if (subjects.get(i).first_dopka <= 0) {
                    status.get(status.size() - 1).setText("Перездача");
                } else if (subjects.get(i).first_dopka >= 60) {
                    status.get(status.size() - 1).setText("Здав");
                } else if (subjects.get(i).second_dopka <= 0) {
                    status.get(status.size() - 1).setText("Перездача");
                } else if (subjects.get(i).second_dopka >= 60) {
                    status.get(status.size() - 1).setText("Здав");
                } else {
                    status.get(status.size() - 1).setText("Відрахування");
                }

                scrollPane.getChildren().add(names.get(names.size() - 1));
                scrollPane.getChildren().add(main_session.get(main_session.size() - 1));
                scrollPane.getChildren().add(first_dopka.get(first_dopka.size() - 1));
                scrollPane.getChildren().add(second_dopka.get(second_dopka.size() - 1));
                scrollPane.getChildren().add(status.get(status.size() - 1));

                scrollPane.setMinHeight(scrollPane.getMaxHeight() + 50);
                size_Y += 50;
            }
        }
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
