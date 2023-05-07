package com.sofiafernandes.foodforyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofiafernandes.foodforyou.model.Interesse;
import com.sofiafernandes.foodforyou.repository.InteresseRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/interesse")
public class InteresseController {	
	
	@Autowired
	private InteresseRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Interesse>> getAllInteresse()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Interesse> findByIdInteresse(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Interesse>> getByNome(@PathVariable String nome)
	{
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Interesse> post(@RequestBody Interesse interesse)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(interesse));
	}
	
	@PutMapping
	public ResponseEntity<Interesse> put(@RequestBody Interesse interesse)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(interesse));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id)
	{
		repository.deleteById(id);
	}
}
