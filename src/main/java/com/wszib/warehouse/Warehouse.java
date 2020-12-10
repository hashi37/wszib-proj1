package com.wszib.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component("warehouse")
public class Warehouse {

    private int run = 1;
    private int currentItemId = 1;
    WarehouseItem item;
    //mozliwe komendy w kolekcji List
    List<String> commands = Arrays.asList("help", "add", "remove", "list", "exit");

    //nasza mini baza danych w kolekcji typu ArrayList
    List<WarehouseItem> productsInWarehouse = new ArrayList<>();

    public Warehouse() {
        //wstep
        System.out.println("Prosta baza danych Magazyn Jakuba!");
        System.out.println("Dostepne komendy: help, add, remove, list, exit");
        System.out.println();
    }

    public void runApplication() {
        {
            //Niech program działa dopóki nie ustawię run na 0
            while (run == 1) {

                System.out.println();
                //dwukropek po ktorym bedziemy wpisywali komendy
                System.out.print(":");

                //czytamy klawiature w konsoli
                Scanner scanner = new Scanner(System.in);
                String inputString = scanner.nextLine();
                System.out.println("Wpisales komende: " + inputString);
                System.out.println();
                System.out.println();

                //dzielimy to co wpisane na elementy oddzielone spacja - zeby oddzielic komende i jej argument
                String[] inputParameters = inputString.split(" ", 2);

                //komenda
                String command = inputParameters[0];

                //argument
                String argument = "";
                if (inputParameters.length > 1) {
                    argument = inputParameters[1];
                }

                //sprawdzamy czy znamy komende
                if (commands.contains(command)) {
                    if (command.equalsIgnoreCase("exit")) {
                        // jak komenda exit to ustaw flage run na 0 i wyjdz
                        run = 0;
                    } else if (command.equalsIgnoreCase("help")) {
                        // wypisz pomoc
                        System.out.println("Dostepne komendy: help, add, remove, list, exit");
                        System.out.println("help - wyswietla pomoc, add NowyProdukt - dodaje, remove - StaryProdukt - usuwa, list - wyswietla co jest w magazynie, exit - zamyka program");
                    } else if (command.equalsIgnoreCase("add")) {
                        // dodaj element (przechowywany w zmiennej argument) to kolekcji ArrayList nazwanej productsInwarehouse
                        System.out.println("Dodaje: " + argument);
                        item = new WarehouseItem();
                        item.setName(argument);
                        item.setId(currentItemId);
                        productsInWarehouse.add(item);
                        currentItemId++;
                    } else if (command.equalsIgnoreCase("remove")) {
                        // usun element z kolekcji ArrayList nazwanej productsInwarehouse
                        System.out.println("Usuwam element o id: " + argument);
                        WarehouseItem itemToRemove = findInProductsInWarehouseList(Integer.parseInt(argument));
                        if(itemToRemove == null){
                            System.out.println("Nie ma elementu o id: " + argument);
                            return;
                        }
                        productsInWarehouse.remove(itemToRemove);
                    } else if (command.equalsIgnoreCase("list")) {
                        // wypisz co mamy zapisane w kolekcji ArrayList productsInwarehouse
                        System.out.println("Produkty w bazie:");
                        System.out.println(productsInWarehouse.toString());
                    } else {
                        // nie powinnismy tu nigdy trafic!
                        System.out.println("?!");
                    }
                } else {
                    System.out.println("Nieznana komenda!");
                }
            }

        }
    }

    private WarehouseItem findInProductsInWarehouseList(int id){
        WarehouseItem foundItem = null;

        for (WarehouseItem item : productsInWarehouse){
            if(item.getId() == id){
                foundItem = item;
            }
        }

        return foundItem;
    }
}
