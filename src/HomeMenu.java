public class HomeMenu
{
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
}
