package com.hades.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hades.configuration.security.TokenAuthenticationService;
import com.hades.filter.StatelessAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenAuthenticationService tokenService;
	
	WebSecurityConfiguration() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.anonymous()
			.and().authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and().addFilterBefore(new StatelessAuthenticationFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
	}

}