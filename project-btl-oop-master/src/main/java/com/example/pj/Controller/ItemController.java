package com.example.pj.Controller;

import com.example.pj.Models.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.example.pj.Controller.HomeController.*;

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
    private HomeController homeController;
    private PhoneController phoneController;
    private TabletController tabletController;
    private PhukienController phukienController;


    //THIẾT LẬP CÁC THÔNG  TIN ITEM HIỂN THỊ LÊN
    public void setData(Item item) {
        this.item = item;

        item.setCodeItem(item.getCodeItem());
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
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
    public void setPhoneController(PhoneController phoneController) {
       this.phoneController=phoneController;
    }
    public void setTabletController(TabletController tabletController) {
        this.tabletController=tabletController;
    }
    public void setPhukienController(PhukienController phukienController) {
        this.phukienController=phukienController;
    }

    //SỰ KIỆN THÊM VÀO GIỎ HÀNG
    public void onThemGioHang() {

        for (Item a : itemsGioHang) {
            if (a.getItemName().equals(item.getItemName())) {
                if (homeController != null) {
                    homeController.daThemGH();
                    return;
                } else {
                }
                if (phoneController != null) {
                    phoneController.daThemGH();
                    return;
                } else {
                }
                if (tabletController != null) {
                    tabletController.daThemGH();
                    return;
                } else {
                }
                if (phukienController != null) {
                    phukienController.daThemGH();
                    return;
                } else {
                }
                return;
            }
        }
        itemsGioHang.add(item);

        if (homeController != null) {
            homeController.hamThemGH();
        } else {
        }
        if (phoneController != null) {
            phoneController.hamThemGH();
        } else {
        }
        if (tabletController != null) {
            tabletController.hamThemGH();
        } else {
        }
        if (phukienController != null) {
            phukienController.hamThemGH();
        } else {
        }


    }


}
