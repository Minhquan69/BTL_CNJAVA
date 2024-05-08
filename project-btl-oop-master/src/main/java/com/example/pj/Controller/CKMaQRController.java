
package com.example.pj.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class CKMaQRController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button btnClose;

    // Phương thức này để thiết lập hình ảnh cho ImageView từ đường dẫn
    public void setImage(String imagePath) {
        Image image = new Image("file:" + imagePath);
        imageView.setImage(image);
    }

    // Phương thức này được gọi khi người dùng đóng cửa sổ popup
    @FXML
    void closePopup() throws IOException {
        // Đóng tất cả các cửa sổ khác
        Stage currentStage = (Stage) btnClose.getScene().getWindow();
        Stage[] stages = Stage.getWindows().toArray(new Stage[0]);
        for (Stage stage : stages) {
            if (stage != currentStage) {
                stage.close();
            }
        }

        // Load lại trang home
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        currentStage.setResizable(false);
        // Set lại scene cho cửa sổ chứa trang home
        currentStage.setScene(scene);
        currentStage.show();
    }


}

