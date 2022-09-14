package com.qa.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "acc_details")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private Long id;
	
	@NotNull
	@Column(name = "acc_number", unique=true)
	private String accountNumber;
	
	@Column(name = "acc_name")
	private String name;
	
	@Column(name="acc_username")
	private String username;
	
	@Column(name="acc_password")
	private String password;
	
}
