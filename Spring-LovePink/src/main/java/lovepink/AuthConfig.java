package lovepink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecation")
public class AuthConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private UserDetailsService userDetail;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetail);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable(); // _csrf -> code... so we need to disabled to make easier login

		 http // allow request page authenticated
		 .authorizeRequests()
		 	.antMatchers("/order/**").authenticated()
		 	.antMatchers("/page/director/**").hasAnyRole("DIRE")
		 	.antMatchers("/page/staff/**", "/manager/**").hasAnyRole("STAF")
         	.antMatchers("/security/**", "/assets/**", "/product/**", "/lovepink").permitAll()
            .anyRequest().authenticated(); // have login
		 http.exceptionHandling().accessDeniedPage("/login/error/role");
		 
		 // LOGIN LOGOUT http://localhost:8080/pages/login
		 http // form login
	 	.formLogin()
	 		.loginPage("/security/login/form")
			.loginProcessingUrl("/security/login") // default [/login] => process the submitted credential
			.defaultSuccessUrl("/login/success", true)
			.failureForwardUrl("/login/failed"); // login failed

         http// logout
		.logout()
			.logoutUrl("/security/logoff") // default [/logout]
			.logoutSuccessUrl("/logout/success");
     
         http.oauth2Login() //Login with google account
	         .loginPage("/security/login/form")
	         .defaultSuccessUrl("/login/success", true)
	         .failureUrl("/login/failed")
	         .authorizationEndpoint().baseUri("/security/login/auth2/");
         
         http.httpBasic();
	}
	
}
