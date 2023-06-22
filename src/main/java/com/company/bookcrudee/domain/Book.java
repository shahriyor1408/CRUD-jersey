package com.company.bookcrudee.domain;

import lombok.*;

import java.sql.Date;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 11/06/23 15:18
 * book-crud-ee
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Book {
    private Long id;
    private String name;
    private String author;
    private String description;
    private Date createdAt;
    private Date updatedAt;
}
