package com.ty.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ty.Exceptions.InvalidRoleException;
import com.ty.Exceptions.PresentationNotFound;
import com.ty.Exceptions.RatingNotFound;
import com.ty.Exceptions.UnauthorizedActionException;
import com.ty.Exceptions.UserNotFound;
import com.ty.Repository.PresentationRepository;
import com.ty.Repository.RatingRepository;
import com.ty.Repository.UserRepository;
import com.ty.dto.RatingRequest;
import com.ty.dto.RatingResponse;
import com.ty.entity.Presentation;
import com.ty.entity.Rating;
import com.ty.entity.User;
import com.ty.enums.PresentationStatus;
import com.ty.enums.Role;
import com.ty.enums.Status;

@Service
public class RatingService {
	private PresentationRepository pr;

	private UserRepository ur;

	private RatingRepository rr;

	public RatingService(PresentationRepository pr, UserRepository ur, RatingRepository rr) {
		this.pr = pr;
		this.ur = ur;
		this.rr = rr;
	}

	public String getrating(Integer aid, Integer sid, Integer pid, RatingRequest request) {
		User admin = ur.findById(aid).orElseThrow(() -> new UserNotFound("Admin Not Found"));
		User student = ur.findById(sid).orElseThrow(() -> new UserNotFound("Student Not Found"));
		Presentation presentation = pr.findById(pid)
				.orElseThrow(() -> new PresentationNotFound("Presentaion Not Found"));
		if (!admin.getRole().equals(Role.ADMIN)) {
			throw new InvalidRoleException("Only an admin can view ratings.");
		}
		if (!admin.getStatus().equals(Status.ACTIVE)) {
			throw new UnauthorizedActionException("Admin account is not active.");
		}
		if (!student.getRole().equals(Role.STUDENT)) {
			throw new UnauthorizedActionException("User is not a student.");
		}
		if (!student.getStatus().equals(Status.ACTIVE)) {
			throw new UnauthorizedActionException("Student account is not active.");
		}
		if (!presentation.getUser().getUid().equals(sid)) {
			throw new PresentationNotFound("Presentation does not belong to the specified student.");
		}
		if (!presentation.getPresentationstatus().equals(PresentationStatus.COMPLETED)) {
			return "Presentation does not completed.";
		}

		Double averageScore = (request.getCommunication() + request.getConfidence() + request.getInteraction()
				+ request.getLiveliness() + request.getUsageProps() + request.getContent()) / 6.0;

		Rating rating = new Rating();
		rating.setUser(student);
		rating.setPresentation(presentation);
		presentation.setUserTotalScorce(averageScore);
		rating.setTotalScore(averageScore);

		BeanUtils.copyProperties(request, rating);

		rr.save(rating);

		return "Rating added to student with id " + student.getUid() + " and presentaion is " + presentation.getTopic();

	}

	public RatingResponse getrating(Integer rid) {
		Rating rating = rr.findById(rid).orElseThrow(() -> new RatingNotFound("Rating Not Found"));
		RatingResponse response = new RatingResponse();
		BeanUtils.copyProperties(rating, response);
		Presentation presentation = rating.getPresentation();

		return response;
	}

	public List<RatingResponse> getallrating(Integer sid) {
		User user = ur.findById(sid).orElseThrow(() -> new UserNotFound("User Not Found"));

		List<Rating> list = rr.findByUser(user);
		if (!user.getRole().equals(Role.STUDENT)) {
			throw new UnauthorizedActionException("User is not a student.");
		}
		if (!user.getStatus().equals(Status.ACTIVE)) {
			throw new UnauthorizedActionException("Student account is not active.");
		}List<RatingResponse> responselist = new ArrayList();
		for (Rating rating : list) {
			
			RatingResponse ratingResponse = new RatingResponse();
			BeanUtils.copyProperties(ratingResponse, list);

		}

		return responselist;
		
	}

	private void newArrayList() {
		// TODO Auto-generated method stub

	}

}
