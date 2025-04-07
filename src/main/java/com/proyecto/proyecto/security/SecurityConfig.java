package com.proyecto.proyecto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/webjars/**", "/css/**", "/images/**" , "/register","/register/submit").permitAll() // Todos pueden acceder
                .antMatchers("/usersList", "/newGame", "/deleteUser", "/deleteGame", "/deleteProject", "/updateGame", "/reportedGames").hasRole("ADMIN") // Solo puedes acceder si eres administrador
				.anyRequest().authenticated() // Puedes acceder si has iniciado sesión
				.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/gameList", true)
				.failureUrl("/login?error=true")
				.loginProcessingUrl("/auth/login-post")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/auth/logout") 
				.logoutSuccessUrl("/login");
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
				
	}
}

