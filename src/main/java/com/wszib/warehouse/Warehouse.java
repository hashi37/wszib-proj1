package com.wszib.warehouse;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component("warehouse")
public class Warehouse {

    final static String COMMAND_HELP = "help";
    final static String COMMAND_ADD = "add";
    final static String COMMAND_REMOVE = "remove";
    final static String COMMAND_LIST = "list";
    final static String COMMAND_EXIT = "exit";

    private int run = 1;
    private int currentItemId = 1;
    private WarehouseItem item;
    //mozliwe komendy w kolekcji List
    private List<String> commands = Arrays.asList(COMMAND_HELP, COMMAND_ADD, COMMAND_REMOVE, COMMAND_LIST, COMMAND_EXIT);

    //nasza mini baza danych w kolekcji typu ArrayList
    private List<WarehouseItem> productsInWarehouse = new ArrayList<>();

    public Warehouse() {
        //wstep
        System.out.println("Prosta baza danych Moj Magazyn!");
        System.out.println("Dostepne komendy: help, add, remove, list, exit");
        System.out.println();
    }

    void runApplication() {
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
                String command = inputParameters[0].toLowerCase();

                //argument
                String argument = "";
                if (inputParameters.length > 1) {
                    argument = inputParameters[1];
                }

                //sprawdzamy czy znamy komende
                if (commands.contains(command)) {

                    switch (command) {
                        case COMMAND_EXIT:
                            // jak komenda exit to ustaw flage run na 0 i wyjdz
                            run = 0;
                            break;
                        case COMMAND_HELP:
                            // wypisz pomoc
                            System.out.println("Dostepne komendy: help, add, remove, list, exit");
                            System.out.println("help - wyswietla pomoc, add NowyProdukt - dodaje, remove - StaryProdukt - usuwa, list - wyswietla co jest w magazynie, exit - zamyka program");
                            break;
                        case COMMAND_ADD:
                            // dodaj element (przechowywany w zmiennej argument) to kolekcji ArrayList nazwanej productsInwarehouse
                            System.out.println("Dodaje: " + argument);
                            item = new WarehouseItem();
                            item.setName(argument);
                            item.setId(currentItemId);
                            productsInWarehouse.add(item);
                            currentItemId++;
                            break;
                        case COMMAND_REMOVE:
                            // usun element z kolekcji ArrayList nazwanej productsInwarehouse
                            System.out.println("Usuwam element o id: " + argument);
                            WarehouseItem itemToRemove = findInProductsInWarehouseList(Integer.parseInt(argument));
                            if (itemToRemove == null) {
                                System.out.println("Nie ma elementu o id: " + argument);
                                return;
                            }
                            productsInWarehouse.remove(itemToRemove);
                            break;
                        case COMMAND_LIST:
                            // wypisz co mamy zapisane w kolekcji ArrayList productsInwarehouse
                            System.out.println("Produkty w bazie:");
                            System.out.println(productsInWarehouse.toString());
                            break;
                        default:
                            // nie powinnismy tu nigdy trafic!
                            System.out.println("?!");
                            break;
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
