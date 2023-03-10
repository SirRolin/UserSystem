import SR.UserSystem.TextUI;
import SR.UserSystem.User;
import SR.UserSystem.UserHandler;

public class Main {
    @SuppressWarnings("ReassignedVariable")
    public static void main(String[] args) {
        // Load login file.
        UserHandler userHandler = new UserHandler();
        userHandler.loadUsers();
        // Request login or creation of new account.
        TextUI textUI = new TextUI();
        boolean createOrLogin = textUI.CreateNewOrLogin();

        User loggedInUser = null;

        if(createOrLogin){
            // Create new User.
            loggedInUser = userHandler.createNewUser();
        } else {
            // Check if there's any account to log on to.
            if (userHandler.getCount() > 0) {
                // Login.
                loggedInUser = userHandler.login();
                // In case we couldn't log in.
                while (loggedInUser == null) {
                    loggedInUser = userHandler.login();
                }
            } else {
                System.out.println("Sorry there's no users to log on to yet, please create one.");
            }
        }
        // Greetings.
        if(loggedInUser != null){
            System.out.println("Greetings " + loggedInUser.getName() + ".");
        }
    }
}