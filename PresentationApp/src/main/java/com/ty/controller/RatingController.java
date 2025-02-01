package com.ty.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.dto.RatingRequest;
import com.ty.dto.RatingResponse;
import com.ty.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	RatingService rs;

	public RatingController(RatingService rs) {
		this.rs = rs;
	}

	@PostMapping("/add/{aid}")
	public ResponseEntity<String> addRating(@PathVariable Integer aid, @RequestParam Integer sid,
			@RequestParam Integer pid, @RequestBody RatingRequest rating) {
		String response = rs.getrating(aid, sid, pid, rating);
		if (response.contains("added")) {
			return new ResponseEntity<String>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/get")
	public ResponseEntity<RatingResponse> getRating(@RequestParam Integer rid) {
		RatingResponse response = rs.getrating(rid);
		return new ResponseEntity<RatingResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<RatingResponse>> getAllRating(@RequestParam Integer sid) {
		List<RatingResponse> list = rs.getallrating(sid);
		if (list.size() > 0) {
			return new ResponseEntity<List<RatingResponse>>(list, HttpStatus.OK);
		} else {
			System.out.println("empty`");
			return new ResponseEntity<List<RatingResponse>>(list, HttpStatus.BAD_REQUEST);
		}

	}
}
