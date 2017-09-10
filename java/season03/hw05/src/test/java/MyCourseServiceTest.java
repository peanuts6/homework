import leader05.config.AppConfig;
import leader05.config.DataSourceConfig;
import leader05.dao.CourseRepository;
import leader05.domain.TCourse;
import leader05.service.MyCourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DataSourceConfig.class})
@ActiveProfiles("test")
public class MyCourseServiceTest {

    @Autowired
    MyCourseService myCourseService;

    @Before
    public void createConn(){
        System.out.println("before class: prepare data");
        myCourseService.setup();
    }

    @Test
    public void getAllCourses(){
        System.out.println("run test...");
        List<TCourse> courses = myCourseService.getAllCourses();
        for(TCourse course:courses){
            System.out.println(course.toString());
        }
    }

    @After
    public void removeData(){
        System.out.println("after class: remove data");
        myCourseService.clean();
    }


}
