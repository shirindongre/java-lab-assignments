import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ArrayListOfBooks {

    private ArrayList<Book> books;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Constructor
    public ArrayListOfBooks() {
        books = new ArrayList<>();
    }

    // Add a fully constructed Book object
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getName());
    }

    // Remove a book by 1-based display index
    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            System.out.println("Removed: " + books.get(index).getName());
            books.remove(index);
        } else {
            System.out.println("Invalid index. No book removed.");
        }
    }

    // Display all books
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the list.");
            return;
        }
        System.out.println("\n=== All Books ===");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("\nBook " + (i + 1) + ":");
            books.get(i).displayDetails();
        }
    }

    // Total books
    public int getTotalBooks() {
        return books.size();
    }

    // Average price
    public double getAveragePrice() {
        if (books.isEmpty()) return 0.0;
        double total = 0.0;
        for (Book book : books) total += book.getPrice();
        return total / books.size();
    }

    // Filter by genre
    public void printBooksByGenre(String genre) {
        ArrayList<Book> filtered = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) filtered.add(book);
        }

        if (filtered.isEmpty()) {
            System.out.println("No books found with genre: " + genre);
            return;
        }

        System.out.println("\n=== Books in Genre: " + genre + " ===");
        for (int i = 0; i < filtered.size(); i++) {
            System.out.println("\nBook " + (i + 1) + ":");
            filtered.get(i).displayDetails();
        }
    }

    // Get book by index
    public Book getBook(int index) {
        if (index >= 0 && index < books.size()) return books.get(index);
        return null;
    }

    // -------------------------------------------------------------------------
    // Interactive input helpers
    // -------------------------------------------------------------------------

    /** Prompt until a non-blank string is entered. */
    private static String readRequiredString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String val = sc.nextLine().trim();
            if (!val.isEmpty()) return val;
            System.out.println("  This field cannot be empty. Please try again.");
        }
    }

    /** Prompt for an optional string; returns the input as-is (may be blank). */
    private static String readOptionalString(Scanner sc, String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    /**
     * Prompt for a non-negative price.
     * Throws InvalidPriceException if the entered value is negative.
     * Re-prompts on non-numeric input.
     */
    private static double readPrice(Scanner sc) throws InvalidPriceException {
        while (true) {
            System.out.print("  Price (e.g. 12.99): $");
            String input = sc.nextLine().trim();
            try {
                double price = Double.parseDouble(input);
                if (price < 0) throw new InvalidPriceException("Price cannot be negative");
                return price;
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input. Please enter a numeric price.");
            }
        }
    }

    /**
     * Prompt for a date in yyyy-MM-dd format.
     * Re-prompts on malformed input; pressing Enter uses today's date.
     */
    private static LocalDate readDate(Scanner sc) {
        while (true) {
            System.out.print("  Date of Publishing (yyyy-MM-dd) [Enter for today]: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) return LocalDate.now();
            try {
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("  Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    /**
     * Collects all fields from the user and constructs a Book.
     * Propagates InvalidPriceException and InvalidGenreException to the caller.
     */
    private static Book promptForBook(Scanner sc)
            throws InvalidPriceException, InvalidGenreException {

        System.out.println("\n--- Enter Book Details ---");
        String name          = readRequiredString(sc, "  Book Name      : ");
        String author        = readRequiredString(sc, "  Author Name    : ");
        double price         = readPrice(sc);                                    // may throw InvalidPriceException
        String publisher     = readOptionalString(sc, "  Publisher Name : ");
        String genre         = readRequiredString(sc, "  Genre          : ");    // empty check also in Book constructor
        String isbn          = readRequiredString(sc, "  ISBN           : ");
        LocalDate published  = readDate(sc);

        return new Book(name, author, price, publisher, genre, isbn, published);
    }

    // -------------------------------------------------------------------------
    // Main — interactive menu
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        ArrayListOfBooks bookList = new ArrayListOfBooks();
        Scanner sc = new Scanner(System.in);

        System.out.println("=============================");
        System.out.println("   Book Management System    ");
        System.out.println("=============================");

        // Load pre-defined books
        bookList.addBook(Book.GREAT_GATSBY);
        bookList.addBook(Book.TO_KILL_A_MOCKINGBIRD);
        bookList.addBook(Book.SAPIENS);
        System.out.println("(3 pre-defined books loaded)\n");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add a new book");
            System.out.println("2. Remove a book");
            System.out.println("3. Display all books");
            System.out.println("4. Search by genre");
            System.out.println("5. Show average price");
            System.out.println("6. Show total book count");
            System.out.println("7. Quit");
            System.out.print("Choose an option (1-7): ");

            String choice = sc.nextLine().trim();

            switch (choice) {

                case "1":
                    try {
                        Book book = promptForBook(sc);
                        bookList.addBook(book);
                    } catch (InvalidPriceException e) {
                        System.out.println("  Price Error: " + e.getMessage());
                    } catch (InvalidGenreException e) {
                        System.out.println("  Genre Error: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("  Input Error: " + e.getMessage());
                    }
                    break;

                case "2":
                    if (bookList.getTotalBooks() == 0) {
                        System.out.println("No books to remove.");
                        break;
                    }
                    bookList.displayAllBooks();
                    System.out.print("\nEnter book number to remove: ");
                    try {
                        int idx = Integer.parseInt(sc.nextLine().trim()) - 1;
                        bookList.removeBook(idx);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                    break;

                case "3":
                    bookList.displayAllBooks();
                    break;

                case "4":
                    System.out.print("Enter genre to search: ");
                    String genre = sc.nextLine().trim();
                    if (genre.isEmpty()) {
                        System.out.println("Genre cannot be empty.");
                    } else {
                        bookList.printBooksByGenre(genre);
                    }
                    break;

                case "5":
                    if (bookList.getTotalBooks() == 0) {
                        System.out.println("No books in the list.");
                    } else {
                        System.out.printf("Average Price: $%.2f%n", bookList.getAveragePrice());
                    }
                    break;

                case "6":
                    System.out.println("Total Books: " + bookList.getTotalBooks());
                    break;

                case "7":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1-7.");
            }
        }

        sc.close();
    }
}