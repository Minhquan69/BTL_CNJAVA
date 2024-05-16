package com.example.pj.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class ForgotPasswordController {
    @FXML
    private Hyperlink login;

    @FXML
    private TextField sdt;

    @FXML
    private Button tiepButton;
    @FXML
    private Label labell;

    // XỬ LÝ SỰ KIỆN TIẾP
    public void onTiep() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/btl", "root", "17062004")) {
            // Truy vấn kiểm tra thông tin đăng nhập
            String query = "SELECT * FROM taiKhoan WHERE sdt = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, sdt.getText());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) { // Nếu thông tin đăng nhập chính xác
                        labell.setText("Mã số đã được gửi đến số điện thoại.");

                    } else if (labell.getText().isEmpty()) {
                        labell.setText("Không được bỏ trống số điện thoại!");

                    } else { // Nếu thông tin đăng nhập không chính xác

                        labell.setText("Số điện thoại không tồn tại!");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // XỬ LÝ SỰ KIỆN ĐĂNG NHẬP
    public void onDangNhap() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) tiepButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
