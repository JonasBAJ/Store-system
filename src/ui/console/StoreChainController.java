package ui.console;

import store.RetailStore;
import store.StoreChain;


public class StoreChainController extends ConsoleUtility
{
    private StoreChain chain;

    public StoreChainController(StoreChain chain)
    {
        this.chain = chain;
        displayChainMenu();
    }

    private void displayChainMenu()
    {
        showInstructions();
        Integer choice = getChoice();
        while (choice > 0)
        {
            switch (choice)
            {
                case 1:
                    displayStores();
                    break;
                case 2:
                    displayAssortment();
                    break;
                case 3:
                    System.out.println("Enter: <store name>");
                    chain.createStore(getName());
                    break;
                case 4:
                    System.out.println("Enter: <new chain name>");
                    chain.setChainName(getName());
                    break;
                case 5:
                    System.out.println("Enter: <number of new stores>");
                    createStores(getInteger());
                    break;
                case 6:
                    System.out.println(chain.getChainName());
                    break;
                case 7:
                    System.out.println("Enter name: <default store name>");
                    normalizeStoreNames(getName());
                    System.out.println("-> Store names were normalized.");
                    break;
                case 8:

                    break;
                case 9:
                    chain.getRetailStores().forEach(store -> System.out.println(store.getName()));
                    System.out.println("Enter name: <Store name>");
                    RetailStore store = chain.getRetailStoreByName(getName());
                    if (store != null) {
                        System.out.println("Store has been founded!");
                        new StoreController(store);
                        System.out.println("-> You are back to store chain menu.");
                        showInstructions();
                    }
                    else System.out.println("-> No such store!");
                    break;
                default:
                    System.out.println("-> No such choice!");
                    showInstructions();
            }
            choice = getChoice();
        }
    }

    private void normalizeStoreNames(String name)
    {
        Integer counter = 1;
        for(RetailStore store : chain.getRetailStores()) {
            store.setName(name.concat("_" + counter.toString()));
            counter++;
        }
    }

    private void showInstructions()
    {
        System.out.println("You are working with store chain: " + chain.toString());
        System.out.println("----------------------------------------------------");
        System.out.println("|   Chain controller menu:                         |");
        System.out.println("|    1 to display stores in this chain;            |");
        System.out.println("|    2 to display full assortment;                 |");
        System.out.println("|    3 to create and add new store to chain;       |");
        System.out.println("|    4 to change store chain name;                 |");
        System.out.println("|    5 to add multiple new stores;                 |");
        System.out.println("|    6 to display store chain name;                |");
        System.out.println("|    7 to normalize store names;                   |");
        System.out.println("|    8 to add new assortment unit to this chain;   |");
        System.out.println("|    9 to work with specific store;                |");
        System.out.println("|    0 to exit store chain menu.                   |");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }

    private void displayStores() {
        System.out.printf("%-20s %-15s %-15s\n", "<Chain name>", "<Store name>", "<Assort qty>");
        chain.getRetailStores().forEach(store -> {
            Integer assort = store.getAssortment().size();
            System.out.printf("%-20s %-15s %-15s\n", chain.getChainName(), store.getName(), assort);
        });
    }

    private void displayAssortment() {
        System.out.printf("%-25s %-15s %-15s\n", "<Unit name>", "<One pcs $>", "<Full $>");
        chain.getFullAssortment().forEach(unit ->{
            Double calculatedPrice = unit.calculatePrice();
            String name = unit.getItem().getName();
            Double price = unit.getItem().getPrice();
            System.out.printf("%-25s %-15s %-15s\n", name, price, calculatedPrice.floatValue());
        });
    }

    private void createStores(Integer newStores) {
            for (int i = 0; i < newStores; i++) {
                Integer storeNo = i+1;
                System.out.println("Enter: <store no " + storeNo + " name>");
                chain.createStore(getName());
            }
            System.out.println("-> New stores were added!");
    }
}
