package com.example.demo.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {

    @Pattern(regexp = "^[A-Za-z]\\w{2,29}$", message = "the name must be more than 2 character")
    private String name;

    @Length(min = 4, max = 4)
    private String publishedYear;

    private int inventory;
}
