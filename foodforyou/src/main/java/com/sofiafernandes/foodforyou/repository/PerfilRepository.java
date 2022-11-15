package com.sofiafernandes.foodforyou.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sofiafernandes.foodforyou.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{

	public List<Perfil> findAllById(Long id);
}
