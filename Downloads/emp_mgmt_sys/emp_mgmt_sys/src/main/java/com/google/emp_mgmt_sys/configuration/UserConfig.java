package com.google.emp_mgmt_sys.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class UserConfig {
	  @Bean
	  public UserDetailsService detailsService() {
		  UserDetails admin = User
				  .withUsername("admin")
				  .password("{noop}admin@123")
				  .roles("ADMIN")
				  .build();
		  UserDetails user=User
				  .withUsername("user")
				  .password("{noop}ser@123")
				  .roles("USER")
				  .build();
		  return new InMemoryUserDetailsManager(admin,user);
	  }
	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) {
		  http
		  .csrf(csrf->csrf.disable())
		  .authorizeHttpRequests(auth->auth
		  .requestMatchers("/emp/inserts","/emp/deleteid/**","/deleteall","/update")
		  .hasRole("ADMIN")
		  .requestMatchers("/emp/id/**","/emp/all")
		  .hasAnyRole("ADMIN","USER"));
		  
		 http.httpBasic(Customizer.withDefaults());
		 return http.build();
	  }

}
