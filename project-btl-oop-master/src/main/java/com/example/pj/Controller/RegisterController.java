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
    private int check = 0;
    private String phoneRegex = "^0\\d{9}$";
    private String TaiKhoanRegex = "^[a-zA-Z0-9_]{5,15}$";
    private String PassWordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";


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
        // KẾT NỐI VỚI CƠ SỞ DỮ LIỆU
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/btl", "root", "17062004");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM taiKhoan WHERE  (SDT = ?) OR (userName = ?)");
            preparedStatement.setString(1, sdt.getText());
            preparedStatement.setString(2, userName.getText());
            ResultSet resultSet = preparedStatement.executeQuery();

            // Kiểm tra xem tên người dùng và số điện thoại có trống không
            if (sdt.getText().isEmpty() || userName.getText().isEmpty()) {
                checkUser.setText("Không được để trống thông tin");
                return;
            }

            // Kiểm tra số điện thoại theo regex
            if (!sdt.getText().matches(phoneRegex)) {
                checkUser.setText("Số điện thoại phải gồm 10 chữ số\n và bắt đầu bằng 0");
                return;
            }

            // Kiểm tra tên người dùng theo regex
            if (!userName.getText().matches(TaiKhoanRegex)) {
                checkUser.setText("Tên người dùng phải có ít nhất 5 ký tự \n gồm chữ cái thường, hoa, và số");
                return;
            }

            // Kiểm tra mật khẩu không trống và trùng khớp
            if (userPassword.getText().isEmpty() || !userPassword.getText().matches(PassWordRegex) || !userPassword.getText().equals(confirmUserPassword.getText())) {
                checkUser.setText("Mật khẩu không được để trống \n và phải khớp và đúng định dạng");
                return;
            }

            // Kiểm tra xem có kết quả trả về từ cơ sở dữ liệu không
            boolean hasResult = resultSet.next();

            if (!hasResult) {
                // Nếu không có kết quả từ cơ sở dữ liệu, thêm người dùng mới
                preparedStatement = connection.prepareStatement("INSERT INTO taiKhoan (userName, userPassword, sdt) VALUES (?, ?, ?)");
                preparedStatement.setString(1, userName.getText());
                preparedStatement.setString(2, userPassword.getText());
                preparedStatement.setString(3, sdt.getText());
                preparedStatement.executeUpdate();
                checkUser.setText("Đăng kí thành công");
            } else {
                // Nếu tìm thấy tài khoản hoặc số điện thoại trong cơ sở dữ liệu
                if (resultSet.getString("sdt").equals(sdt.getText())) {
                    checkUser.setText("Số điện thoại đã được đăng kí");
                } else {
                    checkUser.setText("Tài khoản đã tồn tại");
                }
            }

            preparedStatement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
