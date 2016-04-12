package ui.console;

import Store.Item;
import Store.UnitFactory;
import store.RetailStore;

import java.util.Scanner;


public class StoreController extends ConsoleUtility
{
    private RetailStore store;
    private UnitFactory factory;

    public StoreController(RetailStore store) {
        this.store = store;
        factory = new UnitFactory();
        displayStoreMenu();
    }

    private void displayStoreMenu()
    {
        showInstructions();
        Integer choice = getChoice();
        while (choice > 0)
        {
            switch (choice)
            {
                case 1:
                    displayAssortment();
                    break;
                case 2:
                    System.out.println(store.getName());
                    break;
                case 3:
                    System.out.println("Enter: <New store name>");
                    store.setName(getName());
                    break;
                case 4:
                    System.out.println("Cretae new product:");
                    System.out.println("Enter: <Name> <price> <weighable> <amount>");
                    String name = getName();
                    Double price = getDouble();
                    Boolean weighable = getBoolean();
                    Integer amount = getInteger();
                    if (weighable) {
                        System.out.println("Enter product's weight: <weight>");
                        Double weight = getDouble();
                        store.addUnit(factory.createUnit(new Item(name, price, true), weight));
                    }
                    else
                        store.addUnit(factory.createUnit(new Item(name, price, false), amount));
                    break;
                default:
            }
            choice = getChoice();
        }
    }

    private void showInstructions()
    {
        System.out.println("You are working with store chain: " + store.toString());
        System.out.println("----------------------------------------------------");
        System.out.println("|   Chain controller menu:                         |");
        System.out.println("|    1 to display products in this store;          |");
        System.out.println("|    2 to display store name;                      |");
        System.out.println("|    3 to set new store name;                      |");
        System.out.println("|    4 to add new product to this store;           |");
        System.out.println("|    0 to exit store chain menu.                   |");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }

    public void displayAssortment() {
        System.out.printf("%-25s %-15s %-15s\n", "<Unit name>", "<One pcs $>", "<Full $>");
        store.getAssortment().forEach(unit -> {
            Double calculatedPrice = unit.calculatePrice();
            String name = unit.getItem().getName();
            Double price = unit.getItem().getPrice();
            System.out.printf("%-25s %-15s %-15s\n", name, price, calculatedPrice.floatValue());
        });
    }
}
