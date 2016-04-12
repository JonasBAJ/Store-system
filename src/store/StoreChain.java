package store;

import Store.Item;
import Store.Unit;
import Store.UnitFactory;
import javafx.collections.FXCollections;

import java.util.List;

/**
 * Created by jonas on 16.4.7.
 */
public class StoreChain
{
    private String chainName;
    private UnitFactory unitFactory;
    private List<RetailStore> retailStores;
    private List<Unit> fullAssortment;

    public StoreChain(String chainName){
        this.chainName = chainName;
        this.unitFactory = new UnitFactory();
        this.retailStores = FXCollections.observableArrayList();
        this.fullAssortment = FXCollections.observableArrayList();
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public List<Unit> getFullAssortment() {
        return fullAssortment;
    }

    public void setFullAssortment(List<Unit> fullAssort) {
        this.fullAssortment = fullAssort;
    }

    public void createStore(String name) {
        this.retailStores.add(new RetailStore(name, this));
    }

    public void createUnit(Item item) {
        fullAssortment.add(unitFactory.createUnit(item));
    }

    public void createUnit(Item item, int amount) {
        fullAssortment.add(unitFactory.createUnit(item, amount));
    }

    public void createUnit(Item item, double weight) {
        fullAssortment.add(unitFactory.createUnit(item, weight));
    }

    public List<RetailStore> getRetailStores() {
        return retailStores;
    }

    public void setRetailStores(List<RetailStore> retailStores) {
        this.retailStores = retailStores;
    }

    public RetailStore getRetailStoreByName(String storeName) {
        for (RetailStore store : retailStores) {
            if (store.getName().equals(storeName))
                return store;
        }
        return null;
    }

    @Override
    public String toString() {
        Integer storeCount = this.retailStores.size();
        Integer assortCount = this.fullAssortment.size();
        return getChainName() + " " + storeCount + " " + assortCount;
    }
}
