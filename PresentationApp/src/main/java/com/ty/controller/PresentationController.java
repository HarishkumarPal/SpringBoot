package com.ty.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.Exceptions.InvalidRoleException;
import com.ty.dto.PresentationRequest;
import com.ty.dto.PresentationResponse;
import com.ty.enums.PresentationStatus;
import com.ty.service.PresentationService;

@RestController
@RequestMapping("/presentation")
public class PresentationController {

	private PresentationService ps;

	public PresentationController(PresentationService ps) {

		this.ps = ps;
	}

	/*
	 * Admin can assign the presentation to Student based on sid
	 * 
	 * @param : uid (Integer)  & sid (Integer) & presentation request
	 
	 */

	@PostMapping("/addpstnt/{uid}")
	public ResponseEntity<String> addPresentation(@PathVariable Integer uid, @RequestParam Integer sid,
			@RequestBody PresentationRequest request) {
		String presentation = ps.addPresentation(uid, sid, request);
		if (presentation.contains("Assigned")) {
			return new ResponseEntity<String>(presentation, HttpStatus.CREATED);
		} else
			return new ResponseEntity<String>(presentation, HttpStatus.BAD_REQUEST);

	}

	/*
	 * It accept presentation Id and return presentation Object based on that id
	 * 
	 * @param : pid (Integer)
	 * 
	 */

	@GetMapping("/get")
	public ResponseEntity<PresentationResponse> getPresentation(@RequestParam Integer pid) {
		PresentationResponse presentation = ps.getPresentation(pid);
		return new ResponseEntity<PresentationResponse>(presentation, HttpStatus.OK);
	}

	/*
	 * accpects User Id user should be admin and return List of presentation Object 
	 * 
	 * @param : id (Integer)
	 * 
	 */
	
	@GetMapping("/getlist/{aid}")
	public ResponseEntity<List<PresentationResponse>> getPresentationList(@PathVariable Integer aid, @RequestParam Integer sid) throws InvalidRoleException {
		List<PresentationResponse> presentation = ps.getPresentationList(aid,sid);
		return new ResponseEntity<List<PresentationResponse>>(presentation, HttpStatus.OK);
	}
	
	@PutMapping("/updatestatus")
	public ResponseEntity<String> updatePresentationStatus(@RequestParam Integer sid,@RequestParam Integer pid,@RequestParam PresentationStatus pstatus) throws InvalidRoleException {
		String presentation = ps.updatePresentationStatus(sid,pid ,pstatus);
		return new ResponseEntity<String>(presentation, HttpStatus.OK);
	}
	
}
