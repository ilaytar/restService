package dataobject;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Entity("category")
@XmlRootElement(name = "category")
public class Category {

    @Id
    private String categoryId;
    private String categoryName;
    @Reference
    private List<Book> books = new ArrayList<>();

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(String id) {
        Optional<Book> book = books.stream()
                .filter(b -> b.getBookId().equals(id))
                .findAny();
        return book.isPresent() ? book.get() : null;
    }

    public void addBooks(List<Book> books) {
        this.books.addAll(books);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void deleteBooks(List<Book> books) {
        this.books.removeAll(books);
    }

    public void deleteBook(Book book) {
        this.books.remove(book);
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
