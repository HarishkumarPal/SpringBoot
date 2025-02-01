package com.ty.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ty.enums.Role;
import com.ty.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "user_info")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;

	private String name;

	@Email
//	@NotEmpty(message = "Email should not be empty")
	private String email;

//	@NotNull(message = "Phone should not be empty")
//	@Column(unique = true ,nullable = false)
	private Long phone;

//	@NotEmpty(message = "Password should not be empty")
//	@Column(unique = true ,nullable = false)
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Presentation> presentation;

//	@Column(unique = true ,nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private Role role;

	private Double userTotalScore;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;
}
