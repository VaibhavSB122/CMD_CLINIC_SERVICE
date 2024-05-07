package com.tg.cmd.cmd_clinic_service.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullName {

	@Column(name="First_Name",length = 50,nullable = false )
	private String firstName;
	@Column(name="Middle_Name",length = 50)
	private String middleName;
	@Column(name="Last_Name",length = 50,nullable = false )
	private String lastName;

}