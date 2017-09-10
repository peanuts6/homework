package leader05.service;

import leader05.domain.TCourse;
import leader05.dao.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by leader05 on 17/9/8.
 */
//@Component
public class MyCourseService {

    CourseRepository repository;

    public MyCourseService(CourseRepository repository){
        this.repository = repository;
        //this.setup();
    }

    public List<TCourse> getAllCourses(){
        return repository.getAllCourse();
    }

    public void clean(){
        repository.clean();
    }

    public void setup(){
        repository.setup();
    }
}
