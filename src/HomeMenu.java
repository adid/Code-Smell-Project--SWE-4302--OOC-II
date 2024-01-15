package src;

import java.util.Scanner;

public class HomeMenu
{

    Scanner sc = new Scanner(System.in);

    public void homeMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n********* SPLIT WISE ********");
            System.out.println("""
                    1 - Login
                    2 - Create Account
                    3 - Exit""");
            System.out.print("Choose Option : ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    Login login = new Login();
                    login.login();
                    break;
                case 2:
                    SignUp signUp = new SignUp();
                    signUp.signUp();
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("\t Invalid Input");
                    homeMenu();
                    break;
            }
        }
    }
}
