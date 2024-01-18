import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static ArrayList<User> user = new ArrayList<>(List.of(new User("A", "1111"), new User("B", "2222"), new User("C", "3333")));
    static User currentUser;

    public static void main(String[] args) {
        homeMenu();
    }

    // Home menu Options
    private static void homeMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n********* SPLIT WISE ********");
            System.out.println("""
                    1 - Login
                    2 - Create Account
                    3 - Exit""");
            System.out.print("Choose Option : ");
            Integer option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
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

    // Sign-Up
    private static void signUp() {
        System.out.println("\n******** SignUp ********");
        System.out.print("User name : ");
        String name = sc.nextLine();

        for (User value : user)
            if (value.name.equals(name)) {
                System.out.println("\tUser Name Already Exist, Please Login");
                login();
                return;
            }

        System.out.print("Password : ");
        String password = sc.nextLine();
        user.add(new User(name, password));
        System.out.println("\t Account Created Successfully, Login");
        login();
    }

    //User Login
    private static void login() {
        System.out.println("\n******* Login *******");
        System.out.print("User name : ");
        String name = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();

        for (User value : user)
            if (value.name.equals(name) && value.password.equals(password)) {
                currentUser = value;
                userMenu();
                return;
            }
        System.out.println("\tWrong Credentials, Try Again");
        login();
    }

    // User Options
    private static void userMenu() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n********** Welcome Mr." + currentUser.name + " ***********");
            System.out.println("""
                    1 - Non Group Expenses
                    2 - Group Expenses
                    3 - Add Friends
                    4 - Remove Friends
                    5 - Back""");
            System.out.print("Choose Option : ");
            Integer option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    nonGroupExpenses();
                    break;
                case 2:
                    groupExpenses();
                    break;
                case 3:
                    addFriend();
                    break;
                case 4:
                    removeFriend();
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

    //************************************* Non-Group Expenses **********************************************
    // Non Group Expenses

    private static void nonGroupExpenses() {
        boolean flag = true;
        while (flag) {
            System.out.println("\n********* Non Group *******");
            System.out.println("""
                    1 - Add Expenses
                    2 - Balances
                    3 - Back""");
            System.out.print("Choose Option : ");
            Integer option = sc.nextInt();
            switch (option) {
                case 1:
                    nonGroupAddExpenses();
                    break;
                case 2:
                    nonGroupBalances();
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

    // No-Group Add Expenses
    private static void nonGroupAddExpenses() {
        if (currentUser.commonFriends.isEmpty()) {
            System.out.println("\tPlease Add Friends To Continue");
            userMenu();
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

        int myAmt = 0;
        int friendAmt = 0;
        if (adjustSplit == 1) {
            System.out.print("Me ₹: ");
            myAmt = sc.nextInt();
            sc.nextLine();
            System.out.print(friendObj.name + " ₹: ");
            friendAmt = sc.nextInt();
            sc.nextLine();
        } else {
            myAmt = totAmt / 2;
            friendAmt = totAmt / 2;
        }

        String whoPaid;
        if (option == 0) whoPaid = "You";
        else whoPaid = friendObj.name;

        //Add Expenses in Current User Account
        currentUser.commonExpenses.add(new User.Expenses(friendObj.name, des, whoPaid, myAmt, friendAmt, totAmt));

        if (whoPaid.equals("You")) whoPaid = friendObj.name;
        else whoPaid = "You";
        friendObj.commonExpenses.add(new User.Expenses(currentUser.name, des, whoPaid, friendAmt, myAmt, totAmt));
        System.out.println("\tExpenses Added Successfully");
    }

    // Non-Group balances
    private static void nonGroupBalances() {
        if (currentUser.commonExpenses.isEmpty()) {
            System.out.println("\tN/A");
            return;
        } 
        else {
            System.out.println("\n******** Balances *********");
            for (int i = 0; i < currentUser.commonExpenses.size(); i++) {
                User.Expenses exp = currentUser.commonExpenses.get(i);
                if (exp.whoPaid.equals("You"))
                    System.out.println("\t" + (i + 1) + ". " + "You paid ₹" + exp.totAmt + " for " + exp.description + ", " + exp.friendName + " owes ₹" + exp.friendAmt);
                else
                    System.out.println("\t" + (i + 1) + ". " + exp.friendName + " paid ₹" + exp.totAmt + " for " + exp.description + ", " + "you owes ₹" + exp.myAmt);
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
            nonGroupBalances();
        }
    }

    // Non-Group Remove Expenses
    private static void nonGroupDeleteExpenses(int option) {
        //Remove in Friend Obj
        fObj:
        for (User value : user)
            if (value.name.equals(currentUser.commonExpenses.get(option).friendName))
                for (int j = 0; j < value.commonExpenses.size(); j++)
                    if (value.commonExpenses.get(j).description.equals(currentUser.commonExpenses.get(option).description) && value.commonExpenses.get(j).totAmt == currentUser.commonExpenses.get(option).totAmt) {
                        value.commonExpenses.remove(j);
                        break fObj;
                    }

        // Remove in Current User
        System.out.println("\t" + currentUser.commonExpenses.get(option).description + " expense deleted");
        currentUser.commonExpenses.remove(option);
    }

    //******************************************Group expenses ************************************
    // Group Expenses

    private static void groupExpenses() {
        if (currentUser.commonFriends.isEmpty()) {
            System.out.println("\tPlease Add Common Friends To Continue");
            return;
        }

        boolean flag = true;
        while (flag) {
            System.out.println("\n******** Group ********");
            System.out.println("""
                    1 - Add Expenses
                    2 - Balances
                    3 - Add Friends
                    4 - Back""");
            System.out.print("Choose Option : ");
            Integer option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    groupAddExpenses();
                    break;
                case 2:
                    groupBalances();
                    break;
                case 3:
                    groupFriends();
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("\t Invalid Input");
                    nonGroupExpenses();
                    break;
            }
        }
    }


    // Add Group Expenses
    private static void groupAddExpenses() {
        if (currentUser.groupFriends.isEmpty()) {
            System.out.println("\tAdd Friends In Group");
            return;
        }
        System.out.println("\n******** Add Expenses ********");

        //Choose Friends
        System.out.println("With me and : ");
        for (int i = 0; i < currentUser.groupFriends.size(); i++)
            System.out.println((i + 1) + ". " + currentUser.groupFriends.get(i).name);
        System.out.print("Select Friends : ");
        String position = sc.nextLine();
        String[] fp = position.split(",");

        int[] friendsPosition = new int[fp.length];
        for (int i = 0; i < fp.length; i++)
            friendsPosition[i] = Integer.parseInt(fp[i]) - 1;

        //Expenses Details
        System.out.println("\n******* Expense details *******");
        System.out.print("Description : ");
        String des = sc.nextLine();
        System.out.print("Amount : ");
        int totAmt = sc.nextInt();
        sc.nextLine();


        //Who-Paid
        System.out.print("Paid by (0)You");
        for (int i = 0; i < friendsPosition.length; i++)
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
        int myAmt = 0;

        if (split == 0) {
            myAmt = totAmt / (currentUser.groupFriends.size() + 1);

            ArrayList<User.Expenses> grpExp = new ArrayList<>(List.of(new User.Expenses(currentUser.name, des, whoPaid, myAmt, myAmt, totAmt)));

            for (int i = 0; i < currentUser.groupFriends.size(); i++) {
                int friendAmt = totAmt / (currentUser.groupFriends.size() + 1);
                grpExp.add(new User.Expenses(currentUser.groupFriends.get(i).name, des, whoPaid, friendAmt, myAmt, totAmt));
            }
            currentUser.groupExpenses.add(grpExp);
        } else {

        }

        //Add Expense To Friends obj
        for (int i = 0; i < currentUser.groupFriends.size(); i++)
            currentUser.groupFriends.get(i).groupExpenses.add(currentUser.groupExpenses.get(currentUser.groupExpenses.size() - 1));

        // Clear Group
        for (User value : user)
            value.groupFriends.clear();

        System.out.println("\tExpense Added Successfully");
    }

    // Group-Balances
    private static void groupBalances() {
        if (currentUser.groupExpenses.isEmpty()) {
            System.out.println("\tN/A");
            return;
        }

        for (int i = 0; i < currentUser.groupExpenses.size(); i++) {
            ArrayList<User.Expenses> crtExp = currentUser.groupExpenses.get(i);
            String des = crtExp.get(0).description;

            String whoPaid;
            if (crtExp.get(0).whoPaid.equals(currentUser.name)) whoPaid = "You";
            else whoPaid = crtExp.get(0).whoPaid;

            System.out.println("\t" + (i + 1) + ". " + des + " :-");
            System.out.print("\t\t" + whoPaid + " paid ₹" + crtExp.get(0).totAmt + " for " + des);

            for (int j = 0; j < crtExp.size(); j++)
                if (!crtExp.get(0).whoPaid.equals(crtExp.get(j).friendName)) {
                    String name;
                    if (currentUser.name.equals(crtExp.get(j).friendName)) name = "You";
                    else name = crtExp.get(j).friendName;
                    System.out.print(" " + name + " owes ₹" + crtExp.get(j).myAmt + " &");
                }
        }
    }

    // Group Friends
    private static void groupFriends() {
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

    // Add Friend
    private static void addFriend() {
        System.out.println("\n******* Add Friend ********");
        System.out.print("Friend Name : ");
        String friendName = sc.nextLine();

        if (!currentUser.commonFriends.isEmpty()) {
            for (int i = 0; i < currentUser.commonFriends.size(); i++)
                if (currentUser.commonFriends.get(i).name.equals(friendName)) {
                    System.out.println("\t" + friendName + " is Already in Your Friends List");
                    return;
                }
        }

        for (User value : user)
            if (friendName.equals(value.name)) {
                currentUser.commonFriends.add(value);
                value.commonFriends.add(currentUser);
                System.out.println("\t" + value.name + " Added Successfully");
                return;
            }
        System.out.println("\t" + friendName + " Doesn't Have Account, Invite Sent");
    }

    // Remove Friend
    private static void removeFriend() {
        if(!currentUser.commonFriends.isEmpty()){
            System.out.println("********* Remove Friend ********");
            System.out.print("Friend Name :");
            String name=sc.nextLine();
            int in=currentUser.commonFriends.indexOf(name);
            currentUser.commonFriends.remove(name);
            currentUser.commonExpenses.remove(in);
            return ;
        }
        else{
            System.out.println("Your Friend List is Empty");
            return;
        }
    }

}
