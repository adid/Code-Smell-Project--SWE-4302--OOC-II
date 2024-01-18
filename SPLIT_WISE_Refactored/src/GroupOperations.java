package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroupOperations implements IExpenses
{
    private final User currentUser;
    private final Scanner sc = new Scanner(System.in);
    ArrayList<User> users;
    int myAmount = 0;

    public GroupOperations(User user)
    {
        this.currentUser=user;
        this.users = Main.users;
    }

    public void addExpenses() {
        if (currentUser.groupFriends.isEmpty()) {
            System.out.println("\tAdd Friends In Group");
            return;
        }
        System.out.println("\n******** Add Expenses ********");

        // Choose Friends
        System.out.println("With me and : ");
        for (int i = 0; i < currentUser.groupFriends.size(); i++) {
            System.out.println((i + 1) + ". " + currentUser.groupFriends.get(i).name);
        }

        System.out.print("Select Friends (comma-separated): ");
        String positionInput = sc.nextLine();
        String[] selectedPositions = positionInput.split(",");

        int[] friendsPositions = new int[selectedPositions.length];
        for (int i = 0; i < selectedPositions.length; i++) {
            friendsPositions[i] = Integer.parseInt(selectedPositions[i].trim()) - 1;
        }

// Now you can use 'friendsPositions' array to refer to the selected friends


        //Expenses Details
        System.out.println("\n******* Expense details *******");
        System.out.print("Description : ");
        String des = sc.nextLine();
        System.out.print("Amount : ");
        int totAmt = sc.nextInt();
        sc.nextLine();


        //Who-Paid
        System.out.print("Paid by (0)You");
        for (int i = 0; i < friendsPositions.length; i++)
            System.out.print(" / (" + (i + 1) + ")" + currentUser.groupFriends.get(i).name);
        System.out.print(" : ");
        int wp = sc.nextInt() - 1;
        sc.nextLine();
        String whoPaid;
        if (wp == -1) whoPaid = currentUser.name;
        else whoPaid = currentUser.groupFriends.get(wp).name;

        //Split
        System.out.print("Split (0)Equally / (1)Unequally : ");
        int split = sc.nextInt();
        sc.nextLine();

        if (split == 0) {
            myAmount = totAmt / (currentUser.groupFriends.size() + 1);

            ArrayList<Expenses> grpExp = new ArrayList<>(List.of(new Expenses(currentUser.name, des, whoPaid, myAmount, myAmount, totAmt)));

            for (int i = 0; i < currentUser.groupFriends.size(); i++) {
                int friendAmt = totAmt / (currentUser.groupFriends.size() + 1);
                grpExp.add(new Expenses(currentUser.groupFriends.get(i).name, des, whoPaid, friendAmt, myAmount, totAmt));
            }
            currentUser.groupExpenses.add(grpExp);
        }

        //Add Expense To Friends obj
        for (int i = 0; i < currentUser.groupFriends.size(); i++)
            currentUser.groupFriends.get(i).groupExpenses.add(currentUser.groupExpenses.get(currentUser.groupExpenses.size() - 1));

        // Clear Group
        for (User value : users)
            value.groupFriends.clear();

        System.out.println("\tExpense Added Successfully");
    }

    // Group-Balances
    public void balances() {
        if (currentUser.groupExpenses.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }

        for (int i = 0; i < currentUser.groupExpenses.size(); i++) {
            ArrayList<Expenses> crtExp = currentUser.groupExpenses.get(i);
            String des = crtExp.get(0).description;

            String whoPaid;
            if (crtExp.get(0).whoPaid.equals(currentUser.name)) whoPaid = "You";
            else whoPaid = crtExp.get(0).whoPaid;

            System.out.println("\t" + (i + 1) + ". " + des + " :-");
            System.out.print("\t\t" + whoPaid + " paid ₹" + crtExp.get(0).totalAmount + " for " + des);

            for (Expenses expenses : crtExp)
                if (!crtExp.get(0).whoPaid.equals(expenses.friendName)) {
                    String name;
                    if (currentUser.name.equals(expenses.friendName)) name = "You";
                    else name = expenses.friendName;
                    System.out.print(" " + name + " owes ₹" + expenses.myAmount + " &");
                }
        }
    }

    // Group Friends
    public void groupFriends() {
        System.out.println("\nAdd Friends : ");
        for (int i = 0; i < currentUser.commonFriends.size(); i++)
            System.out.println("\t" + (i + 1) + ". " + currentUser.commonFriends.get(i).name);

        System.out.print("Select Friends : ");
        String position = sc.nextLine();
        String[] friendPosition = position.split(",");

        for (String s : friendPosition) {
            int pos = Integer.parseInt(s) - 1;
            currentUser.groupFriends.add(currentUser.commonFriends.get(pos));
            currentUser.commonFriends.get(pos).groupFriends.add(currentUser);
        }

        for (int i = 0; i < currentUser.groupFriends.size(); i++)
            for (int j = 0; j < currentUser.groupFriends.size(); j++)
                if (!currentUser.groupFriends.get(i).name.equals(currentUser.groupFriends.get(j).name))
                    currentUser.groupFriends.get(i).groupFriends.add(currentUser.groupFriends.get(j));

        System.out.println("\tFriends Added Successfully");
    }
}
