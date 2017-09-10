package leader05.dao;


import leader05.domain.TCourse;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class CourseRepositoryUT implements CourseRepository {


    DataSource dataSource;

    Connection connection;

    JdbcTemplate jdbcTemplate;


    public void setup(){
        dataSource = Mockito.mock(DataSource.class);
        connection = Mockito.mock(Connection.class);
        jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        System.out.println("init");
    }
    public List<TCourse> getAllCourse() {
        List<TCourse> list = new ArrayList<>();
        list.add(new TCourse(1,"test1",90));
        list.add(new TCourse(2,"test2",80));
        list.add(new TCourse(3,"test2",100));
        Mockito.when(
                jdbcTemplate.query(
                        "SELECT id, name, mark FROM course",
                        (rs, rowNum) -> new TCourse(rs.getInt("id"), rs.getString("name"), rs.getInt("mark"))
                )
            ).thenReturn(list);
        return list;
    }

    public void clean(){
        jdbcTemplate.execute("delete from course");
    }
}
