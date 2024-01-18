package src;

import java.util.Scanner;

public class GroupExpenses
{
    private final User currentUser;
    private final Scanner sc = new Scanner(System.in);

    public GroupExpenses(User user)
    {
        this.currentUser=user;
    }
    public void groupExpenses() {
        if (currentUser.commonFriends.isEmpty()) {
            System.out.println("\tPlease Add Common Friends To Continue");
            return;
        }
        GroupOperations groupOperations = new GroupOperations(currentUser);

        boolean flag = true;
        while (flag) {
            System.out.println("\n******** Group ********");
            System.out.println("""
                    1 - Add Expenses
                    2 - Balances
                    3 - Add Friends
                    4 - Back""");
            System.out.print("Choose Option : ");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    groupOperations.addExpenses();
                    break;
                case 2:
                    groupOperations.balances();
                    break;
                case 3:
                    groupOperations.groupFriends();
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("\t Invalid Input");
                    NonGroupExpenses nonGroupExpenses = new NonGroupExpenses(currentUser);
                    nonGroupExpenses.nonGroupExpenses();
                    break;
            }
        }
    }
}
