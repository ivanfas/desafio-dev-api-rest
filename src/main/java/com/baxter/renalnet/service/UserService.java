package com.baxter.renalnet.service;

import com.baxter.renalnet.dto.UserCreate;
import com.baxter.renalnet.dto.UserFilter;
import com.baxter.renalnet.dto.UserResponse;
import com.baxter.renalnet.dto.UserUpdate;
import com.baxter.renalnet.exception.ApiException;
import com.baxter.renalnet.exception.ApiExceptionDomain;
import com.baxter.renalnet.exception.ApiExceptionDomain.ExceptionType;
import com.baxter.renalnet.model.User;
import com.baxter.renalnet.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<UserResponse> find(UserFilter filter) {
		User userExample = convertToEntity(filter);
		userExample.setActive(true);

		List<UserResponse> result = new ArrayList<>();

		int page = filter.getPage() != null ? filter.getPage() : 0;
		int size = filter.getSize() != null && filter.getSize() < 1000 ? filter.getSize() : 1000;
		Pageable basePage = PageRequest.of(page, size);

		/*
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAll()
				.withMatcher("comment", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
				.withMatcher("active", ExampleMatcher.GenericPropertyMatchers.exact());

		 */
		Example<User> example = Example.of(userExample);

		Page<User> queryResult = userRepository.findAll(example, basePage);
		queryResult.map(user -> result.add(convertToResponse(user)));

		return result;
	}

	public Optional<UserResponse> find(Long id) {
		return userRepository.findById(id).map(this::convertToResponse);
	}

	public Long create(@Valid UserCreate dto) {
		User user = convertToEntity(dto);
		LocalDateTime now = LocalDateTime.now();
		user.setDataAtualizacao(now);
		user.setDataCriacao(now);
		user.setId(null);
		user.setActive(true);
		userRepository.save(user);
		return user.getId();
	}

	public void update(@Valid UserUpdate dto, Long id) throws ApiException {
		User original = userRepository.findById(id)
				.orElseThrow(() -> new ApiException(User.class.getSimpleName(),
						id.toString(), ExceptionType.NOT_FOUND));
		original.setDataAtualizacao(LocalDateTime.now());
		userRepository.save(original);
	}

	public void remove(@Valid Long id) throws ApiException {
		User original = userRepository.findById(id)
				.orElseThrow(() -> new ApiException(User.class.getSimpleName(), id.toString(), ApiExceptionDomain.ExceptionType.NOT_FOUND));
		original.setActive(false);
		userRepository.save(original);
	}

	private UserResponse convertToResponse(User user) {
		return modelMapper.map(user, UserResponse.class);
	}

	private User convertToEntity(UserCreate userDto) {
		return modelMapper.map(userDto, User.class);
	}

	private User convertToEntity(UserFilter filter) {
		return modelMapper.map(filter, User.class);
	}

}
