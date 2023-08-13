package package1;

import com.mysql.jdbc.Connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUPController implements Initializable {

    @FXML
    private Button login_signup;
    
    @FXML
    private Button login_create;
    
    private double x = 0;
    private double y = 0;

    public void signIn() {
        Parent root;
        try {
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
        login_signup.getScene().getWindow().hide();
    }
    

    public void signup() {
        
        
        
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("registration.fxml"));

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
        login_create.getScene().getWindow().hide();
        
     
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
