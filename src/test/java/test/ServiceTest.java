package test;

import client.CategoryServiceClient;
import dataaccess.CategoryDAO;
import dataobject.Book;
import dataobject.Category;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static javax.ws.rs.core.Response.Status.*;

@Listeners({TestListener.class})
public class ServiceTest {

    private CategoryServiceClient client = new CategoryServiceClient();
    private CategoryDAO dao;
    private Category category;
    private Category categoryToAdd;

    @BeforeMethod
    public void setUp() {
        category = new Category();
        category.setCategoryId("001");
        category.setCategoryName("Java");

        Book book = new Book();
        book.setAuthor("Naveen Balani");
        book.setBookName("Apache Tomcat");
        book.setBookId("001");
        book.setIsbn("ISB001");

        dao = new CategoryDAO();
        dao.addCategory(category);
        dao.addBook(category, book);

        categoryToAdd = new Category();
        categoryToAdd.setCategoryId("002");
        categoryToAdd.setCategoryName("Fantasy");
    }

    @AfterMethod
    public void cleanUp() {
        dao.deleteCategory(category.getCategoryId());
        dao.deleteCategory(categoryToAdd.getCategoryId());
    }

    @Test
    public void testAddCategory() throws InterruptedException {
        client.addCategory(categoryToAdd)
                .then()
                .statusCode(CREATED.getStatusCode());

        verifyCategory(category);
    }

    @Test
    public void testUpdateCategory() {
        category.setCategoryName(".NET");
        client.updateCategory(category.getCategoryId(), category)
                .then()
                .statusCode(OK.getStatusCode());

        verifyCategory(category);
    }

    @Test
    public void addBooksToCategory() {
        Book book1 = new Book();
        book1.setAuthor("Naveen Balani");
        book1.setBookName("Spring Series");
        book1.setBookId("003");
        book1.setIsbn("ISB003");
        Book book2 = new Book();
        book2.setAuthor("Rajeev Hathi");
        book2.setBookName("CXF Series");
        book2.setBookId("004");
        book2.setIsbn("ISB004");
        List<Book> booksList = new ArrayList<>();
        booksList.add(book1);
        booksList.add(book2);

        client.addBooks(category.getCategoryId(), booksList)
                .then()
                .statusCode(OK.getStatusCode());

        List<Book> expBooks = category.getBooks();
        expBooks.addAll(booksList);

        verifyBooks(expBooks);
    }

    @Test
    public void addBookToCategory() {
        Book book = new Book();
        book.setAuthor("Kathy Sierra");
        book.setBookName("Head First Java");
        book.setBookId("002");
        book.setIsbn("ISB002");

        client.addBooks(category.getCategoryId(), Arrays.asList(book))
                .then()
                .statusCode(OK.getStatusCode());

        List<Book> expBooks = category.getBooks();
        expBooks.add(book);

        verifyBooks(expBooks);
    }

    @Test
    public void testDeleteCategory() {
        Category categoryToDelete = new Category();
        categoryToDelete.setCategoryId("003");
        dao.addCategory(categoryToDelete);

        client.deleteCategory(categoryToDelete.getCategoryId())
                .then()
                .statusCode(NO_CONTENT.getStatusCode());

        Response response = client.getCategory(categoryToDelete.getCategoryId());
        response.then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book();
        book.setAuthor("Adam Balani");
        book.setBookName("Gradle");
        book.setBookId("006");
        book.setIsbn("ISB06");

        dao.addBook(category, book);

        client.deleteBook(category.getCategoryId(), book.getBookId())
                .then()
                .statusCode(NO_CONTENT.getStatusCode());

        verifyBooks(Arrays.asList(category.getBook("001")));
    }

    @Test
    public void testDeleteBooks() {
        client.deleteBooks(category.getCategoryId())
                .then()
                .statusCode(NO_CONTENT.getStatusCode());

        verifyBooks(new ArrayList<>());
    }

    private void verifyCategory(Category category) {
        client.getCategory(category.getCategoryId())
                .then()
                .statusCode(OK.getStatusCode())
                .body("categoryId", equalTo(category.getCategoryId()))
                .body("categoryName", equalTo(category.getCategoryName()));
    }

    private void verifyBooks(List<Book> expBooks) {
        Response response = client
                .getBooks(category.getCategoryId());
        response
                .then()
                .statusCode(OK.getStatusCode());

        List<Book> books = response.jsonPath().getList("", Book.class);

        Assert.assertEquals(books, expBooks);
    }
}