package com.baxter.renalnet.controller;

import com.baxter.renalnet.dto.*;
import com.baxter.renalnet.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Collection;

public interface UserController {

    @GetMapping
    Collection<UserResponse> find(UserFilter filter);

    @GetMapping("/{id}")
    ResponseEntity<?> findOne(@PathVariable Long id);

    @PostMapping
    ResponseEntity<ApiResponse> create(
            @Valid @RequestBody UserCreate dto
    ) throws URISyntaxException, ApiException;

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdate dto
    ) throws ApiException;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) throws ApiException;

}