package edu.cpci.shetech.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import edu.cpci.shetech.service.UserDetailsServiceImpl;

//import com.gti.docbox.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USUARIO = "ROLE_USUARIO";

	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder;
    }
    @Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
        	.csrf().disable()
            .authorizeRequests()
            	.antMatchers( "/", "/homePage", "/register", "/posteo").permitAll()
                .antMatchers("/addPosteo").hasAnyAuthority(ROLE_ADMIN, ROLE_USUARIO)
                .antMatchers("/deletPosteo", "/posteoAdmin").hasAnyAuthority(ROLE_ADMIN)
                //.antMatchers("/newEmpresa", "/posteo/addPosteo").authenticated()
            .and()
	            .formLogin()
		            .loginProcessingUrl("/j_spring_security_check")
		            .loginPage("/login").permitAll()
		            .defaultSuccessUrl("/homePage")
		            .failureUrl("/login?error=true")
            .and()
	            .logout()
		            .logoutUrl("/j_spring_security_logout")
		            //.logoutUrl("/logout")
		            .logoutSuccessUrl("/homePage");
        
        
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        
        http.sessionManagement()
    	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
    	.invalidSessionUrl("/login");
        
     // Config Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
	}
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

}
