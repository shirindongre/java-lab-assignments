import java.time.Year;

public class Vehicle
{
    // Data Members (Fields)
    private String brandName;
    private String modelName;
    private double price;
    private String colour;
    private String mfgCode;
    private String regNo;
    private Year mfgYear;

    // Default Constructor (with proper default values)
    public Vehicle()
    {
        this.brandName = "Maruti";
        this.modelName = "Swift";
        this.price = 750000;
        this.colour = "Silver";
        this.mfgCode = "MAR2022";
        this.regNo = "MH01AA0001";
        this.mfgYear = Year.of(2022);
    }


    // Parameterized Constructor
    public Vehicle(String brandName, String modelName, double price,
                   String colour, String mfgCode, String regNo, Year mfgYear)
    {
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.colour = colour;
        this.mfgCode = mfgCode;
        this.regNo = regNo;
        this.mfgYear = mfgYear;
    }

    // Copy Constructor
    public Vehicle(Vehicle v)
    {
        this.brandName = v.brandName;
        this.modelName = v.modelName;
        this.price = v.price;
        this.colour = v.colour;
        this.mfgCode = v.mfgCode;
        this.regNo = v.regNo;
        this.mfgYear = v.mfgYear;
    }

    // Getter and Setter for mfgCode
    public void setMfgCode(String mfgCode)
    {
        this.mfgCode = mfgCode;
    }

    public String getMfgCode()
    {
        return mfgCode;
    }

    // Other Getters (for printing)
    public String getBrandName()
    {
        return brandName;
    }

    public String getModelName()
    {
        return modelName;
    }

    public double getPrice()
    {
        return price;
    }

    public String getColour()
    {
        return colour;
    }

    public String getRegNo()
    {
        return regNo;
    }

    public Year getMfgYear()
    {
        return mfgYear;
    }

    // Behaviour Methods

    public void start(int initialSpeed)
    {
        System.out.println("Vehicle started at speed: " + initialSpeed + " km/h");
    }

    public void drive(int initialGear, int speedIncrease, int finalSpeed)
    {
        if (initialGear != 1)
        {
            System.out.println("Start from Gear 1 first!");
        }
        else
        {
            System.out.println("Driving...");
            System.out.println("Speed increased by: " + speedIncrease + " km/h");
            System.out.println("Current speed: " + finalSpeed + " km/h");
        }
    }

    public void stop()
    {
        System.out.println("Vehicle stopped.");
    }

    public double calculateTripMileage(double distance, double fuelUsed)
    {
        double mileage = distance / fuelUsed;
        return mileage;
    }
}
