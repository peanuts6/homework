package sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import sso.security.MyUserDetailsService;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String encodeStr = "341231412423423";
	
	
	
	@Configuration
	@Order(3)
	public static class AppSecurityConfig extends WebSecurityConfigurerAdapter{

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("**/*.html","**/js/**","**/vendor/**","**/lib/**","**/css/**","**/styles/**","**/images/**","**/fonts/**", "**/**/favicon.ico");
		}
	  	
	  	@Override
	  	protected void configure(HttpSecurity http) throws Exception{
	  		http.csrf().disable()
	  			.antMatcher("/api/**")
	  			.authorizeRequests()
				.antMatchers("/api/**").denyAll()
				.anyRequest().authenticated()
	  			.and()
				.httpBasic();
	  		
	  		http.antMatcher("/kingcenter/api/**")
	  			.authorizeRequests()
	  			.antMatchers("/kingcenter/api/**").denyAll()
	  			.antMatchers("/kingcenter/api/{username}").access("@authz.check(#username,pricipal)")
	  			.anyRequest().authenticated()
	  			.and().httpBasic();
				
		}
	}
	
	@Configuration
	@Order(1)
	public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
		@Bean
	    protected BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
		
		@Autowired
		MyUserDetailsService userService;

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//			auth.authenticationProvider(myAuthenticationProvider);
			auth.userDetailsService(new MyUserDetailsService());
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/admin/api/**")
	  			.authorizeRequests()
				.antMatchers("/admin/api/**").denyAll()
	  			.anyRequest().hasRole("ADMIN")
	  			.anyRequest().authenticated()
	  			.and()
				.httpBasic();
		}
//		@Override
//		protected void configure(final AuthenticationManagerBuilder auth) throws Exception{
//			auth.inMemoryAuthentication()
//					.withUser("xqy").password("123").roles("USER","ADMIN","manage","op_createuser")
//					.and()
//					.withUser("admin").password("9527").roles("ADMIN")
//					.and()
//					.withUser("manage").password("123").roles("USER","MANAGE")
//					.and()
//					.withUser("yoyo").password("123").roles("USER","MANAGE","OP_CREATEUSER");
//		}
	}
	
	@Configuration
	@Order(2)
	public static class ManageSecurityConfig extends WebSecurityConfigurerAdapter{
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/manage/api/**")
	  			.authorizeRequests()
				.antMatchers("/manage/api/**").hasRole("manage")
				.antMatchers("/manage/api/createuser/**")
				.access("hasRole('manage') and hasRole('op_createuser')")
	  			.antMatchers("/manage/api/**").denyAll()
	  			.anyRequest().authenticated()
	  			.and()
	  			.httpBasic();
	  		}
	}
	
		
		

//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(inMemoryUserDetailsManager());
//	}
//
//	@SuppressWarnings("rawtypes")
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUserDetailsManager() throws Exception {
//		Properties userPropers = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/users.dat"));
//		Properties newUsers = new Properties();
//		for (Entry entry : userPropers.entrySet()) {
//			String user = (String) entry.getKey();
//			String value = (String) entry.getValue();
//			String[] valueItems = value.split(",");
//			String password = valueItems[0];
//			password = PBEUtil.decrypt(password, encodeStr);
//			String newValue = password + "," + valueItems[1] + "," + valueItems[2];
//			// users.put("user","pass,ROLE_USER,enabled");
//			newUsers.setProperty(user, newValue);
//		}
//		return new InMemoryUserDetailsManager(newUsers);
//	}

}