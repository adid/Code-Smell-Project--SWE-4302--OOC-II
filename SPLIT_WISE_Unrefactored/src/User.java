package src;

import java.util.*;

public class User {
    String name;
    String password;
    int grpAmt;

    //General constructor
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //Group expense constructor
    public User(String name, int grpAmt) {
        this.name = name;
        this.grpAmt = grpAmt;
    }

    //Friends list
    ArrayList<User> commonFriends = new ArrayList<>();
    ArrayList<User> groupFriends = new ArrayList<>();

    //Grp & Cmn Expenses
    ArrayList<Expenses> commonExpenses = new ArrayList<>();
    ArrayList<ArrayList<Expenses>> groupExpenses = new ArrayList<>();


    //Non group expenses class
    static class Expenses {
        String friendName;
        String description;
        String whoPaid;
        int myAmt;
        int friendAmt;
        int totAmt;

        public Expenses(String friendName, String description, String whoPaid, int myAmt, int friendAmt, int totAmt) {
            this.friendName = friendName;
            this.description = description;
            this.whoPaid = whoPaid;
            this.myAmt = myAmt;
            this.friendAmt = friendAmt;
            this.totAmt = totAmt;
        }
    }
}
