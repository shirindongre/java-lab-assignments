/**
 * Abstract base class representing a generic bank account.
 * Provides common attributes and operations that all account types share.
 * Child classes override deposit() and withdraw() to implement
 * account-specific behaviour (polymorphism).
 */
public abstract class Account {

    // ------------------------------------------------------------------ fields
    private static int nextAccountNumber = 1000;   // auto-increment seed

    protected int    accountNumber;
    protected int    customerId;
    protected double balance;
    protected String accountType;

    // --------------------------------------------------------------- constructor
    public Account(int customerId, double initialDeposit, String accountType) {
        this.accountNumber = nextAccountNumber++;
        this.customerId    = customerId;
        this.balance       = initialDeposit;
        this.accountType   = accountType;
    }

    // ------------------------------------------------------------------ methods

    /**
     * Deposits the given amount into the account.
     * Child classes may override to add type-specific logic.
     *
     * @param amount the amount to deposit (must be > 0)
     */
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("  [DEPOSIT] Rs. %.2f deposited. New balance: Rs. %.2f%n", amount, balance);
    }

    /**
     * Withdraws the given amount from the account.
     * Child classes override this to enforce specific rules (e.g. minimum balance).
     *
     * @param amount the amount to withdraw (must be > 0 and ≤ balance)
     */
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Withdrawal amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.println("  [ERROR] Insufficient balance.");
            return;
        }
        balance -= amount;
        System.out.printf("  [WITHDRAW] Rs. %.2f withdrawn. New balance: Rs. %.2f%n", amount, balance);
    }

    /**
     * Displays a summary of the account.
     * Child classes call super.displayAccountInfo() then append extra details.
     */
    public void displayAccountInfo() {
        System.out.printf("  Account No  : %d%n", accountNumber);
        System.out.printf("  Type        : %s%n", accountType);
        System.out.printf("  Balance     : Rs. %.2f%n", balance);
    }

    // ------------------------------------------------------------------ getters
    public int    getAccountNumber() { return accountNumber; }
    public int    getCustomerId()    { return customerId; }
    public double getBalance()       { return balance; }
    public String getAccountType()   { return accountType; }
}