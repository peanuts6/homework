package leader05.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import leader05.service.MyCourseService;
import leader05.dao.CourseRepository;

/**
 * Created by leader05 on 17/9/8.
 */
@Configuration
public class AppConfig {

    @Autowired
    CourseRepository dataSource;

    @Bean
    public MyCourseService myCourseService(){
        return new MyCourseService(dataSource);
    }
}
