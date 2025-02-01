package com.ty.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.dto.PresentationResponse;
import com.ty.entity.Presentation;

public interface PresentationRepository extends JpaRepository<Presentation, Integer> {

	@Query("SELECT p FROM Presentation p WHERE p.user.status = com.ty.enums.Status.ACTIVE AND p.user.role = com.ty.enums.Role.STUDENT AND p.user.id = :userId")
	List<Presentation> findActiveStudentPresentationsByUserId(Integer userId);

	Optional<Presentation> findByTopic(String topic);

}
