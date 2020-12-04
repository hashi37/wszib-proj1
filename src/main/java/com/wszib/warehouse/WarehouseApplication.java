package com.wszib.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



@SpringBootApplication
public class WarehouseApplication {


	WarehouseItem item;

	private static int run = 1;
	//mozliwe komendy w kolekcji List
	List<String> commands = Arrays.asList(new String[] {"help", "add", "remove", "list", "exit"});

	//nasza mini baza danych w kolekcji typu ArrayList
	List<String> productsInwarehouse = new ArrayList<String>();

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

	public WarehouseApplication(){
		System.out.println("Prosta baza danych Magazyn Jakuba!");

		//Niech program działa dopóki nie ustawię run na 0
		while (run == 1){

			//wstep
			System.out.println("Dostepne komendy: help, add, remove, list, exit");
			System.out.println();
			//dwukropek po ktorym bedziemy wpisywali komendy
			System.out.print(":");

			//czytamy klawiature w konsoli
			Scanner scanner = new Scanner(System. in);
			String inputString = scanner. nextLine();
			System.out.println("Wpisales komende: \n"+inputString);
			System.out.println();

			//dzielimy to co wpisane na elementy oddzielone spacja - zeby oddzielic komende i jej argument
			String[] inputParameters = inputString.split(" ", 2);

			//komenda
			String command = inputParameters[0];

			//argument
			String argument = "";
			if(inputParameters.length > 1){
				argument = inputParameters[1];
			}

			//sprawdzamy czy znamy komende
			if(commands.contains(command)){
				if(command.equalsIgnoreCase("exit")){
					// jak komenda exit to ustaw flage run na 0 i wyjdz
					run = 0;
				} else if(command.equalsIgnoreCase("help")){
					// wypisz pomoc
					System.out.println("Dostepne komendy: help, add, remove, list, exit");
					System.out.println("help - wyswietla pomoc, add NowyProdukt - dodaje, remove - StaryProdukt - usuwa, list - wyswietla co jest w magazynie, exit - zamyka program");
				} else if(command.equalsIgnoreCase("add")){
					// dodaj element (przechowywany w zmiennej argument) to kolekcji ArrayList nazwanej productsInwarehouse
					System.out.println("Dodaje: "+ argument);
					productsInwarehouse.add(argument);
				} else if(command.equalsIgnoreCase("remove")){
					// usun element (przechowywany w zmiennej argument) z kolekcji ArrayList nazwanej productsInwarehouse
					System.out.println("Usuwam: "+ argument);
					productsInwarehouse.remove(argument);
				} else if(command.equalsIgnoreCase("list")){
					// wypisz co mamy zapisane w kolekcji ArrayList productsInwarehouse
					System.out.println(productsInwarehouse.toString());
					System.out.println("Produkty w bazie:");
					for (String product : productsInwarehouse){
						System.out.print(product+" ,");
					}
				} else {
					// nie powinnismy tu nigdy trafic!
					System.out.println("?!");
				}
			} else{
				System.out.println("Nieznana komenda!");
			}
		}

	}

}
