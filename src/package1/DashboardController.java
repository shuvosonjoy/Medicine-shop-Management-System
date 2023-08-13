package package1;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class DashboardController implements Initializable {

    @FXML
    private Button Add_medicine;

    @FXML
    private TextField Addmedicine_ID;

    @FXML
    private ComboBox<?> Addmedicine_Status;

    @FXML
    private TextField Addmedicine_brand_name;

    @FXML
    private Button Addmedicine_btn_add;

    @FXML
    private Button Addmedicine_btn_clear;

    @FXML
    private Button Addmedicine_btn_delete;

    @FXML
    private Button Addmedicine_btn_update;

    @FXML
    private AnchorPane Addmedicine_form;

    @FXML
    private ImageView Addmedicine_image_viewer;

    @FXML
    private Button Addmedicine_import_btn;

    @FXML
    private TextField Addmedicine_price;

    @FXML
    private TextField Addmedicine_product_name;

    @FXML
    private TextField Addmedicine_search;

    @FXML
    private TableColumn<medicineData, String> Addmedicine_table_ProductName;

    @FXML
    private TableColumn<medicineData, String> Addmedicine_table_Type;

    @FXML
    private TableColumn<medicineData, String> Addmedicine_table_brandName;

    @FXML
    private TableColumn<medicineData, String> Addmedicine_table_date;

    @FXML
    private TableColumn<medicineData, String> Addmedicine_table_medicine_id;

    @FXML
    private TableColumn<medicineData, String> Addmedicine_table_price;

    @FXML
    private TableColumn<medicineData, String> Addmedicine_table_status;

    @FXML
    private ComboBox<?> Addmedicine_type;

    @FXML
    private Button Purchase_medicine;

    @FXML
    private Button close;

    @FXML
    private AreaChart<?, ?> dashboard_area_chart;

    @FXML
    private Label dashboard_available_medicine;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_total_customers;

    @FXML
    private Label dashboard_total_income;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button sign_out_btn;

    @FXML
    private Label username;

    @FXML
    private TableView<medicineData> Addmedicine_table_view;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;

    public void addMedicinesAdd() {
        String sql = "INSERT INTO shop(medicine_id, brand, productName, type, status, price, Image, date)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        connect = (Connection) database.connectDb();
        try {
            Alert alert;

            if (Addmedicine_ID.getText().isEmpty()
                    || Addmedicine_brand_name.getText().isEmpty()
                    || Addmedicine_product_name.getText().isEmpty()
                    || Addmedicine_type.getSelectionModel().getSelectedItem() == null
                    || Addmedicine_Status.getSelectionModel().getSelectedItem() == null
                    || Addmedicine_price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("please fill all blank fields");
                alert.showAndWait();

            } else {
                String checkData = "select medicine_id from shop where medicine_id='"
                        + Addmedicine_ID.getText() + "'";

                statement = (Statement) connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Medicint ID: " + Addmedicine_ID.getText() + " already exist!");
                    alert.showAndWait();
                } else {
                    prepare = (PreparedStatement) connect.prepareStatement(sql);

                    prepare.setString(1, Addmedicine_ID.getText());
                    prepare.setString(2, Addmedicine_brand_name.getText());
                    prepare.setString(3, Addmedicine_product_name.getText());
                    prepare.setString(4, (String) Addmedicine_type.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) Addmedicine_Status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, Addmedicine_price.getText());
                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(7, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();
                    addMedicineshowListData();
                }
            }

            /* 
            prepare = (PreparedStatement) connect.prepareStatement(sql);

            prepare.setString(1, Addmedicine_ID.getText());
            prepare.setString(2, Addmedicine_brand_name.getText());
            prepare.setString(3, Addmedicine_product_name.getText());
            prepare.setString(4, (String) Addmedicine_type.getSelectionModel().getSelectedItem());
            prepare.setString(5, (String) Addmedicine_Status.getSelectionModel().getSelectedItem());
            prepare.setString(6, Addmedicine_price.getText());
            String uri = getData.path;
            uri = uri.replace("\\", "\\\\");
            prepare.setString(7,uri);
            
            
            Date date=new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            prepare.setString(8,String.valueOf(sqlDate));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] addMedicineListT = {"Hydrocodone", "Antibiotics", "Metformin"};

    public void addMedicineListType() {
        List<String> list = new ArrayList<>();

        for (String data : addMedicineListT) {
            list.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(list);
        Addmedicine_type.setItems(listData);
    }

    private String[] addMedicineStatus = {"Available", "Not Available"};

    public void addMedicineListStatus() {

        List<String> lists = new ArrayList<>();

        for (String value : addMedicineStatus) {
            lists.add(value);
        }

        ObservableList listData = FXCollections.observableArrayList(lists);
        Addmedicine_Status.setItems(listData);

    }

    public void addMedicineInsertImage() {
        FileChooser open = new FileChooser();
        open.setTitle("import image file ");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            image = new Image(file.toURI().toString(), 102, 122, false, true);
            Addmedicine_image_viewer.setImage(image);
            getData.path = file.getAbsolutePath();
        }
    }

    public ObservableList<medicineData> addMedicineListData() {

        String sql = "SELECT * FROM shop";
        ObservableList<medicineData> listData = FXCollections.observableArrayList();
        connect = (Connection) database.connectDb();
        try {
            prepare = (PreparedStatement) connect.prepareStatement(sql);
            result = prepare.executeQuery();

            medicineData medData;

            while (result.next()) {
                medData = new medicineData(result.getString("medicine_id"),
                        result.getString("brand"),
                        result.getString("productName"),
                        result.getString("type"),
                        result.getString("status"),
                        result.getDouble("price"),
                        result.getString("Image"),
                        result.getDate("date")
                );
                listData.add(medData);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return listData;
    }

    private ObservableList<medicineData> addMedicineList;

    public void addMedicineshowListData() {
        addMedicineList = addMedicineListData();

        Addmedicine_table_medicine_id.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        Addmedicine_table_brandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        Addmedicine_table_ProductName.setCellValueFactory(new PropertyValueFactory<>("Product"));
        Addmedicine_table_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Addmedicine_table_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Addmedicine_table_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        Addmedicine_table_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        Addmedicine_table_view.setItems(addMedicineList);

    }

    public void addMedicineSelect() {

        medicineData medData = Addmedicine_table_view.getSelectionModel().getSelectedItem();
        //  medicineData medData;
        //  medData = Addmedicine_table_view.getSelectionModel().getSelectedIndex();
        int num = Addmedicine_table_view.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        Addmedicine_ID.setText(medData.getMedicineId());
        Addmedicine_brand_name.setText(medData.getBrand());
        Addmedicine_product_name.setText(medData.getProduct());
        // Addmedicine_type.setText(medData.gettype());
        // Addmedicine_Status.setText(medData.getStatus());
        // Addmedicine_Status.setText(medData.getStatus());
        Addmedicine_price.setText(String.valueOf(medData.getPrice()));

        String uri = "file:" + medData.getImage();

        image = new Image(uri, 102, 122, false, true);
        getData.path = medData.getImage();
        
    }

    public void switchform(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            Addmedicine_form.setVisible(false);
        } else if (event.getSource() == Add_medicine) {
            Addmedicine_form.setVisible(true);
            dashboard_form.setVisible(false);
            addMedicineshowListData();
            addMedicineListType();
            addMedicineListStatus();

        } else if (event.getSource() == Purchase_medicine) {
            Addmedicine_form.setVisible(false);
            dashboard_form.setVisible(false);
        }

    }

    public void disp_username() {
        String user = getData.username;

        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
    }

    private double x = 0;
    private double y = 0;

    public void logout() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Sure to logout??");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                sign_out_btn.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));
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

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disp_username();
        addMedicineshowListData();
        addMedicineListType();
        addMedicineListStatus();
    }

}
