package com.example.farzadfarshad.adeiye.Setting;

/**
 * Created by farshad&farzad on 5/12/2018.
 */

public class Item {


    public enum ItemType {
        ONE_ITEM, TWO_ITEM;
    }


    private String name;


    private ItemType type;

    public Item(String n, ItemType type) {
        this.name = n;
        this.type = type;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public ItemType getType() {
        return type;
    }


    public void setType(ItemType type) {
        this.type = type;
    }


}
