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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import edu.cpci.shetech.service.UsuarioDetailsServiceImpl;

//import com.gti.docbox.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_USUARIO = "USUARIO";

	
    @Autowired
    private UsuarioDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;
*/
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

    }
    




	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
        	.csrf().disable()
            .authorizeRequests()
            	.antMatchers( "/", "/homePage").permitAll()
                //.antMatchers("/newEmpresa").hasAnyRole(ROLE_USUARIO)
                .antMatchers("/newEmpresa", "/posteo/addPosteo").authenticated();
            //.and()
              //  .oauth2Login();
            /*.formLogin()
            	.loginPage("/login").permitAll()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/homepage")
                .failureUrl("/login?error=true")
                
                .and()
            .logout()
            	.logoutUrl("/logout")
            	.logoutSuccessUrl("/logoutSuccessful");
        http
    		.authorizeRequests().anyRequest().authenticated()
    	.and()
    		.oauth2Login();
*/


//        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
//        // If no login, it will redirect to /login page.
//        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('ROL_USUARIO', 'ROL_ADMIN')");
//
//        // For ADMIN only.
//        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROL_ADMIN')");

//         When the user has logged in as XX.
//         But access a page that requires role YY,
//         AccessDeniedException will be thrown.
       // http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config Remember Me.
        //http.authorizeRequests().and() //
          //      .rememberMe().tokenRepository(this.persistentTokenRepository()) //
            //    .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }
/*
    // Token stored in Memory (Of Web Server).
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
	*/

}
