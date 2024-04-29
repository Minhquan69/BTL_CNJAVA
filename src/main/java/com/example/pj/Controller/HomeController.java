package com.example.pj.Controller;

import com.example.pj.Models.Item;
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

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

    //TẠO MỘT DANH SÁCH CÁC SẢN PHẨM ĐƯỢC THÊM VÀO GIỎ HÀNG
    public static List<Item> itemsGioHang = new ArrayList<>();
    public static List<Item> itemAll = new ArrayList<>();

    private static final String FILE_PATHH = "E:\\btllll\\project-btl-oop-master\\src\\main\\java\\com\\example\\pj\\itemAll.txt";
    //PHƯƠNG THỨC TRẢ VỀ DANH SÁCH CHỨA CÁC ITEM
    private static final String FILE_PATH = "E:\\btllll\\project-btl-oop-master\\src\\main\\java\\com\\example\\pj\\itemsHome.txt";

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
        itemAll.addAll(taoToanBoDS());

        List<Item> itemsGoiY = new ArrayList<>(taoDS());

        try {
            for (Item item : itemsGoiY) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(item);

                if (column == 5) {
                    column = 0;
                    ++row;
                }
                gridPane.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // SỰ KIỆN ẤN VÀO GIỎ HÀNG BUTTON
    public void onGioHangButton() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gioHang.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) gioHangButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //SỰ KIỆN ẤN VÀO BUTTON PHONE
    public void onPhone() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Phone.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) PhoneButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //SỰ KIỆN ẤN VÀO BUTTON TABLET
    public void onTablet() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Tablet.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) TabletButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //SỰ KIỆN ẤN VÀO BUTTON PHU KIEN
    public void onPhuKien() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PhuKien.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) phuKienButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    //XỬ LÝ SỰ KIỆN BUTTON  HÓA ĐƠN
    public void onHoaDon() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/hoaDon.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) hoaDonButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // XU LY SỤ KIEN TIM KIEM THEO TEN

    // Xử lý sự kiện tìm kiếm theo tên sản phẩm
    @FXML
    public void onButtonTimKiem() throws IOException {
        String searchText = timKiemField.getText().toLowerCase(); // Lấy giá trị từ trường tìm kiếm


for (var e : itemAll){
    if (e.getItemName().toLowerCase().trim().equals(searchText)){
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
        System.exit(0);
    }

    //XỬ LÝ SỰ KIỆN BUTTON ĐĂNG XUẤT
    public void onDangXuat() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) dangXuatButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void onKhieuNai() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/khieunai.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) khieuNaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
