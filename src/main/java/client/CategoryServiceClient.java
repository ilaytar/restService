package client;

import dataobject.Book;
import dataobject.Category;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CategoryServiceClient {

    public CategoryServiceClient() {
        RestAssured.baseURI = "http://localhost:8084";
        RestAssured.basePath = "/restService/categories";
    }

    private Logger log = Logger.getLogger(CategoryServiceClient.class);

    public Response addCategory(Category category) {
        log.info("Adding category with id = " + category.getCategoryId());

        return given()
                .contentType(ContentType.JSON)
                .body(category)
                .when()
                .post("/");
    }

    public Response getCategory(String id) {
        log.info("Getting category with id = " + id);

        return given()
                .pathParam("id", id)
                .get("/{id}");
    }

    public Response updateCategory(String id, Category category) {
        log.info("Updating category with id = " + id);

        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(category)
                .when()
                .put("/{id}");
    }

    public Response deleteCategory(String id) {
        log.info("Removing category with id = " + id);

        return given()
                .pathParam("id", id)
                .when()
                .delete("/{id}");
    }

    /*public Response addBook(String id, Book book) {
        log.info("Adding book to category with id : " + id);

        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(book)
                .post("/{id}/books");
    }*/

    public Response addBooks(String id, List<Book> books) {
        log.info("Adding books to category with id : " + id);

        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(books)
                .post("/{id}/books");
    }

    public Response getBooks(String id) {
        log.info("Get books for category with id : " + id);

        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .get("/{id}/books");
    }

    public Response deleteBook(String id, String bId) {
        log.info("Deleting book with bookId: " + bId + " for category with category id : " + id);

        return given()
                .pathParam("id", id)
                .pathParam("bId", bId)
                .when()
                .delete("/{id}/books/{bId}");
    }

    public Response deleteBooks(String id) {
        log.info("Deleting all books for category with category id : " + id);

        return given()
                .pathParam("id", id)
                .when()
                .delete(String.format("/{id}/books"), id);
    }
}