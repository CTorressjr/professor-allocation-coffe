package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {
	
	private final AllocationRepository repository;
	private final ProfessorService professorService;
	private final CourseService courseService;
	
	public AllocationService (AllocationRepository repository, ProfessorService professorService, CourseService courseService) {
		this.repository = repository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public List<Allocation> findAll() {
		return repository.findAll();
	}

	public Allocation findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Allocation save(Allocation allocation) {
			allocation.setId(null);
			
			return saveInternal(allocation);
			
	}

	public Allocation update(Allocation allocation) {
		if (repository.existsById(allocation.getId())) {
			
			return saveInternal(allocation);
			
		}else {
			return null;
		}
	}
	
	private Allocation saveInternal(Allocation allocation) {
		allocation = repository.save(allocation);
		
		Professor professor = professorService.findById(allocation.getProfessor().getId());
		Course course = courseService.findById(allocation.getCourse().getId());
		
		allocation.setProfessor(professor);
		allocation.setCourse(course);
		
		return allocation;
	}

	public void deleteById(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		}
		
	}

	public void deleteAll() {
		repository.deleteAllInBatch();
		
	}



		
}
