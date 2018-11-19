package test;

import dataobject.Book;
import dataobject.Category;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterBooksTest {
    private Category category;

    @BeforeTest
    public void setUp() {
        category = new Category();
        category.setCategoryId("001");
        category.setCategoryName("IT");

        ArrayList<Book> books = new ArrayList<>();
        Book book1 = new Book();
        book1.setAuthor("Naveen Balani");
        book1.setBookName("Apache Tomcat");
        book1.setBookId("001");
        book1.setIsbn("ISB001");
        books.add(book1);

        Book book2 = new Book();
        book2.setAuthor("Naveen Balani");
        book2.setBookName("Gradle");
        book2.setBookId("002");
        book2.setIsbn("ISB002");
        books.add(book2);

        Book book3 = new Book();
        book3.setAuthor("Adam Balani");
        book3.setBookName("Apache CXF");
        book3.setBookId("003");
        book3.setIsbn("ISB003");
        books.add(book3);

        category.addBooks(books);
    }

    @Test
    public void testFilterByAuthor() {
        String author = "Naveen Balani";
        List<Book> books = filterByAuthor(category.getBooks(), author);
        Assert.assertEquals(books.size(), 2);
        for (Book book : books) {
            Assert.assertTrue(book.getAuthor().equals(author));
        }
    }

    @Test
    public void testFilterByName() {
        String name = "Gradle";
        List<Book> books = filterByName(category.getBooks(), name);
        Assert.assertEquals(books.size(), 1);
        Assert.assertTrue(books.get(0).getBookName().equals(name));
    }

    private List<Book> filterByAuthor(List<Book> books, String author) {
        return books
                .stream()
                .filter(b -> b.getAuthor().equals(author))
                .collect(Collectors.toList());

    }

    private List<Book> filterByName(List<Book> books, String name) {
        return books
                .stream()
                .filter(b -> b.getBookName().equals(name))
                .collect(Collectors.toList());
    }
}
