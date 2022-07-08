package com.employeemanagement.secuirty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
			auth.inMemoryAuthentication().
			withUser("temp").
			password("12345").
			roles("USER").
			and().
			withUser("admin").
			password("admin").
			roles("ADMIN");
	}
	
	@Bean
	public PasswordEncoder getPassWordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
		.antMatchers("/api/addemployees").hasRole("ADMIN")
		.antMatchers("/").permitAll()
		.antMatchers("/h2-console/**").permitAll()
		.and().httpBasic();
			
		http.csrf().ignoringAntMatchers("/h2-console/**");
		
		http.csrf().disable();
        //this will allow frames with same origin which is much more safe
        http.headers().frameOptions().sameOrigin();
		}
	
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
	
}
