import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class EmployeeService {
    private EmployeeService() {
    }

    public static <T extends Employee> List<T> filterByType(Collection<Employee> employees, Class<T> employeeType) {
        List<T> filtered = new ArrayList<>();
        if (employees == null || employeeType == null) {
            return filtered;
        }

        for (Employee employee : employees) {
            if (employeeType.isInstance(employee)) {
                filtered.add(employeeType.cast(employee));
            }
        }
        return filtered;
    }

    public static List<Employee> filterByCTCRange(Collection<Employee> employees, double minCTC, double maxCTC) {
        List<Employee> filtered = new ArrayList<>();
        if (employees == null) {
            return filtered;
        }
        if (minCTC > maxCTC) {
            throw new IllegalArgumentException("Minimum CTC cannot be greater than maximum CTC");
        }

        for (Employee employee : employees) {
            double ctc = employee.calculateCTC();
            if (ctc >= minCTC && ctc <= maxCTC) {
                filtered.add(employee);
            }
        }
        return filtered;
    }

    public static List<Employee> filterByJoiningDateRange(Collection<Employee> employees, LocalDate startDate, LocalDate endDate) {
        List<Employee> filtered = new ArrayList<>();
        if (employees == null || startDate == null || endDate == null) {
            return filtered;
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        for (Employee employee : employees) {
            LocalDate joiningDate = employee.getJoiningDate();
            if ((joiningDate.isEqual(startDate) || joiningDate.isAfter(startDate))
                    && (joiningDate.isEqual(endDate) || joiningDate.isBefore(endDate))) {
                filtered.add(employee);
            }
        }
        return filtered;
    }

    public static Map<String, Double> summarizeTotalCTCByType(Collection<Employee> employees) {
        Map<String, Double> summary = new LinkedHashMap<>();
        if (employees == null) {
            return summary;
        }

        for (Employee employee : employees) {
            String type = employee.getClass().getSimpleName();
            summary.put(type, summary.getOrDefault(type, 0.0) + employee.calculateCTC());
        }
        return summary;
    }
}