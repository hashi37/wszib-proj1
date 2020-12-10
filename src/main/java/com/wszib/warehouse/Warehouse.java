package com.wszib.warehouse;

import com.wszib.warehouse.database.WarehouseDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    // mozliwe komendy w kolekcji List
    private List<String> commands = Arrays.asList(COMMAND_HELP, COMMAND_ADD, COMMAND_REMOVE, COMMAND_LIST, COMMAND_EXIT);

    // obsługa działąjącej pętli czytającej komendy z klawiatury
    private int run = 1;

    // moduł wewnętrzen bazy danych
    @Autowired
    WarehouseDatabase warehouseDatabase;


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
                if (!commands.contains(command)) {
                    System.out.println("Nieznana komenda!");
                    continue;
                }

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
                        warehouseDatabase.addNewItem(argument);
                        break;
                    case COMMAND_REMOVE:
                        // usun element z kolekcji ArrayList nazwanej productsInwarehouse
                        System.out.println("Usuwam element o id: " + argument);
                        warehouseDatabase.removeItem(Integer.parseInt(argument));
                        break;
                    case COMMAND_LIST:
                        // wypisz co mamy zapisane w kolekcji ArrayList productsInwarehouse
                        System.out.println("Produkty w bazie:");
                        System.out.println(warehouseDatabase.getItemList());
                        break;
                    default:
                        // nie powinnismy tu nigdy trafic!
                        System.out.println("?!");
                        break;
                }

            }

        }
    }


}
