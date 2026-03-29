package com.electronic.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    @Column(name = "title")
    private String title;
    @Column(name="description" ,length = 10000)
    private String description;
    private Integer price;
    private Integer discountedPrice;
    private Integer availableQuantity;
    private Date addedDate;
    private boolean live;
    private boolean inStock;
    private String ProductImageName;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;


}
