package com.markoblazhevski.inventorybackend.controller;

import com.markoblazhevski.inventorybackend.dto.ProductDto;
import com.markoblazhevski.inventorybackend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(name, category, PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> createProduct(
            @Valid @RequestPart("product") ProductDto productDto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return new ResponseEntity<>(productService.createProduct(productDto, file), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/image")
    public ProductDto uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        return productService.uploadProductImage(id, file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductDto updateProduct(
            @PathVariable Long id,
            @Valid @RequestPart("product") ProductDto productDto, // The JSON part
            @RequestPart(value = "file", required = false) MultipartFile file // Optional image
    ) {
        return productService.updateProduct(id, productDto, file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}