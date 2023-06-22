package com.company.bookcrudee.repo;

import com.company.bookcrudee.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 12/06/23 12:39
 * book-crud-ee
 */

public class BookRepository {
    Connection connection = null;

    public BookRepository() {
        String username = "postgres";
        String password = "123";
        String url = "jdbc:postgresql://localhost:5432/postgres";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "select * from book";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .author(resultSet.getString(3))
                        .description(resultSet.getString(4))
                        .createdAt(resultSet.getDate(5))
                        .updatedAt(resultSet.getDate(6))
                        .build();
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(books);
        return books;
    }

    public Long save(Book book) {
        int i = -1;
        String sql = "insert into book(name,author,description,createdAt,updatedAt) values (?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getDescription());
            statement.setDate(4, book.getCreatedAt());
            statement.setDate(5, book.getUpdatedAt());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (long) i;
    }

    public Long update(Book book) {
        updateAuthor(book);
        updateDescription(book);
        updateUpdatedAt(book);
        int i = -1;
        String sql = "update book set name = ? where id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getName());
            statement.setLong(2, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (long) i;
    }

    private void updateUpdatedAt(Book book) {
        String sql = "update book set author = ? where id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getAuthor());
            statement.setLong(2, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateDescription(Book book) {
        String sql = "update book set description = ? where id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getDescription());
            statement.setLong(2, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateAuthor(Book book) {
        String sql = "update book set updatedAt = current_date where id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long delete(Long id) {
        String SQL = "DELETE FROM book WHERE id = ?";

        int affectedrows = 0;

        try {
            PreparedStatement pstmt = connection.prepareStatement(SQL);
            pstmt.setLong(1, id);
            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return (long) affectedrows;
    }
}
