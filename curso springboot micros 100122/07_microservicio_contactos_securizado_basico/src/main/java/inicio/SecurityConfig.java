package inicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public InMemoryUserDetailsManager detailsManager() throws Exception{
		List<UserDetails> users=Arrays.asList(  //List.of
				User.withUsername("user1")
				.password("{noop}user1")
				.authorities("USERS")
				.build(),
				User.withUsername("admin")
				.password("{noop}admin")
				.authorities("USERS","ADMIN") //.roles("USERS","ADMIN")
				.build()
				 

				);
		return new InMemoryUserDetailsManager(users);
	}
	/*
	public DataSource dataSource() {
	DriverManagerDataSource ds=new DriverManagerDataSource();
	ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
	ds.setUrl("jdbc:mysql://localhost:3307/springsecurity?serverTimezone=UTC");
	ds.setUsername("root");
	ds.setPassword("root");
	return ds;
	}*/
	
	
	/*la siguiente configuracion será para el caso de usuarios en una base de datos
	@Bean
	public JdbcUserDetailsManager usersDetailsJdbc() {
		JdbcUserDetailsManager jdbcDetailsManager=new JdbcUserDetailsManager(datasource());
		jdbcDetailsManager.setUsersByUsernameQuery("select username, password, enabled"
	       	+ " from users where username=?");
		jdbcDetailsManager.setAuthoritiesByUsernameQuery("select username, authority "
	       	+ "from authorities where username=?");
		return jdbcDetailsManager;
	}*/
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		
		Customizer<CsrfConfigurer<HttpSecurity>> myCustomizer = custOfCsrfC -> {
		try {
			custOfCsrfC.disable();
		} catch (Exception e) {
	        throw new RuntimeException(e);
		}
		};
					
	  //http.csrf(myCustomizer)// Aquí aplicamos nuestro Customizer
		
		http.csrf(custOfCsrfC->custOfCsrfC.disable()) //programacion funcional	
		//http.csrf().disable()	//programacion imperativa tradicional verbosa	
		//.authorizeRequests()
		//.antMatchers(HttpMethod.POST, "/contactos").hasAnyAuthority("ADMIN")  //.hasAnyRole("ADMIN")
		//.antMatchers(HttpMethod.DELETE, "/contactos/**").hasAnyAuthority("ADMIN")
		//http.csrf(customiz->customiz.disable())//desactivado cuando micro solo llamado desde otro micro, no desde página
		.authorizeHttpRequests(custOfAuth->custOfAuth
		.antMatchers(HttpMethod.POST, "/contactos").hasAnyAuthority("ADMIN")  //.hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/contactos/**").hasAnyAuthority("ADMIN")
		.antMatchers("/contactos").authenticated()
		.anyRequest().permitAll())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	/*
		http.csrf().disable() //desactivado cuando micro solo llamado desde otro micro, no desde página
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/contactos").hasAnyAuthority("ADMIN") //.hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.DELETE, "/contactos/**").hasAnyRole("ADMIN")
		.antMatchers("/contactos").authenticated() //contactos/** securiza ademas si parámetros
		.anyRequest().permitAll() //cualquier petición
		.and()
		.httpBasic();
		return http.build();
	*/
	

		

		
}
