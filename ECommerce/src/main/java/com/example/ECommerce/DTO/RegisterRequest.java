package com.example.ECommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String password;
}
