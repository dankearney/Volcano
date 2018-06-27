package com.example.postgresdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.example.postgresdemo.service.*;
import org.springframework.context.annotation.*;
import com.example.postgresdemo.security.SimpleCORSFilter;
import org.springframework.core.annotation.*;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.boot.autoconfigure.security.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.*;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class VolcanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolcanoApplication.class, args);
	}

	  @Configuration
	  public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	    @Override
		protected void configure(HttpSecurity http) throws Exception {
	        http.
	            authorizeRequests().
	            antMatchers(HttpMethod.GET, "/test").permitAll().
	            antMatchers(HttpMethod.POST, "/register").permitAll().
		        anyRequest().authenticated().and().httpBasic().and().csrf().disable();
		    http.cors();
		}

	    /* To allow Pre-flight [OPTIONS] request from browser */
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	    }
 

	    @Autowired
	    public VolcanoUserDetailsService userDetailsService;

	    @Override
	    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }

	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(encoder());
	        return authProvider;
	    }
    
	    @Bean
	    public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder(11);
	    }

	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("HEAD",
	                "GET", "POST", "PUT", "DELETE", "PATCH"));
	        // setAllowCredentials(true) is important, otherwise:
	        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
	        configuration.setAllowCredentials(true);
	        // setAllowedHeaders is important! Without it, OPTIONS preflight request
	        // will fail with 403 Invalid CORS request
	        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }

  }
}
