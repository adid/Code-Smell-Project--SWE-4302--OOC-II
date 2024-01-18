package src;

import java.util.Scanner;

public class UserMenu
{
    User currentUser;
    Scanner sc = new Scanner(System.in);

    public UserMenu(User user)
    {
        this.currentUser= user;
    }
    public void userMenu() {
        boolean flag = true;
        ManageFriends manageFriends = new ManageFriends(currentUser);
        while (flag) {
            System.out.println("\n********** Welcome Mr." + currentUser.name + " ***********");
            System.out.println("""
                    1 - Non Group Expenses
                    2 - Group Expenses
                    3 - Add Friends
                    4 - Remove Friends
                    5 - Back""");
            System.out.print("Choose Option : ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    NonGroupExpenses nonGroupExpenses = new NonGroupExpenses(currentUser);
                    nonGroupExpenses.nonGroupExpenses();
                    break;
                case 2:
                    GroupExpenses groupExpenses = new GroupExpenses(currentUser);
                    groupExpenses.groupExpenses();
                    break;
                case 3:
                    manageFriends.addFriend();
                    break;
                case 4:
                    manageFriends.removeFriend();
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("\t Invalid Input");
                    userMenu();
                    break;
            }
        }
    }
}
