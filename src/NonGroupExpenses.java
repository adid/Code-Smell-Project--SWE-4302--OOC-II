package src;

import java.util.Scanner;

public class NonGroupExpenses
{
    private final User currentUser;
    private final Scanner sc = new Scanner(System.in);

    public NonGroupExpenses(User user)
    {
        this.currentUser=user;
    }
    public void nonGroupExpenses() {
        boolean flag = true;
        NonGroupOperations nonGroupOperations = new NonGroupOperations(currentUser);
        while (flag) {
            System.out.println("\n********* Non Group *******");
            System.out.println("""
                    1 - Add Expenses
                    2 - Balances
                    3 - Back""");
            System.out.print("Choose Option : ");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    nonGroupOperations.nonGroupAddExpenses();
                    break;
                case 2:
                    nonGroupOperations.nonGroupBalances();
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("\t Invalid Input");
                    nonGroupExpenses();
                    break;
            }
        }
    }
}
