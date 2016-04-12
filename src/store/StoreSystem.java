package store;

import Store.Item;
import javafx.collections.FXCollections;

import java.util.List;


public class StoreSystem {

    private static StoreSystem instance = null;
    private List<StoreChain> storeChains;

    protected StoreSystem() {
        this.storeChains = FXCollections.observableArrayList();
    }

    public static StoreSystem getInstance() {
        if (instance == null)
            return new StoreSystem();
        else
            return instance;
    }

    public void createStoreChain(String chainName)
    {
        this.storeChains.add(new StoreChain(chainName));
    }

    public List<StoreChain> getStoreChains() {
        return this.storeChains;
    }

    public StoreChain getChainByName(String chainName)
    {
        for (StoreChain chain : storeChains) {
            if (chain.getChainName().equals(chainName))
                return chain;
        }
        return null;
    }

    public void loadTestData() {
        createStoreChain("Chain_1");
        createStoreChain("Chain_2");
        createStoreChain("Chain_3");
        createStoreChain("Chain_4");

        getStoreChains().forEach(storeChain -> {
            storeChain.createUnit(new Item("Carrots", 100.10, true), 0.5);
            storeChain.createUnit(new Item("Chicken nuggets", 100.10, false), 12);
            storeChain.createUnit(new Item("Milk", 5.29, false));
        });

        getStoreChains().forEach(storeChain -> {
            storeChain.createStore("Store_1");
            storeChain.createStore("Store_2");
            storeChain.createStore("Store_3");
        });
    }
}
