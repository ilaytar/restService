package dataaccess;

import dataobject.Book;
import dataobject.Category;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.*;

public class CategoryDAO {
    private static Map<String, Category> categoryMap =
            new HashMap<String, Category>();
    private static Map<String, Collection<Book>> bookMap =
            new HashMap<String, Collection<Book>>();
    private static Datastore datastore = new MorphiaDatastore().getDatastore();


    public void addCategory(Category category) {
        datastore.save(category);
    }

    public void addBook(Category category, Book book) {
        category.addBook(book);
        datastore.save(book);
        datastore.save(category);
    }

    // Add Books to the Category
    public void addBooks(Category category, List<Book> books) {
        category.addBooks(books);
        datastore.save(books);
        datastore.save(category);
    }

    public Category getCategory(String categoryId) {
        return datastore.createQuery(Category.class)
                .field("categoryId")
                .equal(categoryId)
                .get();
    }

    public void deleteCategory(String categoryId) {
        Query<Category> query = datastore
                .createQuery(Category.class)
                .field("categoryId")
                .equal(categoryId);

        Category category = query.get();
        if (category != null) {
            for (Book book : category.getBooks())
                datastore.delete(book);
        }
        datastore.delete(query);
    }

    public void updateCategory(String id, Category category) {
        Query<Category> updateQuery = datastore
                .createQuery(Category.class)
                .filter("categoryId", id);

        UpdateOperations<Category> updateOperations = datastore
                .createUpdateOperations(Category.class)
                .set("categoryName", category.getCategoryName());

        datastore.update(updateQuery, updateOperations);
    }

    public void deleteBook(Category category, String bookId) {
        Category dbCategory = getCategory(category.getCategoryId());
        if (dbCategory != null) {
            List<Book> books = dbCategory.getBooks();
            for (Book book : books) {
                if (book.getBookId().equals(bookId)) {
                    category.deleteBook(book);
                    datastore.save(category);
                    datastore.delete(book);
                }
            }
        }
    }

    public void deleteBooks(Category category) {
        Category dbCategory = getCategory(category.getCategoryId());
        if (dbCategory != null) {
            List<Book> books = dbCategory.getBooks();
            category.deleteBooks(books);
            datastore.save(category);
            for (Book book : books)
                datastore.delete(book);
        }
    }
}
