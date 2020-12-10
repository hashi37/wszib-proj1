package com.wszib.warehouse.model;

import org.springframework.stereotype.Component;

@Component
public class WarehouseItem {
    // nazwa
    public String name;
    // numer
    public int id;


    public WarehouseItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + id + ":" + name;
    }

}
