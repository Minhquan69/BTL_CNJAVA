package com.example.pj.Controller;

import com.example.pj.Models.Item;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import com.example.pj.Controller.LoginController;

import static com.example.pj.Controller.LoginController.getUser_Name;

public class HomeController extends Thread implements Initializable {

    @FXML
    private ImageView avatar;

    @FXML
    private Button dangXuatButton;

    @FXML
    private Button TabletButton;

    @FXML
    private Button phuKienButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button gioHangButton;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button hoaDonButton;

    @FXML
    private Button khieuNaiButton;

    @FXML
    private Label nameUser;

    @FXML
    private Button PhoneButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button timKiemButton;

    @FXML
    private TextField timKiemField;
    @FXML
    private Button btnDung;
    @FXML
    private Button btnTiepTuc;
    @FXML
    private Button btnTatNhac;
    @FXML
    private  Label labelGioHang;

    //TẠO MỘT DANH SÁCH CÁC SẢN PHẨM ĐƯỢC THÊM VÀO GIỎ HÀNG
    public static List<Item> itemsGioHang = new ArrayList<>();
    public static List<Item> itemAll = new ArrayList<>();

    public static final String FILE_PATHH = "C:\\Users\\fifah\\Documents\\Zalo Received Files\\btllll\\project-btl-oop-master\\src\\itemAll.txt";
    //PHƯƠNG THỨC TRẢ VỀ DANH SÁCH CHỨA CÁC ITEM
    private static final String FILE_PATH = "C:\\Users\\fifah\\Documents\\Zalo Received Files\\btllll\\project-btl-oop-master\\src\\itemsHome.txt";

    private File musicFile = new File("C:\\Users\\fifah\\Documents\\Zalo Received Files\\btllll\\project-btl-oop-master\\src\\Baihat1-_mp3cut.net_.au");
    private boolean isRunning = true;
    private Clip clip;


    public List<Item> taoDS() {
        List<Item> ls = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String imagePath = parts[0];
                String name = parts[1];
                int price = Integer.parseInt(parts[2].trim());
                String ratingImagePath = parts[3];
                String id = parts[4];
                ls.add(new Item(imagePath, name, price, ratingImagePath, id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public static List<Item> taoToanBoDS() {
        List<Item> lss = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATHH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String imagePath = parts[0];
                String name = parts[1];
                int price = Integer.parseInt(parts[2].trim());
                String ratingImagePath = parts[3];
                String id = parts[4];
                lss.add(new Item(imagePath, name, price, ratingImagePath, id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lss;
    }

    int column = 0;
    int row = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        nameUser.setText(getUser_Name());

        Thread thread = new Thread(this);
        thread.start();

        itemAll.addAll(taoToanBoDS());

        List<Item> itemsGoiY = new ArrayList<>(taoDS());

        try {
            for (Item item : itemsGoiY) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setHomeController(this);
                itemController.setData(item);

                if (column == 5) {
                    column = 0;
                    ++row;
                }
                gridPane.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            System.out.println("Lỗi RunTimeException!");
        }


    }

    // SỰ KIỆN ẤN VÀO GIỎ HÀNG BUTTON
    public void onGioHangButton() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gioHang.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) gioHangButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //SỰ KIỆN ẤN VÀO BUTTON PHONE
    public void onPhone() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Phone.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) PhoneButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //SỰ KIỆN ẤN VÀO BUTTON TABLET
    public void onTablet() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Tablet.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) TabletButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //SỰ KIỆN ẤN VÀO BUTTON PHU KIEN
    public void onPhuKien() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PhuKien.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) phuKienButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //XỬ LÝ SỰ KIỆN BUTTON  HÓA ĐƠN
    public void onHoaDon() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/hoaDon.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) hoaDonButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    // Xử lý sự kiện tìm kiếm theo tên sản phẩm
    @FXML
    public void onButtonTimKiem() throws IOException {
        String searchText = timKiemField.getText().toLowerCase(); // Lấy giá trị từ trường tìm kiếm


        for (var e : itemAll) {
            if (e.getItemName().toLowerCase().trim().equals(searchText)) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(e);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL); // Đặt chế độ modal
                stage.setResizable(false);
                stage.setScene(new Scene(anchorPane)); // Sử dụng anchorPane đã load
                stage.show();

                return;

            }

        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Không tìm thấy đồ yêu cầu!");
        alert.showAndWait();
    }

    @FXML
    //XỬ LÝ SỰ KIỆN BUTTON THOÁT
    public void onThoat() {
        clip.stop();
        clip.close();
        System.exit(0);
    }

    //XỬ LÝ SỰ KIỆN BUTTON ĐĂNG XUẤT
    public void onDangXuat() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) dangXuatButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void onKhieuNai() throws Exception {
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/KhiieuNaiForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) khieuNaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




    @Override
    public void run() {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicFile);
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
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
                System.out.println("Lỗi audio");
            }
        }

    public  void hamThemGH() {
        if (labelGioHang != null) {
            labelGioHang.setText("Đã thêm vào giỏ hàng!");
            labelGioHang.setVisible(true);
            // Tạo một Timeline để ẩn label sau 2 giây
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> labelGioHang.setVisible(false)));
            timeline.play();
        } else {
            System.out.println("labelGioHang is null. Cannot update label.");
        }
    }

    public  void daThemGH() {
        if (labelGioHang != null) {
            labelGioHang.setText("Sản phẩm đã được thêm trước đó!");
            labelGioHang.setVisible(true);
            // Tạo một Timeline để ẩn label sau 2 giây
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> labelGioHang.setVisible(false)));
            timeline.play();
        } else {
            System.out.println("labelGioHang is null. Cannot update label.");
        }
    }
}
