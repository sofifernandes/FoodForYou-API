package com.sofiafernandes.foodforyou.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofiafernandes.foodforyou.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByNome(String nome);
	public Optional<Usuario> findByUsuario(String usuario);
}
