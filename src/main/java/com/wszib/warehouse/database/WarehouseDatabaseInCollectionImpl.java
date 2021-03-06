package com.wszib.warehouse.database;

import com.wszib.warehouse.model.WarehouseItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component("warehouseDatabase")
public class WarehouseDatabaseInCollectionImpl implements WarehouseDatabase {

    //nasza mini baza danych w kolekcji typu ArrayList
    private List<WarehouseItem> productsInWarehouse = new ArrayList<>();
    private int currentItemId = 1;

    @Override
    public void addNewItem(WarehouseItem item) {
        // dodaj element (przechowywany w zmiennej argument) to kolekcji ArrayList nazwanej productsInwarehouse
        item.setId(currentItemId);
        productsInWarehouse.add(item);
        currentItemId++;
    }


    @Override
    public void removeItem(int id) {
        // usun element, ktory najpierw musimy znalezc w ArrayList
        WarehouseItem itemToRemove = findInProductsInWarehouseList(id);
        if (itemToRemove == null) {
            System.out.println("Nie ma elementu o id: " + id);
            return;
        }
        productsInWarehouse.remove(itemToRemove);
    }


    @Override
    public String getItemList() {
        return productsInWarehouse.toString();
    }

    @Override
    public List getAllItems() {
        return null;
    }

    @Override
    public WarehouseItem getById(int id) {
        return findInProductsInWarehouseList(id);
    }

    @Override
    public void saveItem(WarehouseItem item) {
        //nie używana
    }

    /**
     * @param id
     * @return Znaleziony element
     */
    private WarehouseItem findInProductsInWarehouseList(int id) {
        WarehouseItem foundItem = null;

        for (WarehouseItem item : productsInWarehouse) {
            if (item.getId() == id) {
                foundItem = item;
            }
        }

        return foundItem;
    }

}
