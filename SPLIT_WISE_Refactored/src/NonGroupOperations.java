package src;

import java.util.ArrayList;
import java.util.Scanner;

public class NonGroupOperations implements IExpenses
{
    private final User currentUser;
    private final Scanner sc = new Scanner(System.in);
    ArrayList<User> users;

    int myAmount = 0;
    int friendAmount = 0;

    public NonGroupOperations(User user)
    {
        this.currentUser=user;
        this.users = Main.users;
    }

    public void addExpenses() {
        if (currentUser.commonFriends.isEmpty()) {
            System.out.println("\tPlease Add Friends To Continue");
            UserMenu userMenu = new UserMenu(currentUser);
            userMenu.userMenu();
            return;
        }

        System.out.println("\n****** Add Expenses ******");
        //Choose a Friend
        System.out.println("With me and : ");
        for (int i = 0; i < currentUser.commonFriends.size(); i++)
            System.out.println((i + 1) + ". " + currentUser.commonFriends.get(i).name);
        System.out.print("Select The Friend : ");
        int friend = sc.nextInt() - 1;
        sc.nextLine();
        User friendObj = currentUser.commonFriends.get(friend);

        //Expenses Details
        System.out.print("\nDescription : ");
        String des = sc.nextLine();
        System.out.print("Amount : ");
        int totAmt = sc.nextInt();
        sc.nextLine();
        System.out.print("Paid by (0)me / (1)" + friendObj.name + " : ");
        int option = sc.nextInt();
        sc.nextLine();
        System.out.print("Split (0)Equally / (1)Unequally : ");
        int adjustSplit = sc.nextInt();
        sc.nextLine();

        if (adjustSplit == 1) {
            System.out.print("Me ₹: ");
            myAmount = sc.nextInt();
            sc.nextLine();
            System.out.print(friendObj.name + " ₹: ");
            friendAmount = sc.nextInt();
            sc.nextLine();
        } else {
            myAmount = totAmt / 2;
            friendAmount = totAmt / 2;
        }

        String whoPaid;
        if (option == 0) whoPaid = "You";
        else whoPaid = friendObj.name;

        //Add Expenses in Current User Account
        currentUser.commonExpenses.add(new Expenses(friendObj.name, des, whoPaid, myAmount, friendAmount, totAmt));

        if (whoPaid.equals("You")) whoPaid = friendObj.name;
        else whoPaid = "You";
        friendObj.commonExpenses.add(new Expenses(currentUser.name, des, whoPaid, friendAmount, myAmount, totAmt));
        System.out.println("\tExpenses Added Successfully");
    }

    // Non-Group balances
    public void balances() {
        if (currentUser.commonExpenses.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }
        else {
            System.out.println("\n******** Balances *********");
            for (int i = 0; i < currentUser.commonExpenses.size(); i++) {
                Expenses exp = currentUser.commonExpenses.get(i);
                if (exp.whoPaid.equals("You"))
                    System.out.println("\t" + (i + 1) + ". " + "You paid ₹" + exp.totalAmount + " for " + exp.description + ", " + exp.friendName + " owes ₹" + exp.friendAmount);
                else
                    System.out.println("\t" + (i + 1) + ". " + exp.friendName + " paid ₹" + exp.totalAmount + " for " + exp.description + ", " + "you owes ₹" + exp.myAmount);
            }
        }

        System.out.print("(0)Delete Expense / (1)Back : ");
        int option = sc.nextInt();
        sc.nextLine();
        if (option == 0) {
            System.out.print("\nSelect Expense : ");
            int expense = sc.nextInt() - 1;
            sc.nextLine();
            nonGroupDeleteExpenses(expense);
        } else if (option != 1) {
            System.out.println("\tInvalid Option");
            balances();
        }
    }

    // Non-Group Remove Expenses
    public void nonGroupDeleteExpenses(int option) {
        //Remove in Friend Obj
        fObj:
        for (User value : users)
            if (value.name.equals(currentUser.commonExpenses.get(option).friendName))
                for (int j = 0; j < value.commonExpenses.size(); j++)
                    if (value.commonExpenses.get(j).description.equals(currentUser.commonExpenses.get(option).description) && value.commonExpenses.get(j).totalAmount == currentUser.commonExpenses.get(option).totalAmount) {
                        value.commonExpenses.remove(j);
                        break fObj;
                    }

        // Remove in Current User
        System.out.println("\t" + currentUser.commonExpenses.get(option).description + " expense deleted");
        currentUser.commonExpenses.remove(option);
    }
}
