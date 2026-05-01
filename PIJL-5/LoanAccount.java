/**
 * A loan account that models a bank-granted loan.
 *
 * Semantics are the reverse of a normal account:
 *   - balance   = outstanding principal yet to be repaid (stored as a positive number)
 *   - deposit() = treated as a loan repayment (reduces the outstanding amount)
 *   - withdraw() = treated as drawing additional loan funds (increases the outstanding amount),
 *                  subject to the sanctioned credit limit
 *
 * The account also tracks accrued interest on the outstanding principal.
 */
public class LoanAccount extends Account {

    private double loanAmount;          // original / sanctioned loan
    private double interestRate;        // annual interest rate (e.g. 0.085 → 8.5 %)
    private int    tenureMonths;        // loan tenure in months
    private double totalInterestPaid;

    // --------------------------------------------------------------- constructor
    public LoanAccount(int customerId, double loanAmount, double annualInterestRate, int tenureMonths) {
        // balance field is repurposed: it holds the outstanding principal
        super(customerId, loanAmount, "Loan Account");
        this.loanAmount        = loanAmount;
        this.interestRate      = annualInterestRate;
        this.tenureMonths      = tenureMonths;
        this.totalInterestPaid = 0.0;

        System.out.printf(
            "  [LOAN CREATED] Loan of Rs. %.2f sanctioned at %.2f%% p.a. for %d months.%n",
            loanAmount, annualInterestRate * 100, tenureMonths);
    }

    // --------------------------------------------------------------- overrides

    /**
     * Loan repayment: reduces outstanding balance.
     * A full repayment message is shown when the loan is cleared.
     */
    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Repayment amount must be positive.");
            return;
        }
        if (amount > balance) {
            System.out.printf(
                "  [ERROR] Repayment Rs. %.2f exceeds outstanding balance Rs. %.2f.%n", amount, balance);
            return;
        }
        balance -= amount;
        System.out.printf(
            "  [LOAN REPAYMENT] Rs. %.2f repaid. Outstanding balance: Rs. %.2f%n", amount, balance);
        if (balance == 0) {
            System.out.println("  [INFO] Loan fully repaid. Congratulations!");
        }
    }

    /**
     * Additional drawdown: increases outstanding balance up to the sanctioned limit.
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("  [ERROR] Drawdown amount must be positive.");
            return;
        }
        if (balance + amount > loanAmount) {
            System.out.printf(
                "  [ERROR] Drawdown of Rs. %.2f would exceed the sanctioned limit of Rs. %.2f.%n",
                amount, loanAmount);
            return;
        }
        balance += amount;
        System.out.printf(
            "  [LOAN DRAWDOWN] Rs. %.2f drawn. Outstanding balance: Rs. %.2f%n", amount, balance);
    }

    // --------------------------------------------------------------- extra methods

    /**
     * Calculates the simple monthly interest on the current outstanding balance
     * and adds it to the running total of interest paid.
     */
    public void accrueMonthlyInterest() {
        double monthlyInterest = balance * (interestRate / 12);
        totalInterestPaid     += monthlyInterest;
        System.out.printf(
            "  [INTEREST ACCRUED] Monthly interest: Rs. %.2f. Total interest so far: Rs. %.2f%n",
            monthlyInterest, totalInterestPaid);
    }

    /**
     * Returns the fixed EMI using the standard formula:
     *   EMI = P × r × (1+r)^n / ((1+r)^n − 1)
     * where r = monthly rate, n = tenure in months.
     */
    public double calculateEMI() {
        double monthlyRate = interestRate / 12;
        double factor      = Math.pow(1 + monthlyRate, tenureMonths);
        return loanAmount * monthlyRate * factor / (factor - 1);
    }

    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();                    // prints accountNumber, type, balance
        // Override the label printed by super (balance = outstanding here)
        System.out.printf("  Sanctioned Loan : Rs. %.2f%n",       loanAmount);
        System.out.printf("  Outstanding     : Rs. %.2f%n",       balance);
        System.out.printf("  Interest Rate   : %.2f%% p.a.%n", interestRate * 100);
        System.out.printf("  Tenure          : %d months%n",   tenureMonths);
        System.out.printf("  EMI             : Rs. %.2f%n",       calculateEMI());
        System.out.printf("  Interest Accrued: Rs. %.2f%n",       totalInterestPaid);
    }

    // --------------------------------------------------------------- getters
    public double getLoanAmount()       { return loanAmount;        }
    public double getInterestRate()     { return interestRate;      }
    public int    getTenureMonths()     { return tenureMonths;      }
    public double getTotalInterestPaid(){ return totalInterestPaid; }
    public double getOutstanding()      { return balance;           }
}