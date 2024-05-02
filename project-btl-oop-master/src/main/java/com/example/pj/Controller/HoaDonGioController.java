package com.example.pj.Controller;

import com.example.pj.Models.HoaDon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static com.example.pj.Controller.GioHangController.dsHoaDon;

public class HoaDonGioController {

    @FXML
    private Button chiTietButton;

    @FXML
    private Label soHoaDon;

    @FXML
    private Label trangThai;

    @FXML
    private Label tienHoaDon;

    private HoaDon hoadon;

    public static HoaDon detail;

    private String loggedInUsername; // Biến để lưu trữ tên người dùng đã đăng nhập

    // Setter method cho tên người dùng đã đăng nhập
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }

    public void setData(HoaDon hoadon) {
        this.hoadon = hoadon;
        soHoaDon.setText(String.valueOf(hoadon.getId()));
        tienHoaDon.setText(String.valueOf(hoadon.getTien()));
    }

    // XỬ LÝ SỰ KIỆN BUTTON CHI TIẾT
    public void onChiTietbutton() throws Exception {
        for (HoaDon a : dsHoaDon) {
            if (a.getId() == hoadon.getId()) {
                detail = a;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HoaDonDetail.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) chiTietButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                return;
            }
        }
    }

    // Phương thức chèn hoá đơn vào cơ sở dữ liệu
    public void insertHoaDon(HoaDon hoaDon) {
        try {
            // Kết nối đến cơ sở dữ liệu
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/csdl", "root", "daomquan27022004")) {
                String sql = "INSERT INTO hoa_don (id, tien) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Sử dụng dữ liệu từ đối tượng HoaDon đã được truyền vào phương thức
                    setLoggedInUsername(LoginController.loggedInUsername);
                    preparedStatement.setInt(1, hoaDon.getId());
                    preparedStatement.setDouble(2, hoaDon.getTien());

                    // Thực thi truy vấn
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
