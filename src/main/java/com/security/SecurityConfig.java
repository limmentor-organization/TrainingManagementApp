package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsServiceImpl userDetailsServiceImpl;

	public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**").antMatchers("/css/**").antMatchers("/h2-console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.authorizeRequests()
		.antMatchers("/login")
		.permitAll()
		.antMatchers("/signup")
		.permitAll()
		.antMatchers("/users")
		.hasAuthority("ROLE_ADMIN")
		.anyRequest()
		.authenticated();

		http
		.formLogin()
		.loginProcessingUrl("/login")
		.loginPage("/login")
		.failureUrl("/login?error")
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/home", true);

		http
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login?logout");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
		
	}

}
