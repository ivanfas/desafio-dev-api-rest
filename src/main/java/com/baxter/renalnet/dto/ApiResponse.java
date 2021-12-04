package com.baxter.renalnet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse {
	
	Long id;
	HttpStatus status;

}
