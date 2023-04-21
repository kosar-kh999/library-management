package com.example.demo.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.Pattern;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookNameDto {

    @Pattern(regexp = "^[A-Za-z]\\w{2,29}$", message = "the name must be more than 2 character")
    private String name;
}
