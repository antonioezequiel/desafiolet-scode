package com.letscode.moviebattle.moviebattle.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.letscode.moviebattle.moviebattle.service.TokenService;
import com.letscode.moviebattle.moviebattle.service.UsuarioService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private TokenService tokenService;
	
	private static final String[] AUTH_WHITELIST = {
	        "/swagger-resources/**",
	        "/swagger-ui.html",
	        "/v2/api-docs",
	        "/webjars/**"
	};
		
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	//configuração de urls
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/auth").permitAll()
		.antMatchers("/swagger-ui/*").permitAll()
		.antMatchers("/carregar").permitAll()
		.antMatchers("/api/movieshow/ranking").permitAll()
	    .anyRequest().authenticated()
	    .and().csrf().disable()
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    .and().addFilterBefore(new AutenticationTokenFilter(tokenService, usuarioService), UsernamePasswordAuthenticationFilter.class);
	}
	//configuração de autenticação
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }
	//configurações de css, javascript
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2/**");
		web.ignoring().antMatchers(AUTH_WHITELIST);
		super.configure(web);
	}
	
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}
	
}