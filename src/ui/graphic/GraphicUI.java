package ui.graphic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import store.RetailStore;
import store.StoreChain;
import store.StoreSystem;
import ui.UserInterface;

import java.net.URL;


public class GraphicUI implements UserInterface
{
    private Stage stage;
    private StoreSystem system;

    public GraphicUI(StoreSystem system, Stage stage)
    {
        this.system = system;
        this.stage = stage;
    }

    @Override
    public void showSystemController() {
        URL location = getClass().getResource("/fxml/ui_main.fxml");
        FXMLLoader loader = new FXMLLoader(location);
        try {
            TabPane tabPane = loader.load();
            GraphicController gc = loader.getController();
            gc.initForm(this);
            stage.setScene(new Scene(tabPane));
            stage.setTitle("Store System");
            stage.show();
        } catch (Exception ex) {ex.printStackTrace();}
    }

    @Override
    public void showChainController(StoreChain chain) {

    }

    @Override
    public void showStoreController(RetailStore store) {

    }

    @Override
    public StoreSystem getSysNode() {
        return system;
    }

}
