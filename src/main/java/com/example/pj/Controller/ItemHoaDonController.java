package com.example.pj.Controller;

import com.example.pj.Models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemHoaDonController {
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

    Item item;

    public void setData(Item item) {
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
}
