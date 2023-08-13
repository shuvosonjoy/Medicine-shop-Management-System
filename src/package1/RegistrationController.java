package package1;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author shuvo sonjoy
 */
public class RegistrationController implements Initializable {

    @FXML
    private AnchorPane mainform;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginbtn;
    @FXML
    private Button close;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // nextPage();
    }
    private Connection connect;
    private PreparedStatement prepare;
    private double x = 0;
    private double y = 0;

    public void signup() {

        Alert alert;

        if (username.getText().isEmpty()
                || password.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("please fill all blank fields");
            alert.showAndWait();
        } else {

            try {

                String sql = "INSERT INTO admin(username,password)"
                        + "VALUES(?,?)";
                connect = (Connection) database.connectDb();
                prepare = (PreparedStatement) connect.prepareStatement(sql);
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Account Created");
                alert.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }

            loginbtn.getScene().getWindow().hide();
            nextPage();
        }
    }

    private void nextPage() {

        Parent root;
        try {

            Alert alert;

            root = FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loginAdmin(ActionEvent event) {

    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

}
