package com.markoblazhevski.inventorybackend.model;

import com.markoblazhevski.inventorybackend.model.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required!")
    private String name;

    private String description;

    @NotNull(message = "Price is required!")
    @DecimalMin(value = "0.0", message = "Price must be non-negative")
    private BigDecimal price;

    @NotNull(message = "Quantity is required!")
    @Min(value = 0, message = "Quantity must be non-negative")
    private Integer quantityInStock;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String imageUrl;
}
