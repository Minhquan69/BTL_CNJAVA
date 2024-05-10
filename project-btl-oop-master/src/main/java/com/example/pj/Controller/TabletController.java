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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.pj.Controller.HomeController.itemAll;

public class TabletController extends Thread implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Button quayLaiButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button timKiemButton;

    @FXML
    private TextField timKiemField;
    @FXML
    private Label labelGioHang;

    private File musicFile = new File("C:\\Users\\fifah\\Documents\\Zalo Received Files\\btllll\\project-btl-oop-master\\src\\Baihat4-_mp3cut.net_.au");
    private boolean isRunning = true;
    private Clip clip;
    private static final String FILE_PATH="C:\\Users\\fifah\\Documents\\Zalo Received Files\\btllll\\project-btl-oop-master\\src\\itemTablet.txt";
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

    //PHƯƠNG THỨC TRẢ VỀ DANH SÁCH CHỨA CÁC ITEM


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        Thread thread = new Thread(this);
        thread.start();


        int column = 0;
        int row = 1;

        List<Item> itemsTablet = new ArrayList<>(taoDS());

        try {
            for (Item item : itemsTablet) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/fxml/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setTabletController(this);
                itemController.setData(item);

                if (column == 5) {
                    column = 0;
                    ++row;
                }
                gridPane.add(anchorPane, column++, row);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }

    // XỬ LÝ SỰ KIỆN BUTTON QUAY LẠI
    public void onButtonQuayLai() throws Exception{
        clip.stop();
        clip.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) quayLaiButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

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




    @Override
    public void run() {
        try {


            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicFile);
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();



        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException  e) {
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
