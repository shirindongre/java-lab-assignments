import java.time.LocalDate;

public class SeniorManager extends Manager {
    private int teamSize;
    private double leadershipAllowance;

    public SeniorManager(String name, String pan, LocalDate joiningDate, String designation, Integer empID,
                         String role, double baseSalary, double perfBonus, double hiringCommission,
                         double ta, double eduAllowance, int teamSize, double leadershipAllowance) {
        super(name, pan, joiningDate, designation, empID, role, baseSalary, perfBonus, hiringCommission, ta, eduAllowance);
        this.teamSize = requireNonNegative(teamSize, "Team size");
        this.leadershipAllowance = requireNonNegative(leadershipAllowance, "Leadership allowance");
    }

    @Override
    public double calculateCTC() {
        double teamBonus = teamSize * 1000.0;
        return super.calculateCTC() + leadershipAllowance + teamBonus;
    }
}