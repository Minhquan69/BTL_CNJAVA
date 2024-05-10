package com.example.pj.Controller;

import com.example.pj.Models.HoaDon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.pj.Controller.GioHangController.dsHoaDon;
import static com.example.pj.Controller.LoginController.getUser_Name;

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
    @FXML
    private Button btnXuatHoaDon;

    private List<HoaDon> ds = dsHoaDon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            for (HoaDon hoadon : ds) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/hoaDonGio.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                HoaDonGioController hoaDonGioController = fxmlLoader.getController();
                hoaDonGioController.setData(hoadon);

                vBox.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // XỬ LÝ SỰ KIỆN BUTTON QUAY LẠI
    public void onButtonQuayLai() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) quayLaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onXuatHoaDon() {
        for (HoaDon hoadon : ds) {

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/btl", "root", "17062004")) {
                String sql = "INSERT INTO btl.hoadon (shd,tongtien,tenkh) VALUES (?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, hoadon.getId());
                statement.setInt(2, hoadon.getTien());
                statement.setString(3, getUser_Name());

                int rowsInserted = statement.executeUpdate();


                if (rowsInserted > 0) {
                    // Gửi thành công
                    showSuccessDialog();
                } else {
                    // Gửi không thành công
                    showErrorDialog();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
    private void showSuccessDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thành công");
        alert.setHeaderText(null);
        alert.setContentText("Gửi khiếu nại thành công!");

        alert.showAndWait();
    }
    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText("Gửi khiếu nại thất bại. Vui lòng thử lại sau!");

        alert.showAndWait();
    }

}
