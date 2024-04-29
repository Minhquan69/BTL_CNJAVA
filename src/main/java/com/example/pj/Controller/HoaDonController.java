package com.example.pj.Controller;

import com.example.pj.Models.HoaDon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.pj.Controller.GioHangController.dsHoaDon;

public class HoaDonController implements Initializable {


    @FXML
    private ImageView avatar;

    @FXML
    private Label nameUser;

    @FXML
    private Button quayLaiButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBox;

    private List<HoaDon> ds = dsHoaDon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            for(HoaDon hoadon : ds) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/hoaDonGio.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                HoaDonGioController hoaDonGioController = fxmlLoader.getController();
                hoaDonGioController.setData(hoadon);

                vBox.getChildren().add(anchorPane);
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
