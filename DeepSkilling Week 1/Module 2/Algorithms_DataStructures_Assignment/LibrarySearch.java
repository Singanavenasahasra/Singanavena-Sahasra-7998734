import java.util.Arrays;

public class LibrarySearch {

    public static class Book implements Comparable<Book> {
        private final String bookId;
        private final String title;
        private final String author;

        public Book(String bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        public String getBookId() { return bookId; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }

        @Override
        public int compareTo(Book other) {
            return this.title.compareToIgnoreCase(other.title);
        }
    }

    public static int linearSearch(Book[] books, String targetTitle) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equalsIgnoreCase(targetTitle)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Book[] books, String targetTitle) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comp = books[mid].getTitle().compareToIgnoreCase(targetTitle);

            if (comp == 0) return mid;
            if (comp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 6: Library Management System ---");

        Book[] books = {
            new Book("B01", "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book("B02", "To Kill a Mockingbird", "Harper Lee"),
            new Book("B03", "1984", "George Orwell")
        };

        String searchTarget = "1984";

        int linearIndex = linearSearch(books, searchTarget);
        System.out.println("Linear Search: Found at index " + linearIndex);

        Arrays.sort(books);
        int binaryIndex = binarySearch(books, searchTarget);
        System.out.println("Binary Search (Sorted): Found at index " + binaryIndex);

        if (binaryIndex != -1) {
            System.out.println("Book Details -> ID: " + books[binaryIndex].getBookId() + ", Author: " + books[binaryIndex].getAuthor());
        }

        System.out.println("--- Execution Finished Successfully ---");
    }
}