package ui.graphic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import store.StoreSystem;

/**
 * Created by jonas on 16.4.12.
 */
public class SystemWindow {

    @FXML private Button loadDataButton;
    @FXML private Button displayChainsButton;
    @FXML private Button displayStoresButton;
    @FXML private Button displayChainAssortButton;
    @FXML private Button createChainButton;
    @FXML private Button addNewStoresButton;
    @FXML private TextArea consoleField;
    private StoreSystem system;

    public SystemWindow() {

    }

    public void initSystemWindow(StoreSystem system) {
        this.system = system;
        initLoadDataButton();
        initDisplayChainsButton();
    }

    private void initLoadDataButton()
    {
        loadDataButton.setOnAction(event -> {
            consoleField.clear();
            system.loadTestData();
            consoleField.appendText("Test data has been loaded!");
        });
    }

    private void initDisplayChainsButton() {
        displayChainsButton.setOnAction(event -> {
            consoleField.clear();
            String header = String.format("%-15s %-15s %-15s\n", "<Chain name>", "<Store count>", "<Assort count>");
            consoleField.appendText(header);
            system.getStoreChains().forEach(chain -> {
                String[] data = chain.toString().split("\\s+");
                String chainStr = String.format("%-25s %-25s %-15s\n", data[0], data[1], data[2]);
                consoleField.appendText(chainStr);
            });
        });
    }

}
