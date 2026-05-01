import java.time.LocalDate;

public class Book {

    private String name;
    private String authorName;
    private double price;
    private String publisherName;
    private String genre;
    private String ISBN;
    private LocalDate dateOfPublishing;

    // Parameterized Constructor
    public Book(String name, String authorName, double price, String publisherName,
                String genre, String ISBN, LocalDate dateOfPublishing)
            throws InvalidPriceException, InvalidGenreException {

        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Book name cannot be empty");
        if (authorName == null || authorName.trim().isEmpty())
            throw new IllegalArgumentException("Author name cannot be empty");
        if (ISBN == null || ISBN.trim().isEmpty())
            throw new IllegalArgumentException("ISBN cannot be empty");
        if (price < 0)
            throw new InvalidPriceException("Price cannot be negative");
        if (genre == null || genre.trim().isEmpty())
            throw new InvalidGenreException("Genre cannot be empty");

        this.name            = name.trim();
        this.authorName      = authorName.trim();
        this.price           = price;
        this.publisherName   = (publisherName != null) ? publisherName.trim() : "Unknown";
        this.genre           = genre.trim();
        this.ISBN            = ISBN.trim();
        this.dateOfPublishing = (dateOfPublishing != null) ? dateOfPublishing : LocalDate.now();
    }

    // Default Constructor
    public Book() {
        this.name             = "Unknown";
        this.authorName       = "Unknown";
        this.price            = 0.0;
        this.publisherName    = "Unknown";
        this.genre            = "Unknown";
        this.ISBN             = "Unknown";
        this.dateOfPublishing = LocalDate.now();
    }

    // Getters
    public String    getName()             { return name; }
    public String    getAuthorName()       { return authorName; }
    public double    getPrice()            { return price; }
    public String    getPublisherName()    { return publisherName; }
    public String    getGenre()            { return genre; }
    public String    getISBN()             { return ISBN; }
    public LocalDate getDateOfPublishing() { return dateOfPublishing; }

    // Setters with validation
    public void setPrice(double price) throws InvalidPriceException {
        if (price < 0) throw new InvalidPriceException("Price cannot be negative");
        this.price = price;
    }

    public void setGenre(String genre) throws InvalidGenreException {
        if (genre == null || genre.trim().isEmpty())
            throw new InvalidGenreException("Genre cannot be empty");
        this.genre = genre.trim();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Book name cannot be empty");
        this.name = name.trim();
    }

    public void setAuthorName(String authorName) {
        if (authorName == null || authorName.trim().isEmpty())
            throw new IllegalArgumentException("Author name cannot be empty");
        this.authorName = authorName.trim();
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = (publisherName != null) ? publisherName.trim() : "Unknown";
    }

    public void setISBN(String ISBN) {
        if (ISBN == null || ISBN.trim().isEmpty())
            throw new IllegalArgumentException("ISBN cannot be empty");
        this.ISBN = ISBN.trim();
    }

    public void setDateOfPublishing(LocalDate dateOfPublishing) {
        this.dateOfPublishing = (dateOfPublishing != null) ? dateOfPublishing : LocalDate.now();
    }

    // Pre-defined Book objects
    public static Book GREAT_GATSBY;
    public static Book TO_KILL_A_MOCKINGBIRD;
    public static Book SAPIENS;

    static {
        try {
            GREAT_GATSBY = new Book(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                10.99,
                "Scribner",
                "Fiction",
                "978-0743273565",
                LocalDate.of(1925, 4, 10)
            );
            TO_KILL_A_MOCKINGBIRD = new Book(
                "To Kill a Mockingbird",
                "Harper Lee",
                12.99,
                "Lippincott",
                "Fiction",
                "978-0061120084",
                LocalDate.of(1960, 7, 11)
            );
            SAPIENS = new Book(
                "Sapiens: A Brief History of Humankind",
                "Yuval Noah Harari",
                15.99,
                "Harper Collins",
                "Non-Fiction",
                "978-0062316097",
                LocalDate.of(2011, 1, 1)
            );
        } catch (InvalidPriceException | InvalidGenreException e) {
            throw new ExceptionInInitializerError("Failed to initialize predefined books: " + e.getMessage());
        }
    }

    // Display method
    public void displayDetails() {
        System.out.println("  Book Name    : " + name);
        System.out.println("  Author       : " + authorName);
        System.out.printf ("  Price        : $%.2f%n", price);
        System.out.println("  Publisher    : " + publisherName);
        System.out.println("  Genre        : " + genre);
        System.out.println("  ISBN         : " + ISBN);
        System.out.println("  Published On : " + dateOfPublishing);
    }
}