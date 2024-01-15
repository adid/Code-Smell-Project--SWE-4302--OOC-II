package src;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageFriends {
    private final User currentUser;
    private final Scanner sc = new Scanner(System.in);
    private final ArrayList<User> users;

    public ManageFriends(User user) {
        this.currentUser = user;
        this.users = Main.users;
    }

    public void addFriend() {
        System.out.println("\n******* Add Friend ********");
        System.out.print("Friend Name : ");
        String friendName = sc.nextLine();

        if (isAlreadyFriend(friendName)) {
            System.out.println("\t" + friendName + " is Already in Your Friends List");
        } else {
            User friend = findUserByName(friendName);
            if (friend != null) {
                currentUser.commonFriends.add(friend);
                friend.commonFriends.add(currentUser);
                System.out.println("\t" + friend.name + " Added Successfully");
            } else {
                System.out.println("\t" + friendName + " Doesn't Have an Account, Invite Sent");
            }
        }
    }

    private boolean isAlreadyFriend(String friendName) {
        return currentUser.commonFriends.stream().anyMatch(friend -> friend.name.equals(friendName));
    }

    private User findUserByName(String name) {
        return users.stream().filter(user -> user.name.equals(name)).findFirst().orElse(null);
    }

    public void removeFriend() {
        if (!currentUser.commonFriends.isEmpty()) {
            System.out.println("********* Remove Friend ********");
            System.out.print("Friend Name :");
            String friendName = sc.nextLine();

            User friendToRemove = findUserByName(friendName);
            if (friendToRemove != null) {
                currentUser.commonFriends.remove(friendToRemove);
                removeFriendExpenses(friendToRemove);
                System.out.println("\t" + friendName + " Removed Successfully");
            } else {
                System.out.println("\t" + friendName + " is not in Your Friends List");
            }
        } else {
            System.out.println("Your Friend List is Empty");
        }
    }

    private void removeFriendExpenses(User friend) {
        int index = currentUser.commonFriends.indexOf(friend);
        currentUser.commonExpenses.remove(index);
    }
}
