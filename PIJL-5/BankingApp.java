import java.util.ArrayList;
import java.util.List;

/**
 * BankingApp : driver class.
 *
 * Demonstrates:
 *   • Inheritance  : SavingsAccount and LoanAccount extend Account.
 *   • Overriding   : deposit() / withdraw() behave differently per type.
 *   • Polymorphism : Account references point to child objects; the correct
 *                    overridden methods are called at runtime.
 *   • Collections  : ArrayList<Customer> and ArrayList<Account> store all data.
 */
public class BankingApp {

    public static void main(String[] args) {

        // ------------------------------------------------------------------ 1. Create customers
        List<Customer> customers = new ArrayList<>();

        Customer c1 = new Customer(101, "Ananya Sharma",  "ananya@email.com",  "9876543210");
        Customer c2 = new Customer(102, "Rohan Mehta",    "rohan@email.com",   "9123456789");
        Customer c3 = new Customer(103, "Priya Nair",     "priya@email.com",   "9988776655");

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        // ------------------------------------------------------------------ 2. Create accounts (polymorphic references)
        List<Account> allAccounts = new ArrayList<>();

        System.out.println("\n===== ACCOUNT CREATION =====\n");

        // Ananya : one savings, one loan
        SavingsAccount sa1 = new SavingsAccount(101, 15000.00);
        LoanAccount    la1 = new LoanAccount(101, 200000.00, 0.085, 24);
        c1.addAccount(sa1);
        c1.addAccount(la1);
        allAccounts.add(sa1);
        allAccounts.add(la1);

        // Rohan : two savings accounts
        SavingsAccount sa2 = new SavingsAccount(102, 5000.00);
        SavingsAccount sa3 = new SavingsAccount(102, 25000.00);
        c2.addAccount(sa2);
        c2.addAccount(sa3);
        allAccounts.add(sa2);
        allAccounts.add(sa3);

        // Priya : one savings, one loan
        SavingsAccount sa4 = new SavingsAccount(103, 8000.00);
        LoanAccount    la2 = new LoanAccount(103, 500000.00, 0.09, 60);
        c3.addAccount(sa4);
        c3.addAccount(la2);
        allAccounts.add(sa4);
        allAccounts.add(la2);

        // ------------------------------------------------------------------ 3. Perform banking operations
        System.out.println("\n===== BANKING OPERATIONS =====\n");

        // --- Ananya's savings account ---
        System.out.println(">> Ananya : Savings Account operations:");
        sa1.deposit(5000);
        sa1.withdraw(3000);
        sa1.withdraw(18000);          // should fail : breach of min balance
        sa1.applyInterest();

        System.out.println();

        // --- Ananya's loan account ---
        System.out.println(">> Ananya : Loan Account operations:");
        la1.accrueMonthlyInterest();
        la1.deposit(10000);           // repayment
        la1.withdraw(50000);          // additional drawdown

        System.out.println();

        // --- Rohan's first savings account ---
        System.out.println(">> Rohan : Savings Account #1 operations:");
        sa2.deposit(2000);
        sa2.withdraw(500);
        sa2.withdraw(6000);           // should fail : min balance breach
        sa2.applyInterest();

        System.out.println();

        // --- Rohan's second savings account ---
        System.out.println(">> Rohan : Savings Account #2 operations:");
        sa3.deposit(10000);
        sa3.withdraw(5000);
        sa3.applyInterest();

        System.out.println();

        // --- Priya's savings account ---
        System.out.println(">> Priya : Savings Account operations:");
        sa4.deposit(12000);
        sa4.withdraw(4000);
        sa4.applyInterest();

        System.out.println();

        // --- Priya's loan account ---
        System.out.println(">> Priya : Loan Account operations:");
        la2.accrueMonthlyInterest();
        la2.deposit(20000);           // repayment
        la2.withdraw(600000);         // exceeds sanctioned limit : should fail

        // ------------------------------------------------------------------ 4. Consolidated display
        System.out.println("\n\n===== CONSOLIDATED ACCOUNT SUMMARY =====");

        for (Customer customer : customers) {
            customer.displayCustomerInfo();
        }

        // ------------------------------------------------------------------ 5. Polymorphism demo
        System.out.println("\n===== POLYMORPHISM DEMO =====");
        System.out.println("Iterating all accounts via base-class (Account) references:\n");

        for (Account acc : allAccounts) {
            // The correct overridden displayAccountInfo() is called at runtime
            System.out.printf("Account #%d (%s) : Balance/Outstanding: Rs. %.2f%n",
                acc.getAccountNumber(), acc.getAccountType(), acc.getBalance());
        }

        System.out.println("\n[Done]");
    }
}