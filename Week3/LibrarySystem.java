package AssignmentProblems;
class Book {
    String bookId, title;
    boolean isIssued = false;
    static int totalBooks = 0;

    public Book(String t) {
        bookId = "B" + (++totalBooks); title = t;
    }
}

class Member {
    String memberId, name;
    static int totalMembers = 0;
    Book issuedBook;

    public Member(String n) {
        memberId = "M" + (++totalMembers); name = n;
    }

    public void issueBook(Book b) {
        if (!b.isIssued) { issuedBook = b; b.isIssued = true; }
    }

    public void returnBook() {
        if (issuedBook != null) issuedBook.isIssued = false;
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book b1 = new Book("Java");
        Member m1 = new Member("Alice");
        m1.issueBook(b1);
        System.out.println(m1.name + " borrowed " + b1.title);
    }
}
