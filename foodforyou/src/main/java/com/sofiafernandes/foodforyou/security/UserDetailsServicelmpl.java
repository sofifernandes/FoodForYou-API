package com.sofiafernandes.foodforyou.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sofiafernandes.foodforyou.model.Usuario;
import com.sofiafernandes.foodforyou.repository.UsuarioRepository;

@Service("userDetailsService")
public class UserDetailsServicelmpl {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UserDetailsImpl loadUserByUsername(String nome) throws UsernameNotFoundException {
		Optional<Usuario> user = usuarioRepository.findByNome(nome);
		user.orElseThrow(() -> new UsernameNotFoundException(nome + " not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}	

}
