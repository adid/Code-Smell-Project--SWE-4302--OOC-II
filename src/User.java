package src;

import java.util.*;

public class User {
    String name;
    String password;
    int groupAmount;

    //General constructor
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //Group expense constructor
    public User(String name, int groupAmount) {
        this.name = name;
        this.groupAmount = groupAmount;
    }

    //Friends list
    ArrayList<User> commonFriends = new ArrayList<>();
    ArrayList<User> groupFriends = new ArrayList<>();

    //Grp & Cmn Expenses
    ArrayList<Expenses> commonExpenses = new ArrayList<>();
    ArrayList<ArrayList<Expenses>> groupExpenses = new ArrayList<>();
}
