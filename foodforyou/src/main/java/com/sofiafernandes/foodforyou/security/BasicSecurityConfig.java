package com.sofiafernandes.foodforyou.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sofiafernandes.foodforyou.model.Usuario;
import com.sofiafernandes.foodforyou.repository.UsuarioRepository;


@EnableWebSecurity
public class BasicSecurityConfig implements UserDetailsService {

	@Autowired
	private UserDetailsService userDetailsService;	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable().authorizeRequests()
        		.antMatchers("/logar").permitAll()
        		.antMatchers("/cadastrar").permitAll()        		
                .anyRequest().authenticated()        
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		.and().cors()
        		.and().csrf().disable()
                .build();
    }
	
	@Override
	public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
	    Optional<Usuario> user = usuarioRepository.findByUsuario(usuario);
	    if (!user.isPresent()) {
	        throw new UsernameNotFoundException("User not found with username: " + usuario);
	    }
	    return new org.springframework.security.core.userdetails.User(user.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + usuario)).getUsuario(), user.get().getSenha(), new ArrayList<>());
	}

}