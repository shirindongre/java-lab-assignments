import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Read and validate both dimensions first, before collecting any components
            System.out.print("Enter dimension for Vector 1 (2 or 3): ");
            int dim1 = readDimension(sc, 1);

            System.out.print("Enter dimension for Vector 2 (2 or 3): ");
            int dim2 = readDimension(sc, 2);

            if (dim1 != dim2) {
                throw new VectorException(
                    "Dimension mismatch: Vector 1 is " + dim1 + "D but Vector 2 is " + dim2 + "D"
                );
            }

            // Dimensions are valid and matching — now collect components
            Vector v1 = readComponents(sc, 1, dim1);
            Vector v2 = readComponents(sc, 2, dim2);

            // Operations
            System.out.println("\n--- RESULTS ---");

            System.out.print("Vector 1 Magnitude: ");
            System.out.printf("%.4f%n", v1.magnitude());

            System.out.print("Vector 2 Magnitude: ");
            System.out.printf("%.4f%n", v2.magnitude());

            Vector sum = v1.add(v2);
            System.out.print("Sum: ");
            sum.printVector();

            Vector diff = v1.subtract(v2);
            System.out.print("Difference: ");
            diff.printVector();

            double dot = v1.dotProduct(v2);
            System.out.println("Dot Product: " + dot);

            // Cross product only if both are 3D
            try {
                Vector cross = v1.crossProduct(v2);
                System.out.print("Cross Product: ");
                cross.printVector();
            } catch (VectorException e) {
                System.out.println("Cross Product: " + e.getMessage());
            }

        } catch (VectorException e) {
            System.out.println("Vector Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid Input! Please enter numbers only.");
        }

        sc.close();
    }

    /**
     * Reads and validates a single dimension value.
     * Throws VectorException immediately if the value is not 2 or 3.
     */
    private static int readDimension(Scanner sc, int vectorNumber) throws VectorException {
        if (!sc.hasNextInt()) {
            sc.next(); // discard invalid token
            throw new VectorException("Dimension for Vector " + vectorNumber + " must be an integer (2 or 3)");
        }

        int dim = sc.nextInt();

        if (dim != 2 && dim != 3) {
            throw new VectorException(
                "Invalid dimension " + dim + " for Vector " + vectorNumber + ": only 2D or 3D vectors are allowed"
            );
        }

        return dim;
    }

    /**
     * Collects components for a vector of a known, already-validated dimension.
     */
    private static Vector readComponents(Scanner sc, int vectorNumber, int dim) throws VectorException {
        double[] components = new double[dim];
        System.out.println("Enter " + dim + " components of Vector " + vectorNumber + ":");

        for (int i = 0; i < dim; i++) {
            if (!sc.hasNextDouble()) {
                sc.next(); // discard invalid token
                throw new VectorException(
                    "Component " + (i + 1) + " of Vector " + vectorNumber + " is not a valid number"
                );
            }
            components[i] = sc.nextDouble();
        }

        return new Vector(components);
    }
}