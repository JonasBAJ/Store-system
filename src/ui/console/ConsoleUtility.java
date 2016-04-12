package ui.console;

import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class ConsoleUtility {

    public Integer getInteger()
    {
        System.out.println("--> Enter integer: ");
        try {
            return new Scanner(System.in).nextInt();
        } catch (InputMismatchException ex){
            System.out.println("-> Not a integer!");
            return getInteger();
        }
    }

    public Double getDouble() {
        System.out.println("--> Enter double: ");
        try {
            return new Scanner(System.in).nextDouble();
        } catch (InputMismatchException ex) {
            System.out.println("-> Not a double!");
            return getDouble();
        }
    }

    public Boolean getBoolean() {
        System.out.println("--> Enter true or false: ");
        try {
            return new Scanner(System.in).nextBoolean();
        } catch (InputMismatchException ex) {
            System.out.println("-> Not a bool value!");
            return getBoolean();
        }
    }

    public String getName() {
        System.out.println("--> Enter name: ");
        String name = new Scanner(System.in).nextLine();
        if (name.length() < 4) {
            System.out.println("-> Min name length is 4 characters!");
            return getName();
        }
        else {
            return name;
        }
    }

    public Integer getChoice()
    {
        System.out.println("--> Enter your choice: ");
        try {
            return new Scanner(System.in).nextInt();
        } catch (InputMismatchException ex){
            System.out.println("-> Not a choice!");
            return getChoice();
        }
    }
}
