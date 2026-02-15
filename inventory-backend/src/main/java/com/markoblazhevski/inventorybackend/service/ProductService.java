package com.markoblazhevski.inventorybackend.service;

import com.markoblazhevski.inventorybackend.dto.ProductDto;
import com.markoblazhevski.inventorybackend.exception.ProductNotFoundException;
import com.markoblazhevski.inventorybackend.model.Product;
import com.markoblazhevski.inventorybackend.model.enums.Category;
import com.markoblazhevski.inventorybackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final String UPLOAD_DIR = "./uploads/";

    public Page<ProductDto> getAllProducts(String name, String categoryStr, Pageable pageable) {
        boolean hasName = StringUtils.hasText(name);
        Category category = parseCategory(categoryStr);
        Page<Product> response = null;

        Pageable sortedByName = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("name").ascending()
        );

        if (hasName && category != null) {
            response = productRepository.findByNameContainingIgnoreCaseAndCategory(name, category, sortedByName);
        } else if (hasName) {
            response = productRepository.findByNameContainingIgnoreCase(name, sortedByName);
        } else if (category != null) {
            response = productRepository.findByCategory(category, sortedByName);
        } else {
            response = productRepository.findAll(sortedByName);
        }
        return response.map(ProductDto::new);
    }

    private Category parseCategory(String categoryStr) {
        if (!StringUtils.hasText(categoryStr)) {
            return null;
        }
        try {
            return Category.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid category: " + categoryStr);
        }
    }

    public ProductDto getProductById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return new ProductDto(p);
    }

    public ProductDto createProduct(ProductDto dto, MultipartFile file) {
        Product product = mapToEntity(dto);
        product = productRepository.save(product);
        return uploadProductImage(product.getId(), file);
    }

    public ProductDto updateProduct(Long id, ProductDto details, MultipartFile file) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(details.getName());
        existingProduct.setDescription(details.getDescription());
        existingProduct.setPrice(details.getPrice());
        existingProduct.setQuantityInStock(details.getQuantityInStock());
        existingProduct.setCategory(details.getCategory());
        existingProduct.setImageUrl(details.getImageUrl());

        existingProduct = productRepository.save(existingProduct);

        if (file != null && !file.isEmpty()) {
            return uploadProductImage(existingProduct.getId(), file);
        }

        return new ProductDto(existingProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }

    public ProductDto uploadProductImage(Long id, MultipartFile file) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);

            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String imageUrl = "/api/products/images/" + fileName;
            product.setImageUrl(imageUrl);

            return new ProductDto(productRepository.save(product));

        } catch (IOException e) {
            throw new RuntimeException("Failed to store image file: " + e.getMessage());
        }
    }

    private Product mapToEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantityInStock(dto.getQuantityInStock())
                .category(dto.getCategory())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}