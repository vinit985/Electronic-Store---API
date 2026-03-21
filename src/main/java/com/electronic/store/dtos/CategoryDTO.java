package com.electronic.store.dtos;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {

    private Integer categoryId;
    @NotBlank
    @Size(min = 1,message = "Title must be of minimum 1 character")
    private String title;
    @NotBlank(message = "Description is mandatory")
    private String description;
    private String coverImage;
}
