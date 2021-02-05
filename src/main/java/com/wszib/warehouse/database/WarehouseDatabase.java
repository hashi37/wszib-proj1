package com.wszib.warehouse.database;

import com.wszib.warehouse.model.WarehouseItem;

import java.util.List;

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

    /**
     * Pobiera listę wszystkich obietków
     * @return List obiektów z bazy
     */
    List getAllItems();

    /**
     * Pobiera obiekt z bazy
     * @param id
     * @return Przedmiot o danym id
     */
    WarehouseItem getById(int id);

    /**
     * Zapisuje obiekt do bazy
     * @param item
     */
    void saveItem(WarehouseItem item);
}
