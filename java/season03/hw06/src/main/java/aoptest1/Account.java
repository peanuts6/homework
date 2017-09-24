package aoptest1;

/**
 * Created by xqy on 17/9/21.
 */
public class Account {
    private String username;
    private int balance;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "Account [username=" + username + ", balance=" + balance + "]";
    }
}
