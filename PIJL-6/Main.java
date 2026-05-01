import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Employee> employeeMap = new HashMap<>();

        employeeMap.put(1, new FullTimeEmployee("Alice", "PAN123", LocalDate.of(2020, 1, 15), "Software Engineer", 101, "SWE", 60000, 5000, 2000));
        employeeMap.put(2, new Manager("Bob", "PAN456", LocalDate.of(2019, 3, 10), "Project Manager", 102, "HR", 80000, 10000, 5000, 3000, 2000));
        employeeMap.put(3, new ContractEmployee("Charlie", "PAN789", LocalDate.of(2021, 6, 1), "Contractor", 103, 160, 50));

        // New subtypes
        employeeMap.put(4, new InternEmployee("Diana", "PAN234", LocalDate.of(2024, 7, 1), "Intern", 104, 18000, "Alice"));
        employeeMap.put(5, new PartTimeEmployee("Ethan", "PAN567", LocalDate.of(2022, 4, 5), "Part-time Analyst", 105, 80, 40, 100, 2500));
        employeeMap.put(6, new SeniorManager("Farah", "PAN890", LocalDate.of(2017, 11, 20), "Senior Manager", 106,
                "HR", 110000, 15000, 10000, 6000, 5000, 12, 12000));

        System.out.println("=== All Employees ===");
        printEmployees(new ArrayList<>(employeeMap.values()));

        System.out.println("\n=== Filter: Managers (including subtypes) ===");
        List<Manager> managers = EmployeeService.filterByType(employeeMap.values(), Manager.class);
        List<Employee> managerEmployees = new ArrayList<>();
        managerEmployees.addAll(managers);
        printEmployees(managerEmployees);

        System.out.println("\n=== Filter: CTC between 20000 and 90000 ===");
        List<Employee> ctcRange = EmployeeService.filterByCTCRange(employeeMap.values(), 20000, 90000);
        printEmployees(ctcRange);

        System.out.println("\n=== Filter: Joined between 2020-01-01 and 2023-12-31 ===");
        List<Employee> joiningRange = EmployeeService.filterByJoiningDateRange(
                employeeMap.values(), LocalDate.of(2020, 1, 1), LocalDate.of(2023, 12, 31));
        printEmployees(joiningRange);

        System.out.println("\n=== Total CTC By Employee Type ===");
        Map<String, Double> typeSummary = EmployeeService.summarizeTotalCTCByType(employeeMap.values());
        for (Map.Entry<String, Double> entry : typeSummary.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        System.out.println("\n=== Validation Demo ===");
        try {
            new PartTimeEmployee("Invalid", "PAN000", LocalDate.now(), "Part-time", 999, 120, 35, 100, 0);
        } catch (IllegalArgumentException exception) {
            System.out.println("Validation check passed: " + exception.getMessage());
        }
    }

    private static void printEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(
                    "Employee ID: " + employee.empID
                            + ", Name: " + employee.name
                            + ", Type: " + employee.getClass().getSimpleName()
                            + ", CTC: " + employee.calculateCTC());
        }
    }
}