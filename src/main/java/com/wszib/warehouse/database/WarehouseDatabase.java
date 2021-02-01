package com.wszib.warehouse.database;

import com.wszib.warehouse.model.WarehouseItem;

public interface WarehouseDatabase {

    /**
     * Dodaja nowy element do bazy
     *
     * @param item
     */
    void addNewItem(WarehouseItem item);

    /**
     * Usun element z bady po id
     *
     * @param id
     */
    void removeItem(int id);

    /**
     * Wyswietl co mamy w bazie
     *
     * @return String elementow zapisanych w bazie
     */
    String getItemList();

}
