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

import com.sofiafernandes.foodforyou.model.Comentario;
import com.sofiafernandes.foodforyou.repository.ComentarioRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/comentario")
public class ComentarioController {
	@Autowired
	private ComentarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Comentario>> getAllComentarios()
	{
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<List<Comentario>> getComentariosByPost(@PathVariable long postId) {
	    List<Comentario> comentarios = repository.findByPostagemId(postId);
	    return ResponseEntity.ok(comentarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Comentario> findByIdPostagem(@PathVariable long id)
	{
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Comentario> post(@RequestBody Comentario comentario)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(comentario));
	}	

	
	@PutMapping("/{id}")
	public ResponseEntity<Comentario> put(@PathVariable long id, @RequestBody Comentario comentario)
	{
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(comentario));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id)
	{
		repository.deleteById(id);
	}
}
