package ui.console;

import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import store.StoreSystem;
import ui.UserInterface;
import ui.graphic.GraphicUI;


public class InterfaceFactory
{
    @Contract("_ -> !null")
    public static UserInterface getConsoleUI(StoreSystem system)
    {
        return new ConsoleUI(system);
    }

    @Contract("_ -> !null")
    public static UserInterface getGraphicUI(StoreSystem system, Stage stage)
    {
        return new GraphicUI(system, stage);
    }
}
