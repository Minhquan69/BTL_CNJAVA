package com.example.pj.Controller;

import com.example.pj.Models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.pj.Controller.HomeController.itemsGioHang;

public class ItemController {

    @FXML
    private Button buttonThemGioHang;

    @FXML
    private ImageView imageItem;

    @FXML
    private Label nameItem;

    @FXML
    private Label priceItem;

    @FXML
    private ImageView starItem;

    private Item item;



    //THIẾT LẬP CÁC THÔNG  TIN ITEM HIỂN THỊ LÊN
    public void setData(Item item) {
        this.item = item;

        item.setCodeItem("ABC123");
        //THIẾT LẬP ẢNH
        Image image;
        image = new Image(getClass().getResourceAsStream(item.getItemImage()));
        imageItem.setImage(image);

        //THIẾT LẬP TÊN
        nameItem.setText(item.getItemName());

        //THIẾT LẬP GIÁ
        priceItem.setText(String.valueOf(item.getItemPrice()));

        //THIẾT LẬP ĐÁNH GIÁ
        image = new Image(getClass().getResourceAsStream(item.getItemStar()));
        starItem.setImage(image);
    }

    //SỰ KIỆN THÊM VÀO GIỎ HÀNG
    public void onThemGioHang() {

        for(Item a : itemsGioHang) {
            if(a.getItemName().equals(item.getItemName())){
                return;
            }
        }
        itemsGioHang.add(item);
    }




}
