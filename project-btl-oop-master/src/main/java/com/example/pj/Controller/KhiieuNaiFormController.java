package com.example.pj.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KhiieuNaiFormController {
    @FXML
    private Button quayLaiButton;

    @FXML
    private TextField txtHoTen;

    @FXML
    private TextArea txtNoiDung;

    @FXML
    private TextField txtDonHangID;

    @FXML
    private TextField txtEmail;

    @FXML
    private ComboBox<String> cmbThacMac;

    private File selectedFile;

    @FXML
    private void initialize() {
        cmbThacMac.getItems().addAll("Vấn đề vận chuyển", "Sản phẩm bị hỏng", "Chính sách hoàn trả");
    }

    @FXML
    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn file đính kèm");
        selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            System.out.println("File đã chọn: " + selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void submitKhiieuNai() {
        String hoTen = txtHoTen.getText();
        String noiDung = txtNoiDung.getText();
        String donHangID = txtDonHangID.getText();
        String email = txtEmail.getText();
        String thacMac = cmbThacMac.getValue();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/btl", "root", "17062004")) {
            String sql = "INSERT INTO btl.KhiieuNai (ho_ten, noi_dung, don_hang_id, email, thac_mac, file, ngay_gio) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, hoTen);
            statement.setString(2, noiDung);
            statement.setString(3, donHangID);
            statement.setString(4, email);
            statement.setString(5, thacMac);
            statement.setString(6, selectedFile != null ? selectedFile.getAbsolutePath() : null);

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
            // Xử lý lỗi kết nối hoặc truy vấn cơ sở dữ liệu
            showErrorDialog();
        }

        // Đặt lại nội dung của form
        txtHoTen.clear();
        txtNoiDung.clear();
        txtDonHangID.clear();
        txtEmail.clear();
        cmbThacMac.getSelectionModel().clearSelection();
        selectedFile = null;
    }
    private void showSuccessDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thành công");
        alert.setHeaderText(null);
        alert.setContentText("Gửi khiếu nại thành công!");

        alert.showAndWait();
    }

    private void showErrorDialog() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Lỗi");
        alert.setHeaderText(null);
        alert.setContentText("Gửi khiếu nại thất bại. Vui lòng thử lại sau!");

        alert.showAndWait();
    }

    public void onButtonQuayLai() throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) quayLaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}