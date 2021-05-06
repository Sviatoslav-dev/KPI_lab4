package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static DataBase db;
    public static int lectorer_id;
    public static int student_id;

    public Main() throws IOException, ClassNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/main_menu.fxml"));
        primaryStage.setTitle("Decan's information system");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        db = new DataBase();

        ShowDataBase();
    }

    void ShowDataBase () {
        for (int i = 0; i < db.accaunts.size(); i++) {
            System.out.println(db.accaunts.get(i).getUsername() + " " + db.accaunts.get(i).getPassword());
        }
        System.out.println();

        if (db.students != null) {
            for (int i = 0; i < db.students.size(); i++) {
                System.out.println(db.students.get(i).getUsername());
                System.out.println(db.students.get(i).getName());
                System.out.println(db.students.get(i).getGroup());
                for (int j = 0; j < db.students.get(i).getSubjects().size(); j++) {
                    System.out.print(db.students.get(i).getSubjects().get(j).getName() + ", ");
                    if (db.students.get(i).getSubjects().get(j).getSubjectMarks() != null) {
                        for (int k = 0; k < db.students.get(i).getSubjects().get(j).getSubjectMarks().size(); k++) {
                            System.out.print(db.students.get(i).getSubjects().get(j).getSubjectMarks().get(k) + ", ");
                        }
                    }

                    System.out.println();
                }
                System.out.println();
            }
            System.out.println();
        }

        if (db.lecturers != null) {
            for (int i = 0; i < db.lecturers.size(); i++) {
                System.out.println(db.lecturers.get(i).getName());
                System.out.println(db.lecturers.get(i).getUsername());
                for (int j = 0; j < db.lecturers.get(i).getSubjects().size(); j++) {
                    System.out.println(db.lecturers.get(i).getSubjects().get(j));
                }

                for (int j = 0; j < db.lecturers.get(i).getGroups().size(); j++) {
                    System.out.println(db.lecturers.get(i).getGroups().get(j));
                }

                System.out.println();
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
