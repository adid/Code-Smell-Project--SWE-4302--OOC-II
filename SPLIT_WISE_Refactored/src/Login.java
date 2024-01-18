package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Login
{
    ArrayList<User> users;
    Scanner sc = new Scanner(System.in);
    User currentUser;

    public Login()
    {
        this.users=Main.users;
    }
    public void login() {
        System.out.println("\n******* Login *******");
        System.out.print("User name : ");
        String name = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();

        for (User value : users)
            if (value.name.equals(name) && value.password.equals(password)) {
                currentUser = value;
                UserMenu userMenu = new UserMenu(currentUser);
                userMenu.userMenu();
                return;
            }
        System.out.println("\tWrong Credentials, Try Again");
        login();
    }
}
