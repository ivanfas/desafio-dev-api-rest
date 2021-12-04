package com.baxter.renalnet.controller.impl;

import com.baxter.renalnet.controller.UserController;
import com.baxter.renalnet.dto.*;
import com.baxter.renalnet.exception.ApiException;
import com.baxter.renalnet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
class UserControllerImpl implements UserController {

	private final Logger log = LoggerFactory.getLogger(UserControllerImpl.class);

	@Autowired
	private UserService service;

	@Override
	public Collection<UserResponse> find(UserFilter filter) {
		return service.find(filter);
	}

	@Override
	public ResponseEntity<?> findOne(Long id) {
		Optional<UserResponse> result = service.find(id);
		return result.map(response -> ResponseEntity.ok().body(response)).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<ApiResponse> create(UserCreate dto) throws URISyntaxException {
		log.trace("Request to create user: {}", dto);
		Long idQuestion = service.create(dto);
		return ResponseEntity.created(new URI("/api/users/" + idQuestion))
				.body(new ApiResponse(idQuestion, HttpStatus.CREATED));
	}

	@Override
	public ResponseEntity<ApiResponse> update(Long id, UserUpdate dto) throws ApiException {
		log.trace("Request to update user: {}", dto);
		service.update(dto, id);
		return ResponseEntity.ok()
				.body(new ApiResponse(id, HttpStatus.OK));
	}

	@Override
	public ResponseEntity<?> remove(Long id) throws ApiException {
		log.trace("Request to delete user: {}", id);
		service.remove(id);
		return ResponseEntity.ok().body("User removed: " + id);
	}
}