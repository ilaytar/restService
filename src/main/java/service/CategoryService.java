package service;

import dataaccess.CategoryDAO;
import dataobject.Book;
import dataobject.Category;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.Status;

@Path("/categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryService {

    private Logger logger = Logger.getLogger(CategoryService.class);
    private CategoryDAO categoryDAO = new CategoryDAO();

    private CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public String test() {
        return "Hello World";
    }

    @POST
    public Response addCategory(Category category) {
        logger.info("addCategory called");
        getCategoryDAO().addCategory(category);
        return Response.status(Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Category getCategory(@PathParam("id") String id) {
        logger.info("getCategory called with category id: " + id);
        Category category = getCategoryDAO().getCategory(id);
        if (category == null) {
            throw new NotFoundException("Requested category not found");
        }
        return category;
    }

    @PUT
    @Path("/{id}")
    public Response updateCategory(@PathParam("id") String id, Category category) {
        logger.info("updateCategory with category id : " + category.getCategoryId());
        Category dbCategory = getCategoryDAO().getCategory(id);
        if (dbCategory == null) {
            throw new NotFoundException("Requested category not found");
        }
        getCategoryDAO().updateCategory(id, category);
        return Response.status(Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCategory(@PathParam("id") String id) {
        logger.info("deleteCategory with category id : " + id);
        Category dbCategory = getCategoryDAO().getCategory(id);
        if (dbCategory == null) {
            throw new NotFoundException("Requested category not found");
        }
        getCategoryDAO().deleteCategory(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @Path("/{id}/books")
    public Response addBooks(@PathParam("id") String id, List<Book> books) {
        logger.info("adding books to category with id : " + id);
        Category dbCategory = getCategoryDAO().getCategory(id);
        if (dbCategory == null) {
            throw new NotFoundException("Requested category not found");
        }
        getCategoryDAO().addBooks(dbCategory, books);
        return Response.status(Status.OK).build();
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooks(@PathParam("id") String id) {
        logger.info("getting books for category with id : " + id);

        Category dbCategory = getCategoryDAO().getCategory(id);
        if (dbCategory == null) {
            throw new NotFoundException("Requested category not found");
        }
        return dbCategory.getBooks();
    }

    @DELETE
    @Path("/{id}/books/{bId}")
    public Response deleteBook(@PathParam("id") String id, @PathParam("bId") String bId) {
        logger.info("delete book with id : " + bId);

        Category category = getCategoryDAO().getCategory(id);
        if (category != null) {
            Book book = category.getBook(bId);
            if (book == null) {
                throw new NotFoundException("Requested book not found");
            }
        } else
            throw new NotFoundException("Requested category not found");
        getCategoryDAO().deleteBook(category, bId);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}/books")
    public Response deleteBooks(@PathParam("id") String id) {
        logger.info("delete book with id : " + id);

        Category category = getCategoryDAO().getCategory(id);
        if (category != null) {
            List<Book> books = category.getBooks();
            if (books == null) {
                throw new NotFoundException("Requested books not found");
            }
            getCategoryDAO().deleteBooks(category);
        } else throw new NotFoundException("Requested category not found");
        return Response.status(Status.NO_CONTENT).build();
    }
}