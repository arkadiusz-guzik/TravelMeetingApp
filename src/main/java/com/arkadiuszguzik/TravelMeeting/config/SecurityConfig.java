package com.arkadiuszguzik.TravelMeeting.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.arkadiuszguzik.TravelMeeting.service.UserService;


@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.authenticationProvider(authenticationProvider());	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/login").not().authenticated()
		.antMatchers("/repertoire/buy").hasAnyRole("ADMIN", "USER")
		.antMatchers("/repertoire/manage").hasRole("ADMIN")
		.antMatchers("/repertoire/manage/*").hasRole("ADMIN")
		.antMatchers("/repertoire/manage/*/*").hasRole("ADMIN")
		.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/authenticateTheUser")
			.successHandler(customAuthenticationSuccessHandler)
		.permitAll()
		.and()
		.logout().logoutSuccessUrl("/home").permitAll();
		
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
	return auth;
	}
	
	
}
