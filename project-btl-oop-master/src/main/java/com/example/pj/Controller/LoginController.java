package com.example.pj.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink register;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label text;

    public static  String loggedInUsername; // Biến để lưu trữ tên người dùng đã đăng nhập

    // Setter method cho tên người dùng đã đăng nhập
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }

    // XỬ LÝ SỰ KIỆN ĐĂNG KÍ
    public void onDangKi() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //XỬ LÝ SỰ KIỆN QUÊN MẬT KHẨU
    public void onQuenMK() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/forgotPassword.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //XỬ LÝ SỰ KIỆN ĐĂNG NHẬP
    public void onLogin() throws Exception {
        // Kết nối cơ sở dữ liệu
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csdl", "root", "17062004")) {
            // Truy vấn kiểm tra thông tin đăng nhập
            String query = "SELECT * FROM taiKhoan WHERE userName = ? AND userPassword = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, userName.getText());
                preparedStatement.setString(2, userPassword.getText());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) { // Nếu thông tin đăng nhập chính xác
                        String username = resultSet.getString("userName"); // Lấy tên người dùng từ cơ sở dữ liệu
                        setLoggedInUsername(username); // Lưu tên người dùng đã đăng nhập

                        // Chuyển sang màn hình tiếp theo (ví dụ: màn hình chính)
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                        Parent root = fxmlLoader.load();
                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } else { // Nếu thông tin đăng nhập không chính xác
                        text.setText("Tài khoản hoặc mật khẩu không chính xác");
                    }
                }
            }
        }
    }
}
