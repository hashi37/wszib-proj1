package com.wszib.warehouse.database;

import com.wszib.warehouse.model.WarehouseItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("warehouseDatabase")
public class WarehouseDatabase {

    //nasza mini baza danych w kolekcji typu ArrayList
    private List<WarehouseItem> productsInWarehouse = new ArrayList<>();
    private int currentItemId = 1;
    private WarehouseItem item;

    /**
     * Dodaja nowy element do bazy
     *
     * @param itemName
     */
    public void addNewItem(String itemName) {
        // dodaj element (przechowywany w zmiennej argument) to kolekcji ArrayList nazwanej productsInwarehouse
        item = new WarehouseItem();
        item.setName(itemName);
        item.setId(currentItemId);
        productsInWarehouse.add(item);
        currentItemId++;
    }

    /**
     * Usun element z bady po id
     *
     * @param id
     */
    public void removeItem(int id) {
        // usun element, ktory najpierw musimy znalezc w ArrayList
        WarehouseItem itemToRemove = findInProductsInWarehouseList(id);
        if (itemToRemove == null) {
            System.out.println("Nie ma elementu o id: " + id);
            return;
        }
        productsInWarehouse.remove(itemToRemove);
    }

    /**
     * Wyswietl co mamy w bazie
     *
     * @return String elementow zapisanych w bazie
     */
    public String getItemList() {
        return productsInWarehouse.toString();
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
