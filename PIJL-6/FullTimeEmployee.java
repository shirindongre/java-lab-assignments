import java.time.LocalDate;

public class FullTimeEmployee extends Employee {
    protected String role;
    protected double baseSalary;
    protected double perfBonus;
    protected double hiringCommission;

    public FullTimeEmployee(String name, String pan, LocalDate joiningDate, String designation, Integer empID,
                            String role, double baseSalary, double perfBonus, double hiringCommission) {
        super(name, pan, joiningDate, designation, empID);
        this.role = requireNonBlank(role, "Role");
        this.baseSalary = requireNonNegative(baseSalary, "Base salary");
        this.perfBonus = requireNonNegative(perfBonus, "Performance bonus");
        this.hiringCommission = requireNonNegative(hiringCommission, "Hiring commission");
    }

    @Override
    public double calculateCTC() {
        String normalizedRole = role.trim().toUpperCase();
        if ("SWE".equals(normalizedRole)) {
            return baseSalary + perfBonus;
        }
        if ("HR".equals(normalizedRole)) {
            return baseSalary + hiringCommission;
        }

        throw new IllegalArgumentException("Unsupported role: " + role);
    }

    public String getRole() {
        return role;
    }
}