package com.example.availabilityapp;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AvailabilityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvailabilityAppApplication.class, args);
	}

	@RestController
	class AvailabilityController {

		private AvailabilityRepository repo;

		AvailabilityController(AvailabilityRepository repo) {
			this.repo = repo;
		}

		@GetMapping
		public Iterable<Availability> getAll() {
			return repo.findAll();
		}

		@GetMapping("/available/false")
		public Iterable<Availability> getNotAvailable() {
			return repo.findByAvailableFalse();
		}

		@GetMapping("/available/true")
		public Iterable<Availability> getAvailable() {
			return repo.findByAvailableTrue();
		}
	}

}

interface AvailabilityRepository extends CrudRepository<Availability, Integer>{

	public Iterable<Availability> findByAvailableTrue();

	public Iterable<Availability> findByAvailableFalse();
}

record Availability(@Id Integer id, String name, Boolean available){}