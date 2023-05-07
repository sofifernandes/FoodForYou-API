package com.sofiafernandes.foodforyou.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class BasicSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/usuario/logar").permitAll()
		.antMatchers("/usuario/cadastrar").permitAll()
		.antMatchers(HttpMethod.GET,"/usuario").permitAll()
		.antMatchers(HttpMethod.GET, "/usuario/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/usuario/nome/*").permitAll()        
        .antMatchers(HttpMethod.POST, "/usuario").permitAll()
        .antMatchers(HttpMethod.PUT, "/usuario").permitAll()
        .antMatchers(HttpMethod.DELETE, "/usuario/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/tema").permitAll()
		.antMatchers(HttpMethod.GET, "/tema/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/tema/nome/*").permitAll()        
        .antMatchers(HttpMethod.POST, "/tema").permitAll()
        .antMatchers(HttpMethod.PUT, "/tema").permitAll()
        .antMatchers(HttpMethod.DELETE, "/tema/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/postagem").permitAll()
		.antMatchers(HttpMethod.GET, "/postagem/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/postagem/titulo/*").permitAll()        
        .antMatchers(HttpMethod.POST, "/postagem").permitAll()
        .antMatchers(HttpMethod.PUT, "/postagem").permitAll()
        .antMatchers(HttpMethod.DELETE, "/postagem/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/perfil").permitAll()
		.antMatchers(HttpMethod.GET, "/perfil/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/perfil/nome/*").permitAll()
        .antMatchers(HttpMethod.POST, "/perfil").permitAll()
        .antMatchers(HttpMethod.PUT, "/perfil").permitAll()
        .antMatchers(HttpMethod.DELETE, "/perfil/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/interesse").permitAll()
		.antMatchers(HttpMethod.GET, "/interesse/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/interesse/nome/*").permitAll()
        .antMatchers(HttpMethod.POST, "/interesse").permitAll()
        .antMatchers(HttpMethod.PUT, "/interesse").permitAll()
        .antMatchers(HttpMethod.DELETE, "/interesse/id/*").permitAll()
        .antMatchers(HttpMethod.GET,"/comentario").permitAll()
		.antMatchers(HttpMethod.GET, "/comentario/id/*").permitAll()
        .antMatchers(HttpMethod.POST, "/comentario").permitAll()
        .antMatchers(HttpMethod.PUT, "/comentario").permitAll()
        .antMatchers(HttpMethod.DELETE, "/comentario/id/*").permitAll()
        .anyRequest().authenticated()
        .and().httpBasic()
        .and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
		
		http.headers().frameOptions().disable();
		
		return http.build();
	}
	
	@Bean
	protected WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/swagger-ui.hmtl", "/v3/api-docs/**");
	}
}