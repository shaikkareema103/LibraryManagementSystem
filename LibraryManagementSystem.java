import java.util.*;
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isIssued;
    private User issuedTo;
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isIssued = false;
        this.issuedTo = null;
    }public String getTitle() {
        return title;
    }
    public boolean isIssued() {
        return isIssued;
    }
    public User getIssuedTo() {
        return issuedTo;
    }
    public void issueTo(User user) {
        this.isIssued = true;
        this.issuedTo = user;
    }
    public void returnBook() {
        this.isIssued = false;
        this.issuedTo = null;
    }
    @Override
    public String toString() {
        String status = isIssued ? "Issued to " + issuedTo.getName() : "Available";
        return title + " by " + author + " | ISBN: " + isbn + " | " + status;
    }
}
class User {
    private String name;
    private String userId;
    private List<Book> borrowedBooks;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void borrowBook(Book book) {
        if (book.isIssued()) {
            System.out.println(book.getTitle() + "' is already issued to " + book.getIssuedTo().getName());
        } else {
            book.issueTo(this);
            borrowedBooks.add(book);
            System.out.println(book.getTitle() + "' issued to " + name);
        }
    }
    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
            System.out.println( book.getTitle() + "' returned by " + name);
        } else {
            System.out.println(name + " did not borrow '" + book.getTitle() + "'");
        }
    }
    @Override
    public String toString() {
        return "User: " + name + " | ID: " + userId + " | Borrowed: " + borrowedBooks.size() + " book(s)";
    }
}
class Library {
    private String name;
    private List<Book> books;
    private List<User> users;
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    public void addBook(Book book) {
        books.add(book);
        System.out.println(" Book '" + book.getTitle() + "' added to " + name);
    }
    public void addUser(User user) {
        users.add(user);
        System.out.println(" User '" + user.getName() + "' registered in " + name);
    }
    public void showBooks() {
        System.out.println("\n Books in " + name + ":");
        for (Book book : books) {
            System.out.println(" - " + book);
        }
    }
    public void showUsers() {
        System.out.println("\n Users in " + name + ":");
        for (User user : users) {
            System.out.println(" - " + user);
        }
    }
}
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library("City Library");
        Book b1 = new Book("1984", "George Orwell", "ISBN001");
        Book b2 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "ISBN002");
        library.addBook(b1);
        library.addBook(b2);
        User u1 = new User("Alice", "U001");
        User u2 = new User("Bob", "U002");
        library.addUser(u1);
        library.addUser(u2);
        library.showBooks();
        u1.borrowBook(b1);   
        u2.borrowBook(b1);   
        u1.returnBook(b1);   
        u2.borrowBook(b1);  
        library.showBooks(); 
    }
}

