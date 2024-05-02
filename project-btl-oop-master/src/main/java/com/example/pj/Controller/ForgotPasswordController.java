package com.example.pj.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ForgotPasswordController {
    @FXML
    private Hyperlink login;

    @FXML
    private TextField sdt;

    @FXML
    private Button tiepButton;

    // XỬ LÝ SỰ KIỆN TIẾP


    // XỬ LÝ SỰ KIỆN ĐĂNG NHẬP
    public void onDangNhap() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) tiepButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
