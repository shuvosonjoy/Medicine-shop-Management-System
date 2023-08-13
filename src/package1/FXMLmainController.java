package package1;

import java.sql.ResultSet;
import java.sql.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLmainController implements Initializable {

    private PreparedStatement prepare;
    private Connection connect;
    private ResultSet result;

    @FXML
    private Button close;

    @FXML
    private Button loginbtn;

    @FXML
    private AnchorPane mainform;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    private double x = 0;
    private double y = 0;

    public void loginAdmin() {
        String sql = "Select * from admin where username=? and password=?";
        connect = database.connectDb();

        try {
            prepare = (PreparedStatement) connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();
            Alert alert;
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("fill empty field");
                alert.showAndWait();
            } else {

                if (result.next()) {
                    getData.username = username.getText();
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Successfull");
                    alert.showAndWait();

                    loginbtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                        stage.setOpacity(.5);
                    });

                    root.setOnMouseReleased((MouseEvent event) -> {
                        stage.setOpacity(1);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username or Password");
                    alert.showAndWait();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
