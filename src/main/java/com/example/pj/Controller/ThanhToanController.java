package com.example.pj.Controller;

import com.example.pj.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThanhToanController {

    @FXML
    private Button chuyenKhoanButton;

    @FXML
    private Button tienMatButton;

    @FXML
    private Label paymentMethodLabel;


    // Phương thức này được gọi khi người dùng chọn chuyển khoản
    @FXML
    void chonChuyenKhoan() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ckMaQR.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);

            // Lấy tham chiếu đến controller của fxml
            CKMaQRController controller = fxmlLoader.getController();

            // Thiết lập hình ảnh cho ImageView
            controller.setImage("E:\\btllll\\project-btl-oop-master\\target\\classes\\image\\home\\img.png");

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Phương thức này được gọi khi người dùng chọn tiền mặt
    @FXML
    void chonTienMat() throws IOException {
        String message = "Vui lòng đến quầy thu ngân thanh toán";
        showAlert(Alert.AlertType.INFORMATION, "Thanh toán bằng tiền mặt", message);


        Stage currentStage = (Stage) tienMatButton.getScene().getWindow();
        Stage[] stages = Stage.getWindows().toArray(new Stage[0]);
        for (Stage stage : stages) {
            if (stage != currentStage) {
                stage.close();
            }
        }


        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene homeScene = new Scene(homeLoader.load());
        Stage homeStage = (Stage) chuyenKhoanButton.getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Phương thức này để thiết lập ImageView từ bên ngoài


    // Phương thức này được gọi khi controller được khởi tạo


}
