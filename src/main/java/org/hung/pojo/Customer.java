package org.hung.pojo;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("my_customer_table")
public class Customer {

	private @Id Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	@Column("date_of_birth")
	private LocalDate dob;
}
