package ui.console;

import store.RetailStore;
import store.StoreChain;
import store.StoreSystem;
import ui.UserInterface;


public class ConsoleUI extends ConsoleUtility implements UserInterface
{
    private StoreSystem system;

    public ConsoleUI(StoreSystem system) {
        this.system = system;
        showSystemController();
    }

    @Override
    public StoreSystem getSysNode() {
        return system;
    }


    @Override
    public void showSystemController() {
        showInstructions();
        Integer choice = getChoice();
        while (choice > 0) {
            switch (choice) {
                case 1:
                    system.loadTestData();
                    System.out.println("-> Test data was loaded.");
                    break;
                case 2:
                    System.out.println("<Chain name> <Stores> <Assortment>");
                    displayChains();
                    break;
                case 3:
                    System.out.println("<Chain name> <Name> <Assortment>");
                    displayStores();
                    break;
                case 4:
                    System.out.println("<Chain name> <Unit name> <Unit Price> <Full price>");
                    displayChainAssortment();
                    break;
                case 5:
                    System.out.println("Enter: <chain name>");
                    system.createStoreChain(getName());
                    System.out.println("-> New chain has been created!");
                    break;
                case 6:
                    system.getStoreChains().forEach(chain -> System.out.println(chain.getChainName()));
                    System.out.println("Enter: <chain name>");
                    StoreChain chain = system.getChainByName(getName());
                    if (chain != null){
                        System.out.println("Chain has been founded!");
                        showChainController(chain);
                        System.out.println("-> You are back to mainUI.fxml menu.");
                        showInstructions();
                    }
                    else
                        System.out.println("-> No such store chain!");
                    break;
                case 7:
                    System.out.println("Enter: <number of new stores>");
                    createStores(getInteger());
                    break;
                default:
                    System.out.println("-> No such choice!");
                    showInstructions();
            }
            choice = getChoice();
        }
    }

    @Override
    public void showChainController(StoreChain chain) {
        new StoreChainController(chain);
    }

    @Override
    public void showStoreController(RetailStore store) {

    }

    private void createStores(Integer newStores) {
        if (!system.getStoreChains().isEmpty()) {
            for (int i = 0; i < newStores; i++) {
                Integer storeNo = i+1;
                System.out.println("Enter: <store no " + storeNo + " name>");
                String name = getName();
                system.getStoreChains().forEach(storeChain -> storeChain.createStore(name));
            }
            System.out.println("-> New stores were created!");
        }
        else System.out.println("-> You have no store chains!\n" +
                "Please load test data(choice 1) or create new chain(choice 5).");
    }

    public void displayChains() {
        system.getStoreChains().forEach(storeChain -> System.out.println(storeChain.toString()));
    }

    public void displayStores() {
        system.getStoreChains().forEach(storeChain ->
            storeChain.getRetailStores().forEach(retailStore -> System.out.println(retailStore.toString()))
        );
    }

    public void displayChainAssortment() {
        system.getStoreChains().forEach(storeChain ->
            storeChain.getFullAssortment().forEach(unit ->{
                Double calculatedPrice = unit.calculatePrice();
                String unitStr = storeChain.getChainName() + " " +
                        unit.getItem().toString() + " " + calculatedPrice.floatValue();
                System.out.println(unitStr);
            })
        );
    }

    void showInstructions()
    {
        System.out.println("_____________________________________________");
        System.out.println("|   Menu:                                   |");
        System.out.println("|    1 to load test data;                   |");
        System.out.println("|    2 to display loaded store chains data; |");
        System.out.println("|    3 to display loaded stores;            |");
        System.out.println("|    4 to display assortment of chains;     |");
        System.out.println("|    5 to create new store chain;           |");
        System.out.println("|    6 to work with specified store chain;  |");
        System.out.println("|    7 to add stores to all chains;         |");
        System.out.println("|    8 to show instructions;                |");
        System.out.println("|    0 to end the program.                  |");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }
}
