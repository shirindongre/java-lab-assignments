import java.time.Year;

public class MainForVehicle
{
    public static void main(String[] args)
    {
        // Default Vehicle
        Vehicle v1 = new Vehicle();

        // Parameterized Vehicle
        Vehicle v2 = new Vehicle(
                "Honda",
                "City",
                1200000,
                "White",
                "HND2024",
                "MH12AB1234",
                Year.of(2024)
        );

        // Copy Vehicle
        Vehicle v3 = new Vehicle(v2);

        // Print Table Header
        printTableHeader();

        // Print Vehicle Details
        printVehicleDetails(v1);
        printVehicleDetails(v2);
        printVehicleDetails(v3);

        // Test Behaviour
        System.out.println("\n--- Vehicle Operations ---");

        v2.start(10);
        v2.drive(1, 20, 60);

        double mileage = v2.calculateTripMileage(140, 20);
        System.out.println("Trip Mileage: " + mileage + " km/l");

        v2.stop();
    }

    // Prints Table Header
    public static void printTableHeader()
    {
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-12s %-10s %-10s %-12s %-15s %-10s\n",
                "Brand", "Model", "Price", "Colour", "Mfg Code", "Reg No", "Year");
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    // Prints One Row of Vehicle Data
    public static void printVehicleDetails(Vehicle v)
    {
        System.out.printf("%-12s %-12s %-10.2f %-10s %-12s %-15s %-10s\n",
                v.getBrandName(),
                v.getModelName(),
                v.getPrice(),
                v.getColour(),
                v.getMfgCode(),
                v.getRegNo(),
                v.getMfgYear()
        );
    }
}
