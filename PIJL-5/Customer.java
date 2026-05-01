import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank customer who can hold multiple accounts.
 */
public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String phone;
    private List<Account> accounts;

    public Customer(int customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }

    // --- Account Management ---

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // --- Getters ---

    public int getCustomerId() { return customerId; }
    public String getName()    { return name; }
    public String getEmail()   { return email; }
    public String getPhone()   { return phone; }

    // --- Display ---

    public void displayCustomerInfo() {
        System.out.println("=".repeat(60));
        System.out.printf("Customer ID : %d%n", customerId);
        System.out.printf("Name        : %s%n", name);
        System.out.printf("Email       : %s%n", email);
        System.out.printf("Phone       : %s%n", phone);
        System.out.printf("Accounts    : %d%n", accounts.size());
        System.out.println("-".repeat(60));

        if (accounts.isEmpty()) {
            System.out.println("  No accounts linked to this customer.");
        } else {
            for (Account acc : accounts) {
                acc.displayAccountInfo();
                System.out.println("-".repeat(60));
            }
        }
    }
}