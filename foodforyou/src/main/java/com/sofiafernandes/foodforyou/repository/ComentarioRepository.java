package com.sofiafernandes.foodforyou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofiafernandes.foodforyou.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	public List<Comentario> findAllById(Long id);
	
	public List<Comentario> findByPostagemId(long postId);
}
