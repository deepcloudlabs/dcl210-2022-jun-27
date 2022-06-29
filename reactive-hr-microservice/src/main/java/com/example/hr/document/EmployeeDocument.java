package com.example.hr.document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "employees")
@Data
public class EmployeeDocument {
	@Id
	private String identity;
	@Field(name = "fn")
	@Size(min=5)
	private String fullName;
	@Indexed(unique = true)
	@NotBlank
	private String iban;
	@Min(4500)
	private double salary;
	@Field(name = "birth_year")
	@Indexed
	@Max(2006)
	private int birthYear;
}
