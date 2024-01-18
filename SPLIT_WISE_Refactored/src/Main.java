package src;


import java.util.*;

public class Main {
    static ArrayList<User> users = new ArrayList<>(List.of(new User("A", "1111"), new User("B", "2222"), new User("C", "3333")));

    public static void main(String[] args) {
        HomeMenu homeMenu = new HomeMenu();
        homeMenu.homeMenu();
    }

}
