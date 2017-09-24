package leader05;

import leader05.config.AppConfig;
import leader05.config.DataSourceConfig;
import leader05.domain.TCourse;
import leader05.service.MyCourseService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by leader05 on 17/9/8.
 */
public class App {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.getEnvironment().setActiveProfiles("production");
        ctx.getEnvironment().setActiveProfiles("test");
        ctx.register(DataSourceConfig.class);
        ctx.register(AppConfig.class);
        ctx.refresh();

        MyCourseService myCourseService = (MyCourseService) ctx.getBean("myCourseService");
        myCourseService.setup();
        List<TCourse> courseList = myCourseService.getAllCourses();
        for(TCourse course:courseList){
            System.out.println(course.toString());
        }
        myCourseService.clean();
    }
}
