public class Vector {
    private double[] components;

    // Constructor
    public Vector(double[] components) throws VectorException {
        if (components == null || (components.length != 2 && components.length != 3)) {
            throw new VectorException("Only 2D or 3D vectors are allowed");
        }
        this.components = components.clone();
    }

    // Check dimensions match between two vectors
    private void checkDimensions(Vector other) throws VectorException {
        if (this.components.length != other.components.length) {
            throw new VectorException("Vector dimensions must match ("
                    + this.components.length + "D vs " + other.components.length + "D)");
        }
    }

    // Addition
    public Vector add(Vector other) throws VectorException {
        checkDimensions(other);
        double[] result = new double[components.length];

        for (int i = 0; i < components.length; i++) {
            result[i] = this.components[i] + other.components[i];
        }

        return new Vector(result);
    }

    // Subtraction
    public Vector subtract(Vector other) throws VectorException {
        checkDimensions(other);
        double[] result = new double[components.length];

        for (int i = 0; i < components.length; i++) {
            result[i] = this.components[i] - other.components[i];
        }

        return new Vector(result);
    }

    // Dot Product
    public double dotProduct(Vector other) throws VectorException {
        checkDimensions(other);
        double result = 0;

        for (int i = 0; i < components.length; i++) {
            result += this.components[i] * other.components[i];
        }

        return result;
    }

    // Cross Product (3D only)
    public Vector crossProduct(Vector other) throws VectorException {
        if (this.components.length != 3 || other.components.length != 3) {
            throw new VectorException("Cross product is only defined for 3D vectors");
        }

        double[] result = {
            this.components[1] * other.components[2] - this.components[2] * other.components[1],
            this.components[2] * other.components[0] - this.components[0] * other.components[2],
            this.components[0] * other.components[1] - this.components[1] * other.components[0]
        };

        return new Vector(result);
    }

    // Magnitude (Euclidean norm)
    public double magnitude() {
        double sum = 0;
        for (double c : components) {
            sum += c * c;
        }
        return Math.sqrt(sum);
    }

    // Dimension getter
    public int getDimension() {
        return components.length;
    }

    // Display method
    public void printVector() {
        System.out.print("[");
        for (int i = 0; i < components.length; i++) {
            System.out.print(components[i]);
            if (i < components.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}