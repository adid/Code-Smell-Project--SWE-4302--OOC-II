package src;

public class Expenses {
    String friendName;
    String description;
    String whoPaid;
    int myAmount;
    int friendAmount;
    int totalAmount;

    public Expenses(String friendName, String description, String whoPaid, int myAmount, int friendAmount, int totalAmount) {
        this.friendName = friendName;
        this.description = description;
        this.whoPaid = whoPaid;
        this.myAmount = myAmount;
        this.friendAmount = friendAmount;
        this.totalAmount = totalAmount;
    }
}
