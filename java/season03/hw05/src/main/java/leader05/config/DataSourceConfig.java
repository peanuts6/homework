package leader05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import leader05.dao.CourseRepository;
import leader05.dao.CourseRepositoryPrd;
import leader05.dao.CourseRepositoryUT;


/**
 * Created by leader05 on 17/9/8.
 */

@Configuration
public class DataSourceConfig {
    @Bean(name="dataSource")
    @Profile("test")
    public CourseRepository getTestRepository(){
        return new CourseRepositoryUT();
    }

    @Bean(name="dataSource")
    @Profile("production")
    public CourseRepository getPrdRepository(){
        return new CourseRepositoryPrd();
    }
}
