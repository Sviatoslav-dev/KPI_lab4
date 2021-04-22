package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static DataBase db;

    public Main() throws IOException, ClassNotFoundException {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        db = new DataBase();

        for (int i = 0; i < db.accaunts.size(); i++) {
            System.out.println(db.accaunts.get(i).username + ", " + db.accaunts.get(i).type);
        }
        System.out.println();

        for (int i = 0; i < db.students.size(); i++) {
            System.out.println(db.students.get(i).username);
            System.out.println(db.students.get(i).name);
            System.out.println(db.students.get(i).group);
            for (int j = 0; j < db.students.get(i).subjects.size(); j++) {
                System.out.print(db.students.get(i).subjects.get(j).name + ", ");
                if (db.students.get(i).subjects.get(j).subject_marks != null){
                    for (int k = 0; k < db.students.get(i).subjects.get(j).subject_marks.size(); k++){
                        System.out.print(db.students.get(i).subjects.get(j).subject_marks.get(k) + ", ");
                    }
                }

                System.out.println();
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < db.lecturers.size(); i++) {
            System.out.println(db.lecturers.get(i).name);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
