import java.time.LocalDate;

public abstract class Employee {
    public String name;
    private String pan;
    private LocalDate joiningDate;
    public String designation;
    public Integer empID;
    
    public Employee(String name, String pan, LocalDate joiningDate, String designation, Integer empID) {
        this.name = requireNonBlank(name, "Name");
        this.pan = requireNonBlank(pan, "PAN");
        this.joiningDate = requireNonNull(joiningDate, "Joining date");
        this.designation = requireNonBlank(designation, "Designation");
        this.empID = requirePositive(empID, "Employee ID");
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = requireNonBlank(pan, "PAN");
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = requireNonNull(joiningDate, "Joining date");
    }

    public abstract double calculateCTC();

    protected static String requireNonBlank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank");
        }
        return value.trim();
    }

    protected static <T> T requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        return value;
    }

    protected static double requireNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return value;
    }

    protected static int requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
        return value;
    }

    protected static Integer requirePositive(Integer value, String fieldName) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be a positive number");
        }
        return value;
    }
}