package com.markoblazhevski.inventorybackend.config;

import com.markoblazhevski.inventorybackend.model.Product;
import com.markoblazhevski.inventorybackend.model.enums.Category;
import com.markoblazhevski.inventorybackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            Product p1 = Product.builder()
                    .name("Gaming Laptop")
                    .description("High-end gaming laptop with RTX 4080")
                    .price(new BigDecimal("1500.00"))
                    .quantityInStock(15)
                    .category(Category.ELECTRONICS)
                    .imageUrl("/api/products/images/gaming_laptop.png")
                    .build();

            Product p2 = Product.builder()
                    .name("Coffee Maker")
                    .description("Automatic espresso machine")
                    .price(new BigDecimal("299.99"))
                    .quantityInStock(50)
                    .category(Category.HOME_APPLIANCES)
                    .imageUrl("/api/products/images/coffee-maker.png")
                    .build();

            Product p3 = Product.builder()
                    .name("Java Programming Book")
                    .description("Complete guide to modern Java")
                    .price(new BigDecimal("45.00"))
                    .quantityInStock(100)
                    .category(Category.BOOKS)
                    .imageUrl("/api/products/images/java_programming_book.png")
                    .build();

            Product p4 = Product.builder()
                    .name("Gaming Laptop 2")
                    .description("High-end gaming laptop with RTX 4080")
                    .price(new BigDecimal("1500.00"))
                    .quantityInStock(15)
                    .category(Category.ELECTRONICS)
                    .imageUrl("/api/products/images/gaming_laptop.png")
                    .build();

            Product p5 = Product.builder()
                    .name("Coffee Maker 2")
                    .description("Automatic espresso machine")
                    .price(new BigDecimal("299.99"))
                    .quantityInStock(50)
                    .category(Category.HOME_APPLIANCES)
                    .imageUrl("/api/products/images/coffee-maker.png")
                    .build();

            Product p6 = Product.builder()
                    .name("Java Programming Book 2")
                    .description("Complete guide to modern Java")
                    .price(new BigDecimal("45.00"))
                    .quantityInStock(100)
                    .category(Category.BOOKS)
                    .imageUrl("/api/products/images/java_programming_book.png")
                    .build();

            Product p7 = Product.builder()
                    .name("Gaming Laptop 3")
                    .description("High-end gaming laptop with RTX 4080")
                    .price(new BigDecimal("1500.00"))
                    .quantityInStock(15)
                    .category(Category.ELECTRONICS)
                    .imageUrl("/api/products/images/gaming_laptop.png")
                    .build();

            Product p8 = Product.builder()
                    .name("Coffee Maker 3")
                    .description("Automatic espresso machine")
                    .price(new BigDecimal("299.99"))
                    .quantityInStock(50)
                    .category(Category.HOME_APPLIANCES)
                    .imageUrl("/api/products/images/coffee-maker.png")
                    .build();

            Product p9 = Product.builder()
                    .name("Java Programming Book 3")
                    .description("Complete guide to modern Java")
                    .price(new BigDecimal("45.00"))
                    .quantityInStock(100)
                    .category(Category.BOOKS)
                    .imageUrl("/api/products/images/java_programming_book.png")
                    .build();

            Product p10 = Product.builder()
                    .name("Gaming Laptop 4")
                    .description("High-end gaming laptop with RTX 4080")
                    .price(new BigDecimal("1500.00"))
                    .quantityInStock(15)
                    .category(Category.ELECTRONICS)
                    .imageUrl("/api/products/images/gaming_laptop.png")
                    .build();

            Product p11 = Product.builder()
                    .name("Coffee Maker 4")
                    .description("Automatic espresso machine")
                    .price(new BigDecimal("299.99"))
                    .quantityInStock(50)
                    .category(Category.HOME_APPLIANCES)
                    .imageUrl("/api/products/images/coffee-maker.png")
                    .build();

            Product p12 = Product.builder()
                    .name("Java Programming Book 4")
                    .description("Complete guide to modern Java")
                    .price(new BigDecimal("45.00"))
                    .quantityInStock(100)
                    .category(Category.BOOKS)
                    .imageUrl("/api/products/images/java_programming_book.png")
                    .build();

            repository.saveAll(List.of(p1, p2, p3,  p4, p5, p6, p7, p8, p9, p10, p12));
            System.out.println("Successfully seeded 12 products into the database.");
        }
    }
}