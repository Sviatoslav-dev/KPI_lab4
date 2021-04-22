package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentSession {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goBack;

    @FXML
    private GridPane gridpane;

    @FXML
    void initialize() {

        Text text1 = new Text("text1");
        Text text2 = new Text("text2");
        Text text3 = new Text("text3");
        Text text4 = new Text("text4");
        Text text5 = new Text("text5");
        Text text6 = new Text("text6");
        Text text7 = new Text("text7");
        Text text8 = new Text("text8");
        Text text9 = new Text("text9");

        gridpane.setHgap(3);
        gridpane.setVgap(3);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.RIGHT);
        gridpane.getColumnConstraints().add(column1);

        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.LEFT);
        gridpane.getColumnConstraints().add(column2);

        gridpane.add(text1, 0, 0);
        gridpane.add(text2, 1, 0);
        gridpane.add(text3, 0, 1);
        gridpane.add(text4, 1, 1);
        gridpane.add(text5, 0, 2);
        gridpane.add(text6, 1, 2);
        gridpane.add(text4, 2, 0);
        gridpane.add(text5, 2, 1);
        gridpane.add(text6, 2, 2);


//        gridpane.getColumnConstraints().add(new ColumnConstraints(300)); // column 0 is 100 wide
//        gridpane.getColumnConstraints().add(new ColumnConstraints(300)); // column 1 is 200 wide


        goBack.setOnAction(event -> {
            goBack.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StudentMenu.fxml"));
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
}
