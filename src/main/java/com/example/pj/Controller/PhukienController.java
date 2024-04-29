package com.example.pj.Controller;

import com.example.pj.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PhukienController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Button quayLaiButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button timKiemButton;

    @FXML
    private TextField timKiemField;
    //PHƯƠNG THỨC TRẢ VỀ DANH SÁCH CHỨA CÁC ITEM
    private static final String FILE_PATH="E:\\btllll\\project-btl-oop-master\\src\\main\\java\\com\\example\\pj\\itemPhuKien.txt";
    public List<Item> taoDS() {
        List<Item> ls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String imagePath = parts[0];
                String name = parts[1];
                int price = Integer.parseInt(parts[2].trim());
                String ratingImagePath = parts[3];
                String id = parts[4];
                ls.add(new Item(imagePath, name, price, ratingImagePath, id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        int column = 0;
        int row = 1;

        List<Item> phuKien = new ArrayList<>(taoDS());

        try {
            for (Item item : phuKien) {
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
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }

    // XỬ LÝ SỰ KIỆN BUTTON QUAY LẠI
    public void onButtonQuayLai() throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) quayLaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
