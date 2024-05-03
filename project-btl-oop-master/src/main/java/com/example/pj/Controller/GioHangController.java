package com.example.pj.Controller;

import com.example.pj.Models.HoaDon;
import com.example.pj.Models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.pj.Controller.HomeController.itemsGioHang;

public class GioHangController extends Thread implements Initializable {

    @FXML
    private ImageView avatar;

    @FXML
    private Label nameUser;

    @FXML
    private Button quayLaiButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button thanhToanButton;

    @FXML
    private Button timKiemButton;

    @FXML
    private TextField timKiemField;

    @FXML
    private Label tongTienField;

    @FXML
    private VBox vBox;

    private File musicFile = new File("E:\\Test\\progresbar.au");
    private boolean isRunning = true;
    private Clip clip;

    private List<Item> ds = itemsGioHang;
    private int tong = 0;
    public static List<HoaDon> dsHoaDon = new ArrayList<>();

    List<Item> itemsGioHangTemp;

    private URL url;
    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicFile);
            clip.open(inputStream);

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }



        Thread thread = new Thread(this);
        thread.start();




        this.url = url;
        this.resourceBundle = resourceBundle;

        try {
            for (Item item : ds) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/itemGioHang.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemGioHangController itemGioHangController = fxmlLoader.getController();
                itemGioHangController.setData(item);

                vBox.getChildren().add(anchorPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (Item a : itemsGioHang) {
            tong = tong + a.getItemPrice() * a.getItemNumber();
        }

        tongTienField.setText(String.valueOf(tong));
    }

    //SỰ KIỆN BUTTON QUAY LẠI
    public void onButtonQuayLai() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) quayLaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //SỰ KIỆN KHI NHẤN VÀO NÚT THANH TOÁN
    public void onButtonThanhToan() throws Exception {
        clip.stop();
        clip.close();
        if (tong==0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng thêm đồ vào giỏ hàng trước khi thanh toán!");
            alert.showAndWait();
            FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Scene homeScene = new Scene(homeLoader.load());
            Stage homeStage = (Stage) quayLaiButton.getScene().getWindow();
            homeStage.setScene(homeScene);
            homeStage.show();
        }
        else{

        // TẠO ĐỐI TƯỢNG HOADON MOI SAU ĐÓ THÊM NÓ VÀO DANH SÁCH HÓA ĐƠN THANH TOÁN
        itemsGioHangTemp = new ArrayList<>(itemsGioHang);
        dsHoaDon.add(new HoaDon(itemsGioHangTemp));
        //XÓA CÁC SẢN PHẨM TRONG GIỎ HÀNG
        itemsGioHang.clear();
        //CẬP NHẬT LẠI GIAO DIỆN

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ThanhToan.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Đặt chế độ modal
        stage.setResizable(false);
        stage.setTitle("App");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }}


    //SỬ LÝ SỰ KIỆN TÌM KIẾM
    // SỬ LÝ SỰ KIỆN TÌM KIẾM
    @FXML
    public void onButtonTimKiem() {
        String searchText = timKiemField.getText().toLowerCase(); // Lấy giá trị từ trường tìm kiếm

        vBox.getChildren().clear(); // Xóa các sản phẩm hiện có trong VBox

        // Duyệt qua danh sách sản phẩm trong giỏ hàng để tìm kiếm
        for (Item item : ds) {
            if (item.getItemName().toLowerCase().contains(searchText)) { // Kiểm tra nếu tên sản phẩm chứa giá trị tìm kiếm
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/fxml/itemGioHang.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    ItemGioHangController itemGioHangController = fxmlLoader.getController();
                    itemGioHangController.setData(item);

                    vBox.getChildren().add(anchorPane); // Hiển thị sản phẩm tìm thấy trong VBox
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }





    @Override
    public void run() {
        try {


            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicFile);
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            while (isRunning && clip.getMicrosecondPosition() < clip.getMicrosecondLength()) {
                Thread.sleep(10);
            }

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void resumeMusic() {
        if (clip != null && !clip.isRunning()) {
            long currentPosition = clip.getMicrosecondPosition();
            clip.setMicrosecondPosition(currentPosition);
            clip.start();
        }
    }

    public void stopMusic() {
        isRunning = false;
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public void startMusic() {
        if (clip != null && clip.isOpen()) {
            clip.stop();
            clip.close();
        }

        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }







}
