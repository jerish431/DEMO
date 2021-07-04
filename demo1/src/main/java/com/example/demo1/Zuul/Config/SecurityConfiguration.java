package com.example.demo1.Zuul.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	AuthenticationProvider authenticationProvider;
	@Autowired
	AuthenticationSuccessHandler successHandler;
	@Autowired
    AuthenticationFailureHandler failureHandler;
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/**").authenticated()
	    .and()
		.formLogin()
		.loginProcessingUrl("/*/login/login")
		.usernameParameter("usn")
		.passwordParameter("pw")
		.successHandler(successHandler).failureHandler(failureHandler);
		http.csrf().disable();	
	}
}