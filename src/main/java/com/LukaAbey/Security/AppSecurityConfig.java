package com.LukaAbey.Security;
import static com.LukaAbey.Security.AppUserRole.ADMIN;
import static com.LukaAbey.Security.AppUserRole.ADMINTRAINEE;
import static com.LukaAbey.Security.AppUserRole.STUDENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;

	@Autowired
	public AppSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "index").permitAll().antMatchers("/api/**").hasRole(STUDENT.name())
				.anyRequest().authenticated().and().httpBasic();

	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails Johnny = User.builder().username("JohnnySmith").password(passwordEncoder.encode("password"))
				.roles(ADMIN.name()).build();
		
		UserDetails Jemma = User.builder().username("jemma123").password(passwordEncoder.encode("password123"))
				.roles(STUDENT.name()).build();

		UserDetails Tom = User.builder().username("tommy").password(passwordEncoder.encode("password123"))
				.roles(ADMINTRAINEE.name()).build();

		return new InMemoryUserDetailsManager(Johnny, Jemma, Tom);
	}
}
