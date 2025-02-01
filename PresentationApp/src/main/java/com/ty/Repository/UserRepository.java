package com.ty.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.entity.User;
import com.ty.enums.Role;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);

	List<User> findAllByRole(Role role);

	List<User> findAllByUid(Integer uid);

}
