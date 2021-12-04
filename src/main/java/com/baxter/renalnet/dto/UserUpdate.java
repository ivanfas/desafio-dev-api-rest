package com.baxter.renalnet.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserUpdate {
	
	@NotNull
	Long idUser;
	
	@NotNull
	String comment;
	
}
