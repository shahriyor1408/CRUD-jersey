package com.company.bookcrudee.dto;

import lombok.*;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 11/06/23 15:43
 * book-crud-ee
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookUpdateDto {
    private Long id;
    private String name;
    private String author;
    private String description;
}
