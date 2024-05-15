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
import static com.example.pj.Controller.HomeController.search;

public class hienthi implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button quayLaiButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (search == null || search.isBlank()) {
            return;
        }

        // Clear previous search results
        gridPane.getChildren().clear();
        System.out.println("gridPane size after clear: " + gridPane.getChildren().size());
        System.out.println("itemtk content size: " + itemtk.size());

        int column = 0;
        int row = 1;

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
                System.out.println("GridPane children size: " + gridPane.getChildren().size());
                GridPane.setMargin(anchorPane, new Insets(10));
            }
            System.out.println("Added items to gridPane, total items: " + gridPane.getChildren().size());
        } catch (IOException e) {
            System.out.println("Lỗi RunTimeException!");
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
