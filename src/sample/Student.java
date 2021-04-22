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

    public Student () {
        subjects = new ArrayList<>();
    }
}

