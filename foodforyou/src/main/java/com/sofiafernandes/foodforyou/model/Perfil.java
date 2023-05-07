package com.sofiafernandes.foodforyou.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_perfil_usuario")
public class Perfil {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 3, max = 45)
	private String primeiro_nome;
	
	@Size(min = 3, max = 45)
	private String ultimo_nome;
	
	@Size(min = 3, max = 255)
	private String genero;
	
	@Size(min = 3, max = 255)
	private String ocupacao;
	
	@Size(min = 3, max = 255)
	private String sobre_usuario;
	
	@OneToOne
	@JsonIgnoreProperties("perfil")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrimeiro_nome() {
		return primeiro_nome;
	}

	public void setPrimeiro_nome(String primeiro_nome) {
		this.primeiro_nome = primeiro_nome;
	}

	public String getUltimo_nome() {
		return ultimo_nome;
	}

	public void setUltimo_nome(String ultimo_nome) {
		this.ultimo_nome = ultimo_nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public String getSobre_usuario() {
		return sobre_usuario;
	}

	public void setSobre_usuario(String sobre_usuario) {
		this.sobre_usuario = sobre_usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
