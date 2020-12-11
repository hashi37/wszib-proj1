package com.wszib.warehouse.database;

public interface WarehouseDatabase {

    /**
     * Dodaja nowy element do bazy
     *
     * @param itemName
     */
    void addNewItem(String itemName);

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
