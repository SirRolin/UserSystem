package SR.UserSystem;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
public class UserHandler {
    private final ArrayList<User> users = new ArrayList<>();
    public void loadUsers() {
        try {
            File UserFile = new File("Users.txt");
            Scanner scanner = new Scanner(UserFile);
            while(scanner.hasNextLine()){
                String[] userLine = scanner.nextLine().split(", ");
                switch (userLine.length) {
                    case 2, 3 -> {
                        User newUser = new User(userLine[0], userLine[1]);
                        users.add(newUser);
                    }
                    default -> System.out.println("something's wrong with the users file");
                }
            }
        } catch (FileNotFoundException notFoundException) {
            File UserFile = new File("Users.txt");
            try {
               UserFile.createNewFile();
            } catch (IOException IO_exception) {
                System.out.println("No access to file Users.txt.");
            }
        }
    }

    public void saveUsers() {
        try {
            FileWriter userWriter = new FileWriter("Users.txt");
            for(User user: users) {
                userWriter.write(user.getName() + ", " + user.getPassword());
            }
            userWriter.close();
        } catch (IOException IO_exception) {
            System.out.println("No access to file Users.txt.");
        }
    }

    @SuppressWarnings("ReassignedVariable")
    public User createNewUser(){
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Name:");
        String name = inputScanner.nextLine().trim();
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        //System.out.println();
        System.out.print("Password: ");
        String pass = inputScanner.nextLine().trim();
        User user = new User(name, pass);
        users.add(user);
        saveUsers();
        return user;
    }

    public User login() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Initialising login");
        System.out.print("Name:");
        String name = inputScanner.nextLine();
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        System.out.print("Password: ");
        String pass = inputScanner.nextLine();
        for(User user: users){
            if(user.getName().equals(name)){
                if(user.getPassword().equals(pass)){
                    return user;
                } else {
                    System.out.println("Wrong password.");
                    break;
                }
            }
        }
        System.out.println("No user with that name.");
        return null;
    }

    public int getCount() {
        return users.size();
    }
}
