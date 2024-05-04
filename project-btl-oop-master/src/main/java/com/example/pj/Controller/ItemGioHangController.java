package com.example.pj.Controller;

import com.example.pj.Models.Item;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import static com.example.pj.Controller.HomeController.itemsGioHang;

public class ItemGioHangController {

    @FXML
    private Button giamButton;

    @FXML
    private ImageView imageItem;

    @FXML
    private Label nameItem;

    @FXML
    private Label priceItem;

    @FXML
    private Label soLuongField;

    @FXML
    private ImageView starItem;

    @FXML
    private Button tangButton;

    @FXML
    private Button xoaButton;

    private Item item;


    //THIẾT LẬP DỮ LIỆU CHO CẢNH ITEMGIOHANG
    public void setData(Item item) {
        this.item = item;

        //THIẾT LẬP ẢNH
        Image  image = new Image(getClass().getResourceAsStream(item.getItemImage()));
        imageItem.setImage(image);

        //THIẾT LẬP TÊN
        nameItem.setText(item.getItemName());

        //THIẾT LẬP GIÁ
        priceItem.setText(String.valueOf(item.getItemPrice()));

        //THIẾT LẬP ĐÁNH GIÁ
        image = new Image(getClass().getResourceAsStream(item.getItemStar()));
        starItem.setImage(image);

        //THIẾT LẬP SỐ LƯỢNG
        soLuongField.setText(String.valueOf(item.getItemNumber()));

    }

    //SỰ KIỆN KHI ẤN XÓA ITEM KHỎI GIỎ HÀNG
    public void onXoa() throws Exception{
        itemsGioHang.remove(item);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gioHang.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) xoaButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    //SỰ KIỆN KHI ẤN NÚT TĂNG SỐ LƯỢNG
    public void onTangButton() throws Exception{

        for (Item a : itemsGioHang) {
            if (a.getItemName().equals(item.getItemName())) {
                a.setItemNumber(a.getItemNumber()+1);
                soLuongField.setText(String.valueOf(a.getItemNumber()));
                //CẬP NHẬT LẠI GIAO DIỆN
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gioHang.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) tangButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    // XU LY TIM KIEM
    public void onGiamButton() throws Exception{
        for (Item a : itemsGioHang) {
            if (a.getItemName().equals(item.getItemName())) {
                a.setItemNumber(a.getItemNumber()-1);
                if(a.getItemNumber() == -1) {
                    a.setItemNumber(0);
                }
                soLuongField.setText(String.valueOf(a.getItemNumber()));
                //CẬP NHẬT LẠI GIAO DIỆN
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/gioHang.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) giamButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                return;
            }
        }
    }


}
