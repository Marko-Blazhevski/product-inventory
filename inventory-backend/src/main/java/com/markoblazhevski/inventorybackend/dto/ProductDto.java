package com.markoblazhevski.inventorybackend.dto;

import com.markoblazhevski.inventorybackend.model.Product;
import com.markoblazhevski.inventorybackend.model.enums.Category;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
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

    private Category category;
    private String imageUrl;

    public ProductDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.quantityInStock = entity.getQuantityInStock();
        this.category = entity.getCategory();
        this.imageUrl = entity.getImageUrl();
    }
}