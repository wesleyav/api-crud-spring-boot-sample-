package com.github.wesleyav.apicrudspringbootsample.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.github.wesleyav.apicrudspringbootsample.entities.City;
import com.github.wesleyav.apicrudspringbootsample.repositories.CityRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "City")
public class CityController {

	@Autowired
	CityRepository cityRepository;

	@GetMapping(value = "/cities/", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint to list cities")
	public List<City> findAll() {
		List<City> cities = cityRepository.findAll();
		return cities;
	}

	@GetMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint to list city by Id")
	public ResponseEntity<City> findById(@PathVariable Long id) {
		Optional<City> city = cityRepository.findById(id);
		if (city.isPresent()) {
			return ResponseEntity.ok(city.get());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Endpoint to delete city by Id")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		Optional<City> optional = cityRepository.findById(id);
		if (optional.isPresent()) {
			cityRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@Operation(summary = "Endpoint to create city")
	@PostMapping(value = "/cities/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> create(@RequestBody City city, UriComponentsBuilder uriBuilder) {
		cityRepository.save(city);

		URI uri = uriBuilder.path("/api/v1/cities/{id}").buildAndExpand(city.getId()).toUri();
		return ResponseEntity.created(uri).body(new City());
	}

}
