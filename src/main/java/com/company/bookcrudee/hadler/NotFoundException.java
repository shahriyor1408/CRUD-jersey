package com.company.bookcrudee.hadler;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 11/06/23 17:22
 * book-crud-ee
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
