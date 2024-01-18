package src;

import java.util.ArrayList;
import java.util.Scanner;

public class SignUp
{
    ArrayList<User>users;
    Scanner sc = new Scanner(System.in);

    public SignUp()
    {
        this.users= Main.users;
    }

    public void signUp() {
        System.out.println("\n******** SignUp ********");
        System.out.print("User name : ");
        String name = sc.nextLine();

        Login login = new Login();

        for (User value : users)
            if (value.name.equals(name))
            {
                System.out.println("\tUser Name Already Exist, Please Login");
                login.login();
                return;
            }

        System.out.print("Password : ");
        String password = sc.nextLine();
        users.add(new User(name, password));
        System.out.println("\t Account Created Successfully, Login");
        login.login();
    }
}
