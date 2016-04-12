package ui;

import store.RetailStore;
import store.StoreChain;
import store.StoreSystem;

/**
 * Created by jonas on 16.4.10.
 */
public interface UserInterface
{
    void showSystemController();
    void showChainController(StoreChain chain);
    void showStoreController(RetailStore store);
    StoreSystem getSysNode();
}
