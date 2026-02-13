package com.markoblazhevski.inventorybackend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private T data;
    private LocalDateTime timestamp;
}
