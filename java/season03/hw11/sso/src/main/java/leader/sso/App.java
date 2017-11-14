package leader.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(scanBasePackages="leader.sso.**")
@MapperScan("leader.sso.mapper")
public class App extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
	
    public static void main( String[] args )
    {
    	// ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
    	SpringApplication.run(App.class, args);
    }
}
