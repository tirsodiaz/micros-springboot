package inicio;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {	
	AuthenticationManager auth;
	
	@Bean
	public InMemoryUserDetailsManager detailsManager() throws Exception{
		List<UserDetails> users=Arrays.asList(  //List.of
				User.withUsername("user1")
				.password("{noop}user1")
				.authorities("USERS")
				.roles("USERS")
				.build(),
				User.withUsername("admin")
				.password("{noop}admin")
				.authorities("USERS","ADMIN")
				.roles("USERS","ADMIN")
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
	
	
	/*la siguiente configuración será para el caso de usuarios en una base de datos
	@Bean
	public JdbcUserDetailsManager usersDetailsJdbc() {
		JdbcUserDetailsManager jdbcDetailsManager=new JdbcUserDetailsManager(datasource());
		jdbcDetailsManager.setUsersByUsernameQuery("select username, password, enabled"
	       	+ " from users where username=?");
		jdbcDetailsManager.setAuthoritiesByUsernameQuery("select username, authority "
	       	+ "from authorities where username=?");
		return jdbcDetails;
	}*/
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST, "/contactos").hasAnyRole("ADMIN") //.hasAnyAuthority("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/contactos/**").hasAnyRole("ADMIN")
		.requestMatchers("/contactos").authenticated()
		.anyRequest().permitAll()
		.and()
		.addFilter(new JWTAuthorizationFilter(auth));
		return http.build();
	}
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception{
		auth=conf.getAuthenticationManager();
		return auth;
	}
}

