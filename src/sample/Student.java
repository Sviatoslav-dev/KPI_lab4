package sample;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class Student implements Serializable {

    String username;
    String name;
    String group;
    ArrayList<Subject> subjects;

    public class Subject {
        String name;
        ArrayList<Float> subject_marks;
        float sum;
        float session;
        float first_dopka;
        float second_dopka;

        public void sum_marks() {
            sum = 0;
            for (int i = 0; i < subject_marks.size(); i++) {
                sum += subject_marks.get(i);
            }
        }
    }

//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private Button Exit;
//
//    @FXML
//    private Button Marks;
//
//    @FXML
//    private Button Session;
//
//    @FXML
//    void initialize() {
//        Exit.setOnAction(event -> {
//            Exit.getScene().getWindow().hide();
//
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("sample.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Parent root = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.show();
//
//        });
//    }

}

