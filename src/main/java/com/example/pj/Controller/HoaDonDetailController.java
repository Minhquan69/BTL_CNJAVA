package com.example.pj.Controller;

import com.example.pj.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.pj.Controller.HoaDonGioController.detail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoaDonDetailController implements Initializable {

    @FXML
    private VBox vBox;

    @FXML
    private Label idHoaDon;

    @FXML
    private Button quayLaiButton;

    @FXML
    private ScrollPane scrollPane;

    private List<Item> ds = detail.getDs();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idHoaDon.setText(String.valueOf(detail.getId()));
        try {
            for(Item item : ds) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/itemHoaDon.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemHoaDonController itemHoaDonController = fxmlLoader.getController();
                itemHoaDonController.setData(item);

                vBox.getChildren().add(anchorPane);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


        //SỰ KIỆN BUTTON QUAY LẠI
    public void onButtonQuayLai() throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/hoaDon.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) quayLaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}