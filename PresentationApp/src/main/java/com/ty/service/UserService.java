package com.ty.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ty.Exceptions.UnauthorizedActionException;
import com.ty.Exceptions.UserNotFound;
import com.ty.Repository.UserRepository;
import com.ty.dto.PresentationResponse;
import com.ty.dto.StudentResponse;
import com.ty.dto.UserRequest;
import com.ty.dto.UserResponse;
import com.ty.entity.User;
import com.ty.enums.Role;
import com.ty.enums.Status;

@Service
public class UserService {

	private UserRepository ur;

	public UserService(UserRepository ur) {

		this.ur = ur;
	}

	public String registerStudent(UserRequest request) {
		Optional<User> op = ur.findByEmail(request.getEmail());

		if (op.isPresent()) {

			User user = op.get();
			return user.getRole().toString().toLowerCase() + " Already Registered";

		} else {

			User user = new User();

			user.setRole(Role.STUDENT);
			user.setStatus(Status.ACTIVE);

			BeanUtils.copyProperties(request, user);
			ur.save(user);

			return user.getRole().toString().toLowerCase() + " Registered with " + user.getEmail();
		}
	}

	public String registerAdmin(UserRequest request) {
		Optional<User> op = ur.findByEmail(request.getEmail());

		if (op.isPresent()) {

			User user = op.get();
			return user.getRole().toString().toLowerCase() + " Already Registered";

		} else {

			User user = new User();

			user.setRole(Role.ADMIN);
			user.setStatus(Status.ACTIVE);

			BeanUtils.copyProperties(request, user);
			ur.save(user);

			return user.getRole().toString().toLowerCase() + " Registered with " + user.getEmail();
		}
	}

	public String loginStudent(String email, String password) {

		User user = ur.findByEmail(email)
				.orElseThrow(() -> new UserNotFound("User Not Registered..Please Register First"));

		if (user.getStatus().equals(Status.INACTIVE)) {
			return "Your Id is Inactive Please Contact Support";
		}
		if (user.getRole().equals(Role.STUDENT) && user.getPassword().equals(password)) {

			return user.getRole().toString().toLowerCase() + " Login Successfull ";

		} else {

			return "Invalid Credentials ";

		}

	}

	public String loginAdmin(String email, String password) {

		User user = ur.findByEmail(email)
				.orElseThrow(() -> new UserNotFound("User Not Registered..Please Register First"));
		if (user.getStatus().equals(Status.INACTIVE)) {
			return "Your Id is Inactive Please Contact Support";
		}
		if (user.getRole().equals(Role.ADMIN) && user.getPassword().equals(password)) {

			return user.getRole().toString().toLowerCase() + " Login Successfull ";

		} else {

			return " Invalid Credentials ";

		}
	}

	public List<StudentResponse> getAllStudent(Integer uid, Role role) throws UnauthorizedActionException {
		User admin = ur.findById(uid).orElseThrow(() -> new UserNotFound("User Not Found"));

		if (admin.getRole().equals(Role.ADMIN) && role.equals(Role.STUDENT)) {
			List<User> students = ur.findAllByRole(role);

			List<StudentResponse> responses = new ArrayList<>();
			for (User user : students) {
				StudentResponse response = new StudentResponse();
				BeanUtils.copyProperties(user, response);
				responses.add(response);
			}

			return responses;
		}

		throw new UnauthorizedActionException("Access denied: User is not authorized to view students.");
	}

	public String updateStatus(Integer uid, Integer sid, Status status) {
		User admin = ur.findById(uid).orElseThrow(() -> new UserNotFound("Admin Not Found"));

		User stu = ur.findById(sid).orElseThrow(() -> new UserNotFound("Student Not Found"));

		if (admin.getRole().equals(Role.ADMIN) && stu.getRole().equals(Role.STUDENT)) {
			
			stu.setStatus(status);
			ur.save(stu);
			return "Status Updated";
		} else {
			return "Student not Change Status";
		}

	}

	public UserResponse getDetails(Integer uid) {
		User user = ur.findById(uid).orElseThrow(() -> new UserNotFound("User Not Found"));
		if (!Status.ACTIVE.equals(user.getStatus())) {
			throw new UnauthorizedActionException("Your Id is Inactive Please Contact Support");
		}
		UserResponse response = new UserResponse();

		BeanUtils.copyProperties(user, response);
		return response;
	}

}
