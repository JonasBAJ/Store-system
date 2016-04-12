package ui.graphic;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Created by jonas on 16.4.12.
 */
public class GraphicController
{
    @FXML private HBox systemHBox;
    @FXML private HBox chainHBox;
    @FXML private HBox storeHBox;
    @FXML private TabPane tabPane;
    private TextField submitField;
    private Button submitButton;
    private VBox leftVBox;
    private VBox rightVBox;
    private TextArea textArea;
    private GraphicUI ui;
    private Integer minButtonWidth;

    public GraphicController() {}

    public void initForm(GraphicUI ui) {
        this.ui = ui;
        minButtonWidth = 177;
        iniTabPane();
        initSystemPane();
    }

    private void initSystemPane()
    {
        initLeftVBox();
        initRightVBox();
        initTextArea();
        initSubmitFields();
        initDataButton();
        initDisplayChainsButton();
        initDisplayChainStores();
        initNewChainButton();
    }

    private void initLeftVBox() {
        this.leftVBox = new VBox(12);
        leftVBox.setAlignment(Pos.CENTER);
        systemHBox.getChildren().add(leftVBox);
    }
    
    private void initRightVBox() {
        this.rightVBox = new VBox(12);
        rightVBox.setAlignment(Pos.CENTER);
        systemHBox.getChildren().add(rightVBox);
    }

    private void initTextArea() {
        this.textArea = new TextArea();
        textArea.setEditable(false);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        rightVBox.getChildren().add(textArea);
    }
    
    private void initSubmitFields() {
        this.submitField = new TextField();
        this.submitButton = new Button("Submit");
        HBox.setHgrow(submitField, Priority.ALWAYS);
        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(submitField, submitButton);
        rightVBox.getChildren().add(hbox);
    }

    private void initDataButton() {
        Button loadData = new Button("Load test data");
        loadData.setMinWidth(minButtonWidth);
        loadData.setOnAction(event -> {
            ui.getSysNode().loadTestData();
            textArea.clear();
            textArea.appendText("Test data has been loaded!");
        });
        leftVBox.getChildren().add(loadData);
    }

    private void initDisplayChainsButton() {
        Button displayChains = new Button("Display store chains");
        displayChains.setMinWidth(minButtonWidth);
        displayChains.setOnAction(event -> {
            textArea.clear();
            String header = String.format("%-10s %-10s %-10s\n", "<Chain>", "<Store count>", "<Assort count>");
            textArea.appendText(header);
            ui.getSysNode().getStoreChains().forEach(chain -> {
                String chName = chain.getChainName();
                Integer storeCount = chain.getRetailStores().size();
                Integer assortCount = chain.getFullAssortment().size();
                String chainStr = String.format("%-15s %-25s %-25s\n", chName, storeCount, assortCount);
                textArea.appendText(chainStr);
            });
        });
        leftVBox.getChildren().add(displayChains);
    }

    private void initDisplayChainStores() {
        Button displayStores = new Button("Display stores");
        displayStores.setMinWidth(minButtonWidth);
        displayStores.setOnAction(event -> {
            textArea.clear();
            String header = String.format("%-10s %-10s %-10s\n", "<Chain>", "<Store>", "<Assort count>");
            textArea.appendText(header);
            ui.getSysNode().getStoreChains().forEach(chain ->
                chain.getRetailStores().forEach(store -> {
                    String chName = chain.getChainName();
                    String storeName = store.getName();
                    Integer assortCount = store.getAssortment().size();
                    String chainStr = String.format("%-13s %-12s %-10s\n", chName, storeName, assortCount);
                    textArea.appendText(chainStr);
                })
            );
        });
        leftVBox.getChildren().add(displayStores);
    }

    private void initNewChainButton() {
        Button newChain = new Button("Create new chain");
        newChain.setMinWidth(minButtonWidth);
        newChain.setOnAction(event -> {
            textArea.clear();
            textArea.appendText("In text field below enter:\n<New Chain name> and press submit.");
            submitButton.setOnAction(event1 -> {
                if (submitField.getText().length() < 4) {
                    textArea.clear();
                    textArea.appendText("Your name is less than 4 characters!");
                }
                else {
                    textArea.clear();
                    textArea.appendText("New chain has been created!");
                    ui.getSysNode().createStoreChain(submitField.getText());
                    submitField.clear();
                }
            });
        });
        leftVBox.getChildren().add(newChain);
    }

    private void iniTabPane() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue.getText()) {
                case "Store system":
                    System.out.println("SS");
                    break;
                case "Store chain":
                    System.out.println("SC");
                    break;
                case "Store":
                    System.out.println("S");
                    break;
            }
        });
    }


}
