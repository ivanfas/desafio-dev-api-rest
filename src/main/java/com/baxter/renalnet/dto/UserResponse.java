package com.baxter.renalnet.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    Long id;
    String name;
    String address;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
