package com.ty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ty.Exceptions.InvalidRoleException;
import com.ty.Exceptions.PresentationNotFound;
import com.ty.Exceptions.UnauthorizedActionException;
import com.ty.Exceptions.UserNotFound;
import com.ty.Repository.PresentationRepository;
import com.ty.Repository.RatingRepository;
import com.ty.Repository.UserRepository;
import com.ty.dto.PresentationRequest;
import com.ty.dto.PresentationResponse;
import com.ty.entity.Presentation;
import com.ty.entity.User;
import com.ty.enums.PresentationStatus;
import com.ty.enums.Role;
import com.ty.enums.Status;

@Service
public class PresentationService {

	private PresentationRepository pr;

	private UserRepository ur;

	private RatingRepository rr;

	public PresentationService(PresentationRepository pr, UserRepository ur) {

		this.pr = pr;
		this.ur = ur;
	}

	public String addPresentation(Integer uid, Integer sid, PresentationRequest request) {
	    
	    User admin = ur.findById(uid).orElseThrow(() -> new UserNotFound("Admin Not Found"));

	
	    User stu = ur.findById(sid).orElseThrow(() -> new UserNotFound("Student Not Found"));

	 
	    if (!admin.getRole().equals(Role.ADMIN)) {
	        return "Only an admin can assign presentations.";
	    }

	  
	    if (!stu.getRole().equals(Role.STUDENT)) {
	        return "Target user is not a student.";
	    }

	   
	    if (!stu.getStatus().equals(Status.ACTIVE)) {
	        return "Student ID is not active.";
	    }
	    
	    Presentation presentation = new Presentation();
	    BeanUtils.copyProperties(request, presentation); 
	    presentation.setUser(stu);  
	    stu.setUserTotalScore(presentation.getUserTotalScorce());  

	    pr.save(presentation);

	    return "Presentation Assigned to Student " + stu.getName();
	}


	public PresentationResponse getPresentation(Integer pid) {

		Presentation presentation = pr.findById(pid)
				.orElseThrow(() -> new PresentationNotFound("Presentation Not Present"));
		PresentationResponse pres = new PresentationResponse();
		BeanUtils.copyProperties(presentation, pres);
		return pres;
	}

	public List<PresentationResponse> getPresentationList(Integer aid, Integer sid) throws RuntimeException {

		User student = ur.findById(sid).orElseThrow(() -> new UserNotFound("Student Not Found"));
		User admin = ur.findById(aid).orElseThrow(() -> new UserNotFound("Admin Not Found"));

		if (!student.getRole().equals(Role.STUDENT)) {
			throw new UnauthorizedActionException("User is not a student");
		}
		if (!admin.getRole().equals(Role.ADMIN)) {
			throw new InvalidRoleException("Only an admin can get list of presentations.");
		}

		if (!admin.getStatus().equals(Status.ACTIVE)) {
			throw new UnauthorizedActionException("Admin account is not active");
		}

		if (!student.getStatus().equals(Status.ACTIVE)) {
			throw new UnauthorizedActionException("Student is not active");
		}

		List<PresentationResponse> response = new ArrayList<>();

		List<Presentation> presentationList = student.getPresentation();
		if (presentationList != null) {
			for (Presentation presentation : presentationList) {
				PresentationResponse presentationResponse = new PresentationResponse();
				BeanUtils.copyProperties(presentation, presentationResponse);
				response.add(presentationResponse);
			}
		}

		return response;
	}

	public String updatePresentationStatus(Integer sid, Integer pid, PresentationStatus pstatus) {
		User user = ur.findById(sid).orElseThrow(() -> new UserNotFound("User Not Found"));
		Presentation presentation = pr.findById(pid)
				.orElseThrow(() -> new PresentationNotFound("Presentatuin Not Found"));
		if (!user.getStatus().equals(Status.ACTIVE)) {
			throw new UnauthorizedActionException("Student is not active");
		}
		if (!user.getRole().equals(Role.STUDENT)) {
			throw new UnauthorizedActionException("User is not a student");
		}
		if (user.getUid() == (presentation.getUser().getUid())) {
			presentation.setPresentationstatus(pstatus);
			user.setUserTotalScore(presentation.getUserTotalScorce());
			pr.save(presentation);
		}
		return "Presentation Status Updated as " + pstatus;
	}

}
