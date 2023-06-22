package com.company.bookcrudee.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author "Sohidjonov Shahriyor"
 * @since 11/06/23 16:03
 * book-crud-ee
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
public class AppErrorDto {
    private String message;
    private String friendlyMessage;
    private LocalDateTime creationTime;
    private Integer status;
}
