package sample;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DecanatTransfer {
    @FXML
    private Button goBack;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField NameField;

    @FXML
    private Button Search;

    private TextField Group;
    private Button transfer;
    private Text text;

    @FXML
    void initialize() {
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
        String name = NameField.getText();
        clear_Pane ();

        int st_id = Main.db.find_student_by_name(name);

        if (st_id == -1) {
            text = new Text();
            text.setText("Студента не знайдено");
            text.setX(50);
            text.setY(15);
            pane.getChildren().add(text);
            Shake SearchAnim = new Shake(Search);
            SearchAnim.play();
        } else {
            text = new Text();
            text.setText(Main.db.students.get(st_id).getName() + " - " + Main.db.students.get(st_id).getGroup());
            text.setX(50);
            text.setY(15);
            pane.getChildren().add(text);

            Group = new TextField();
            Group.setLayoutX(200);
            Group.setLayoutY(100);
            Group.setPromptText("Перевести в:");
            pane.getChildren().add(Group);

            transfer = new Button();
            transfer.setLayoutX(250);
            transfer.setLayoutY(150);
            transfer.setText("Перевести");
            transfer.setOnAction(event-> {
                try {
                    Transfer(st_id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            pane.getChildren().add(transfer);
        }
    }

    void clear_Pane () {
        Group = null;
        transfer = null;
        text = null;

        pane.getChildren().clear();
    }

    void Transfer (int st_id) throws IOException {
        Main.db.transfer_in_other_group(st_id, Group.getText());
        Main.db.save_students();

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
    }
}
