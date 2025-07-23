package com.enpointe.employeeManagementSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "API response for errors.")
public class SyncResponse {

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "error type", example = "Bad request")
    private String error;

    @Schema(description = "error message", example = "Email must be unique")
    private String message;

    @Schema(description = "time when error occurred", example = "2025-07-24T00:20:12")
    private LocalDateTime timeStamp;

}
