package store;

import Store.Unit;

import java.util.List;



public class RetailStore
{
    private List<Unit> assortment;
    private String chainName;
    private String name;

    public RetailStore(String name, StoreChain chain) {
        this.name = name;
        this.assortment = chain.getFullAssortment();
        this.chainName = chain.getChainName();
    }

    public List<Unit> getAssortment() {
        return assortment;
    }

    public void setAssortment(List<Unit> assortment) {
        this.assortment = assortment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUnit(Unit unit){
        assortment.add(unit);
    }

    @Override
    public String toString() {
        Integer assortmentCount = this.assortment.size();
        return this.chainName + " " + getName() + " " + assortmentCount.toString();
    }
}
