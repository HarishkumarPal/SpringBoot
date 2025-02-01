package com.ty.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.entity.Rating;
import com.ty.entity.User;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
	List<Rating> findByUser(User user);
}
