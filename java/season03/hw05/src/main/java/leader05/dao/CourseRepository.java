package leader05.dao;

import leader05.domain.TCourse;

import java.util.List;


public interface CourseRepository {
    void setup();
    List<TCourse> getAllCourse();
    void clean();
}
