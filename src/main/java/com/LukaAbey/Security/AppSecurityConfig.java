package com.LukaAbey.Security;

import static com.LukaAbey.Security.AppUserRole.ADMIN;
import static com.LukaAbey.Security.AppUserRole.ADMINTRAINEE;
import static com.LukaAbey.Security.AppUserRole.STUDENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AppSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // TODO: I will teach this in detail in the next section
				.authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*").permitAll().antMatchers("/api/**")
				.hasRole(STUDENT.name())
				.antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(COURSE_WRITE.getPermission())
				.antMatchers(HttpMethod.POST, "/api/**").hasAuthority(COURSE_WRITE.getPermission())
				.antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(COURSE_WRITE.getPermission())
				.antMatchers("/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
				.anyRequest().authenticated().and().httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails annaSmithUser = User.builder().username("annasmith").password(passwordEncoder.encode("password"))
//                .roles(STUDENT.name()) // ROLE_STUDENT
				.authorities(STUDENT.getGrantedAuthorities()).build();

		UserDetails lindaUser = User.builder().username("linda").password(passwordEncoder.encode("password123"))
//                .roles(ADMIN.name()) // ROLE_ADMIN
				.authorities(ADMIN.getGrantedAuthorities()).build();

		UserDetails tomUser = User.builder().username("tom").password(passwordEncoder.encode("password123"))
//                .roles(ADMINTRAINEE.name()) // ROLE_ADMINTRAINEE
				.authorities(ADMINTRAINEE.getGrantedAuthorities()).build();

		return new InMemoryUserDetailsManager(annaSmithUser, lindaUser, tomUser);

	}
}