package com.example.pj.Controller;

import com.example.pj.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.pj.Controller.HomeController.itemtk;
import static com.example.pj.Controller.PhoneController.itemtkPhone;
import static com.example.pj.Controller.PhukienController.itemtkPhuKien;

import static com.example.pj.Controller.HomeController.search;
import static com.example.pj.Controller.PhoneController.searchh;
import static com.example.pj.Controller.PhukienController.searchhh;
import static com.example.pj.Controller.TabletController.itemtkTablet;
import static com.example.pj.Controller.TabletController.searchhhh;


public class hienthi implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button quayLaiButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        gridPane.getChildren().clear();

        int column = 0;
        int row = 1;
        System.out.println(itemtk.size());
        System.out.println(search);
        if (itemtk.size() > 0) {
            if (search == null || search.isBlank()) {

                return;
            }
            try {
                for (Item item : itemtk) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/fxml/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(item);

                    if (column == 5) {
                        column = 0;
                        ++row;
                    }
                    gridPane.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(10));
                }
                itemtk.clear();
            } catch (IOException e) {
                System.out.println("Lỗi RunTimeException!");
            }
        } else if (itemtkPhone.size() > 0) {

            if (searchh == null || searchh.isBlank()) {
                return;
            }
            try {
                for (Item item : itemtkPhone) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/fxml/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(item);

                    if (column == 5) {
                        column = 0;
                        ++row;
                    }
                    gridPane.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(10));

                }
                itemtkPhone.clear();
            } catch (IOException e) {
                System.out.println("Lỗi RunTimeException!");
            }
        } else if (itemtkPhuKien.size() > 0) {

            if (searchhh == null || searchhh.isBlank()) {
                return;
            }
            try {
                for (Item item : itemtkPhuKien) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/fxml/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(item);

                    if (column == 5) {
                        column = 0;
                        ++row;
                    }
                    gridPane.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(10));

                }
                itemtkPhuKien.clear();
            } catch (IOException e) {
                System.out.println("Lỗi RunTimeException!");
            }
        } else if (itemtkTablet.size() > 0) {

            if (searchhhh == null || searchhhh.isBlank()) {
                return;
            }
            try {
                for (Item item : itemtkTablet) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/fxml/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(item);

                    if (column == 5) {
                        column = 0;
                        ++row;
                    }
                    gridPane.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(10));

                }
                itemtkTablet.clear();
            } catch (IOException e) {
                System.out.println("Lỗi RunTimeException!");
            }
        }
    }

    public void onButtonQuayLai() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) quayLaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
