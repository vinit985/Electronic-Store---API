package com.electronic.store.dtos;

import com.electronic.store.entities.Category;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {

    private Integer productId;
    @NotBlank
    @Size(min = 2,message = "Minimum size of title is 2 ")
    private String title;
    private String description;
    private Integer price;
    private Integer discountedPrice;
    private Integer availableQuantity;
    private Date addedDate;
    private boolean live;
    private boolean inStock;
    private String ProductImageName;
    private CategoryDTO categoryDTO;
}
