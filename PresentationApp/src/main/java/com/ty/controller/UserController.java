package com.ty.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.dto.PresentationResponse;
import com.ty.dto.StudentResponse;
import com.ty.dto.UserRequest;
import com.ty.dto.UserResponse;
import com.ty.enums.Role;
import com.ty.enums.Status;
import com.ty.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService us;

	public UserController(UserService us) {
		this.us = us;
	}

	/*
	 * register user as student
	 * 
	 * @param : UserRequest (Object)
	 */
	@PostMapping("/registerstu")
	public ResponseEntity<String> registerstu(@RequestBody UserRequest request) {

		String response = us.registerStudent(request);

		if (response.contains("Already")) {

			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);

		} else {

			return new ResponseEntity<String>(response, HttpStatus.CREATED);

		}

	}

	/*
	 * register user as admin
	 * 
	 * @param : UserRequest (Object)
	 */

	@PostMapping("/registeradmin")
	public ResponseEntity<String> registeradmin(@RequestBody UserRequest request) {
		String response = us.registerAdmin(request);
		if (response.contains("Already")) {

			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);

		} else {

			return new ResponseEntity<String>(response, HttpStatus.CREATED);

		}
	}

	/*
	 * login user as student
	 * 
	 * @param : username (String)
	 * 
	 * @param : password (String)
	 */

	@PostMapping("/loginstu")
	public ResponseEntity<String> loginstu(@RequestParam String email, @RequestParam String password) {

		String response = us.loginStudent(email, password);

		if (response.contains("Successfull")) {

			return new ResponseEntity<String>(response, HttpStatus.OK);

		} else {

			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);

		}

	}

	/*
	 * login user as admin
	 * 
	 * @param : username (String)
	 * 
	 * @param : password (String)
	 */

	@PostMapping("/loginadmin")
	public ResponseEntity<String> loginadmin(@RequestParam String email, @RequestParam String password) {

		String response = us.loginAdmin(email, password);
		System.out.println(response);

		if (response.contains("Successfull")) {

			return new ResponseEntity<String>(response, HttpStatus.OK);

		} else {

			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);

		}
	}

	/*
	 * get list of students based on role (only admin can get student user data)
	 * 
	 * @param : uid (Integer)
	 * 
	 * @param : role (Role)
	 */

	@GetMapping("/getallusr") // admin get all users of student
	public ResponseEntity<List<StudentResponse>> getAllusers(@RequestParam Integer uid, @RequestParam Role role) {

		List<StudentResponse> allStudent = us.getAllStudent(uid, role);

		return new ResponseEntity<List<StudentResponse>>(allStudent, HttpStatus.OK);
	}

	/*
	 * get User details by uid
	 * 
	 * @param : uid (Integer)
	 */

	@GetMapping("/get")
	public ResponseEntity<UserResponse> getDetails(@RequestParam Integer uid) {

		UserResponse response = us.getDetails(uid);

		return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
	}

	/*
	 * admin can update status of user(Student)
	 * 
	 * @param : uid (Integer)
	 * 
	 * @param : sid (Integer)
	 * 
	 * @param : status (Status)
	 */

	@PutMapping("/updatestatus/{uid}")
	public ResponseEntity<String> updateStatus(@PathVariable Integer uid, @RequestParam Integer sid,
			@RequestParam Status status) {
		String updateStatus = us.updateStatus(uid, sid, status);

		if (updateStatus.contains("Updated")) {
			return new ResponseEntity<String>(updateStatus, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(updateStatus, HttpStatus.BAD_REQUEST);
		}

	}

}
