import java.time.LocalDate;

public class Manager extends FullTimeEmployee {
    private double ta;
    private double eduAllowance;

    public Manager(String name, String pan, LocalDate joiningDate, String designation, Integer empID,
                   String role, double baseSalary, double perfBonus, double hiringCommission,
                   double ta, double eduAllowance) {
        super(name, pan, joiningDate, designation, empID, role, baseSalary, perfBonus, hiringCommission);
        this.ta = requireNonNegative(ta, "Travel allowance");
        this.eduAllowance = requireNonNegative(eduAllowance, "Education allowance");
    }

    @Override
    public double calculateCTC() {
        return super.calculateCTC() + ta + eduAllowance;
    }
}