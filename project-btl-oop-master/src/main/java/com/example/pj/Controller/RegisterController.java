package com.example.pj.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;

public class RegisterController {
    @FXML
    private PasswordField confirmUserPassword;

    @FXML
    private Hyperlink login;

    @FXML
    private Button registerButton;

    @FXML
    private TextField sdt;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label checkUser;

    // XỬ LÝ SỰ KIỆN ĐĂNG NHẬP
    public void onDangNhap() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // XỬ LÝ SỰ KIỆN ĐĂNG KÍ
    public void onDangKy() {

        //KẾT NỐI VỚI CƠ SỞ DỮ LIỆU
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csdl", "root", "17062004");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM taiKhoan WHERE  (SDT = ?) OR (userName = ?)");
            preparedStatement.setString(1, sdt.getText());
            preparedStatement.setString(2, userName.getText());
            ResultSet resultSet = preparedStatement.executeQuery();

            // KIỂM TRA XEM TÀI KHOẢN ĐÃ TỒN TẠI HAY CHƯA
            while (resultSet.next()) {
                //CHECK SDT
                if (resultSet.getString("sdt").equals(sdt.getText())) {
                    checkUser.setText("Số điện thoại đã được đăng kí");
                    preparedStatement.close();
                    resultSet.close();
                    connection.close();
                    return;
                }
                //CHECK TÀI KHOẢN
                if (resultSet.getString("userName").equals(userName.getText())) {
                    checkUser.setText("Tài khoản đã tồn tại");
                    preparedStatement.close();
                    resultSet.close();
                    connection.close();
                    return;
                }
            }

            //CHECK MẬT KHẨU NHẬP LẠI
            if(!userPassword.getText().equals(confirmUserPassword.getText())) {
                checkUser.setText("Mật khẩu không khớp");
                return;
            }

            //NẾU NHƯ KHÔNG TỒN TẠI TÀI KHOẢN THÌ THỰC HIỆN ĐĂNG KÍ
            preparedStatement = connection.prepareStatement("INSERT INTO taiKhoan (userName, userPassword, sdt) VALUES (?, ?, ?)");
            preparedStatement.setString(1, userName.getText());
            preparedStatement.setString(2, userPassword.getText());
            preparedStatement.setString(3, sdt.getText());
            preparedStatement.executeUpdate();
            checkUser.setText("Đăng kí thành công");
            preparedStatement.close();
            resultSet.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
