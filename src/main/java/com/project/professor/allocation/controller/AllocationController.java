package com.project.professor.allocation.controller;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;

@RestController
@RequestMapping (path = "/allocations")
public class AllocationController {
	
	@GetMapping (produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll(){
		List<Allocation> allocations = new ArrayList<>();
		
		Allocation alloc = new Allocation();
		alloc.setDayOfWeek(DayOfWeek.SATURDAY);
		alloc.setStartHour((Time.valueOf(LocalTime.of(8, 00))));
		alloc.setEndHour((Time.valueOf(LocalTime.of(17, 00))));
		
		allocations.add(alloc);
		
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}
}

