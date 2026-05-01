import java.time.LocalDate;

public class PartTimeEmployee extends ContractEmployee {
    private int maxHoursPerMonth;
    private double fixedAllowance;

    public PartTimeEmployee(String name, String pan, LocalDate joiningDate, String designation, Integer empID,
                            int noOfHrs, double hourlyRate, int maxHoursPerMonth, double fixedAllowance) {
        super(name, pan, joiningDate, designation, empID, noOfHrs, hourlyRate);
        this.maxHoursPerMonth = requireNonNegative(maxHoursPerMonth, "Max hours per month");
        this.fixedAllowance = requireNonNegative(fixedAllowance, "Fixed allowance");

        if (getNoOfHrs() > this.maxHoursPerMonth) {
            throw new IllegalArgumentException("Part-time hours cannot exceed max hours per month");
        }
    }

    @Override
    public double calculateCTC() {
        return super.calculateCTC() + fixedAllowance;
    }

    public int getMaxHoursPerMonth() {
        return maxHoursPerMonth;
    }
}