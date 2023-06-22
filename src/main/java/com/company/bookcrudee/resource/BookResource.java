package com.company.bookcrudee.resource;

import com.company.bookcrudee.domain.Book;
import com.company.bookcrudee.dto.BookCreateDto;
import com.company.bookcrudee.dto.BookUpdateDto;
import com.company.bookcrudee.service.BookService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 11/06/23 15:38
 * book-crud-ee
 */

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookService service = new BookService();

    @GET
    @Path("/{id}")
    public Book get(@PathParam(value = "id") Long id) {
        return service.get(id);
    }

    @GET
    public List<Book> getAll(@QueryParam(value = "search") String search) {
        return service.getAll(search);
    }

    @POST
    public Long create(BookCreateDto dto) {
        return service.create(dto);
    }

    @PUT
    public Long update(BookUpdateDto dto) {
        return service.update(dto);
    }

    @DELETE
    @Path("/{id}")
    public Long delete(@PathParam(value = "id") Long id) {
        return service.delete(id);
    }
}
