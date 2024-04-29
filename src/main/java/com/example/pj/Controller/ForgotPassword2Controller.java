package com.example.pj.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ForgotPassword2Controller {
    @FXML
    private PasswordField confirmPassword;

    @FXML
    private PasswordField password;

    @FXML
    private Hyperlink troLai;

    @FXML
    private Button xacNhanButton;

    // XỬ LÝ SỰ KIỆN XÁC NHẬN

    //XỬ LÝ SỰ KIỆN QUAY LẠI
    public void onTroLai() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/forgotPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) troLai.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
