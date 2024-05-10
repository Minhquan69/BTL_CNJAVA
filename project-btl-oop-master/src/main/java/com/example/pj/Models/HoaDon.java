package com.example.pj.Models;

import java.util.List;

import static com.example.pj.Controller.HoaDonController.nextId;

public class HoaDon {
    private int id;
    private List<Item> ds;

    private int tien;

    private static int nextId = nextId();


    public HoaDon(List<Item> ds) {
        this.id = nextId;
        nextId++;
        this.ds = ds;
        int sum = 0;
        for(Item a : ds) {
            sum = sum + (a.getItemNumber()*a.getItemPrice());
        }
        this.tien = sum;
    }

    public List<Item> getDs() {
        return ds;
    }

    public void setDs(List<Item> ds) {
        this.ds = ds;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTien() {
        return tien;
    }

    public void setTien(int tien) {
        this.tien = tien;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        HoaDon.nextId = nextId;
    }
}
