import javafx.application.Application;
import javafx.stage.Stage;
import store.StoreSystem;
import ui.UserInterface;
import ui.console.InterfaceFactory;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by jonas on 16.4.7.
 */
public class Main extends Application {

    public static void main(String[] args) {
        printGreetings();
        Integer choice = getChoice();

        if (choice == 1)
            InterfaceFactory.getConsoleUI(StoreSystem.getInstance());
        else
            launch(args);
        printGoodBye();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StoreSystem system = StoreSystem.getInstance();
        UserInterface ui = InterfaceFactory.getGraphicUI(system, primaryStage);
        ui.showSystemController();
    }

    private static Integer getChoice()
    {
        System.out.println("--> Enter your choice: ");
        try {
            return new Scanner(System.in).nextInt();
        }catch (InputMismatchException ex){
            System.out.println("-> Not a choice!");
            return getChoice();
        }
    }

    private static void printGreetings() {
        System.out.println("____________________________________________________________");
        System.out.println("|    Welcome to Store management system!                   |");
        System.out.println("|     1 if you want to use console interface.              |");
        System.out.println("|     2 if you want to use graphic user interface.         |");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }

    private static void printGoodBye() {
        System.out.println("--> Let the force be with you!");
    }
}
