package com.company.bookcrudee.service;

import com.company.bookcrudee.domain.Book;
import com.company.bookcrudee.dto.BookCreateDto;
import com.company.bookcrudee.dto.BookUpdateDto;
import com.company.bookcrudee.repo.BookRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 11/06/23 15:46
 * book-crud-ee
 */

public class BookService {
    private final BookRepository repository = new BookRepository();

    public Book get(Long id) {
        return repository.getAll().stream().filter(book -> book.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Book not found!"));
    }

    public List<Book> getAll(String search) {
        List<Book> books = repository.getAll();
        if (Objects.equals(search, "")) {
            return books;
        } else {
            return books.stream().filter(book -> book.getName().toLowerCase().contains(search.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(search.toLowerCase())).toList();
        }
    }

    public Long create(BookCreateDto dto) {
        Book book = Book.builder()
                .name(dto.getName())
                .author(dto.getAuthor())
                .description(dto.getDescription())
                .createdAt(Date.valueOf(LocalDate.now()))
                .build();
        return repository.save(book);
    }

    public Long update(BookUpdateDto dto) {
        Book book = get(dto.getId());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        return repository.update(book);
    }

    public Long delete(Long id) {
        return repository.delete(id);
    }
}
