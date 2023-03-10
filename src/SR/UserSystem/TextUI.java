package SR.UserSystem;

import java.util.Scanner;

public class TextUI {
    Scanner inputScanner = new Scanner(System.in);

    public boolean CreateNewOrLogin() {
        System.out.println("1) Create new\n2) login to existing");
        String input = inputScanner.next();
        return (input.equals("1"));
    }
}
