package org.luongnm93.quarkus.starting;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.List;
import java.util.Optional;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    Logger logger;

    @Inject
    BookRepository bookRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAllBooks() {
        logger.info("Getting all books");
        return bookRepository.getAllBooks();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public int count() {
        logger.info("Counting books");
        return bookRepository.getAllBooks().size();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Optional<Book> getBook(@PathParam("id") int id) {
        logger.infof("Getting book with id %d", id);
        return bookRepository.getBook(id);
    }

}
