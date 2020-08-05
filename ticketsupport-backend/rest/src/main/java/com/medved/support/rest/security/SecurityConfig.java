package com.medved.support.rest.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.medved.support.rest.jwt.JwtRequestFilter;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private MyCustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
//		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}
//	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors().and().csrf().disable().authorizeRequests()
		.antMatchers("/medved/**").permitAll()
		.and().authorizeRequests()
		.antMatchers("/medvedapi/users/authenticate").permitAll()
		.and().authorizeRequests().antMatchers("/medvedapi/enterprises/**").hasRole("Admin")
		
		.and().authorizeRequests().antMatchers("/medvedapi/externalTickets/{id}").permitAll()
		.and().authorizeRequests().antMatchers("/medvedapi/externalTickets/meta/{keyword}").permitAll()
		.and().authorizeRequests().antMatchers("/medvedapi/externalTickets/{perPage}/{currentPage}").permitAll()
		.and().authorizeRequests().antMatchers("/medvedapi/externalTickets/meta/{keyword}/{perPage}/{currentPage}").permitAll()
		.and().authorizeRequests().antMatchers("/medvedapi/externalTickets/**").hasRole("Admin")
		
		.and().authorizeRequests().antMatchers("/medvedapi/keywordss/**").hasRole("Admin")
		.and().authorizeRequests().antMatchers("/medvedapi/rol/**").hasRole("Admin")
		.and().authorizeRequests().antMatchers("/medvedapi/sources/**").hasRole("Admin")
		.and().authorizeRequests().antMatchers("/medvedapi/statistics/**").hasRole("Admin")
		.and().authorizeRequests().antMatchers("/medvedapi/reports/**").hasRole("Admin")
		.and().authorizeRequests().antMatchers("/medvedapi/synchronizationRegister**").hasRole("Admin")
		.and().authorizeRequests().antMatchers("/medvedapi/users/agents").permitAll()
		.and().authorizeRequests().antMatchers("/medvedapi/users/**").hasRole("Admin")
		
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/medved/login").permitAll()
		.and().sessionManagement().
		sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public FilterRegistrationBean<?> simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// * URL below needs to match the Vue client URL and port *s
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}