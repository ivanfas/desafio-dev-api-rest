package com.baxter.renalnet.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class UserCreate {

	@NotEmpty
	@Size(max = 100)
	String name;

	@NotEmpty
	@Size(max = 100)
	String address;

	@NotEmpty
	@Size(max = 100)
	@Email
	String email;

}
