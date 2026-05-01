/**
 * A savings account that:
 *  - Enforces a minimum balance (cannot withdraw below it).
 *  - Earns interest that can be calculated and credited at any time.
 */
public class SavingsAccount extends Account {

    private static final double MIN_BALANCE       = 1000.0;  // Rs. 1,000 minimum
    private static final double INTEREST_RATE     = 0.04;    // 4 % per annum

    private double interestEarned;

    // --------------------------------------------------------------- constructor
    public SavingsAccount(int customerId, double initialDeposit) {
        super(customerId, initialDeposit, "Savings Account");
        this.interestEarned = 0.0;

        if (initialDeposit < MIN_BALANCE) {
            System.out.printf(
                "  [WARNING] Initial deposit Rs. %.2f is below the minimum balance of Rs. %.2f.%n",
                initialDeposit, MIN_BALANCE);
        }
    }

    // --------------------------------------------------------------- overrides

    /**
     * Deposits amount and prints a savings-specific confirmation.
     */
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Deposit amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf(
            "  [SAVINGS DEPOSIT] Rs. %.2f deposited. New balance: Rs. %.2f%n", amount, balance);
    }

    /**
     * Withdraws only if the remaining balance stays at or above MIN_BALANCE.
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Withdrawal amount must be positive.");
            return;
        }
        if (balance - amount < MIN_BALANCE) {
            System.out.printf(
                "  [ERROR] Cannot withdraw Rs. %.2f. Minimum balance of Rs. %.2f must be maintained.%n",
                amount, MIN_BALANCE);
            return;
        }
        balance -= amount;
        System.out.printf(
            "  [SAVINGS WITHDRAW] Rs. %.2f withdrawn. New balance: Rs. %.2f%n", amount, balance);
    }

    // --------------------------------------------------------------- extra methods

    /**
     * Calculates annual interest on the current balance and credits it to the account.
     */
    public void applyInterest() {
        double interest = balance * INTEREST_RATE;
        interestEarned += interest;
        balance        += interest;
        System.out.printf(
            "  [INTEREST] Rs. %.2f interest applied (%.0f%%). New balance: Rs. %.2f%n",
            interest, INTEREST_RATE * 100, balance);
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.printf("  Interest Rate   : %.0f%% p.a.%n", INTEREST_RATE * 100);
        System.out.printf("  Min Balance     : Rs. %.2f%n",       MIN_BALANCE);
        System.out.printf("  Interest Earned : Rs. %.2f%n",       interestEarned);
    }

    // --------------------------------------------------------------- getters
    public double getInterestEarned()    { return interestEarned; }
    public static double getMinBalance() { return MIN_BALANCE;    }
}