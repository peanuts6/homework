package leader.sso.config;

import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import leader.sso.security.MyUserDetailsService;
import temp.MyAuthenticationProvider;
import temp.MyAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableRedisHttpSession
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String encodeStr = "341231412423423";
	
	@Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
//	@Autowired
//	MyAuthenticationProvider myAuthenticationProvider;
//	
//	@Autowired
//	MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
//	
//	@Autowired
//	SimpleUrlAuthenticationFailureHandler authFailureHandler;
	
	@Autowired
	MyUserDetailsService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.authenticationProvider(myAuthenticationProvider);
		auth.userDetailsService(new MyUserDetailsService());
	}
	
	
		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("**/*.html","**/js/**","**/vendor/**","**/lib/**","**/css/**","**/styles/**","**/images/**","**/fonts/**", "**/**/favicon.ico");
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.antMatchers("/auth/**").permitAll()
				.and()
				.authorizeRequests()
				.antMatchers("/api/**","/kingcenter/api/**").denyAll()
				.anyRequest().hasRole("USER")
				.antMatchers("/kingcenter/api/{username}").access("@authz.check(#username,pricipal)")
				.and()
				.authorizeRequests()
				.antMatchers("/admin/api/**").denyAll()
				.anyRequest().hasRole("ADMIN")
				.and()
				.authorizeRequests()
				.antMatchers("/manage/api/**").denyAll()
				.anyRequest().hasRole("manage")
				.antMatchers("/manage/api/createuser/**").hasAuthority("op_createuser")
				.anyRequest().authenticated()
				.and()
				.httpBasic();
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