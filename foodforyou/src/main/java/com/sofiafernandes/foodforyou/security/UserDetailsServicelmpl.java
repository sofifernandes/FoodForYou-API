package com.sofiafernandes.foodforyou.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sofiafernandes.foodforyou.model.Usuario;
import com.sofiafernandes.foodforyou.repository.UsuarioRepository;

@Service
public class UserDetailsServicelmpl {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UserDetailsImpl loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> user = usuarioRepository.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}

}
