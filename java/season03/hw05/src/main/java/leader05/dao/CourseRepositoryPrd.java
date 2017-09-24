package leader05.dao;


import leader05.domain.TCourse;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class CourseRepositoryPrd implements CourseRepository {

    private static final Map<String, BasicDataSource> dataSources = new ConcurrentHashMap<>();

    BasicDataSource basicDataSource;

    Connection connection;

    JdbcTemplate jdbcTemplate;

    public void setDataSource(){
        BasicDataSource basicDataSource;
        String connectionString = "jdbc:mysql://localhost:3306/tcourse";
        if (dataSources.containsKey(connectionString)) {
            basicDataSource = dataSources.get(connectionString);
        } else {
            basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            basicDataSource.setUrl(connectionString);
            basicDataSource.setUsername("root");
            basicDataSource.setPassword("123456");
            dataSources.put(connectionString, basicDataSource);
        }
        try {
            basicDataSource.getConnection();
            jdbcTemplate = new JdbcTemplate(basicDataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn(BasicDataSource basicDataSource){
        try {
            return basicDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JdbcTemplate getJdbcTemplate(BasicDataSource basicDataSource){
        JdbcTemplate jdbcTemplate;
        jdbcTemplate = new JdbcTemplate(basicDataSource);
        return jdbcTemplate;
    }

    public void setup(){
        setDataSource();

        System.out.println("Creating tables");


        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS course(" +
                "id SERIAL, name VARCHAR(20), mark INT(10))");

        List<Object[]> courses = Arrays.asList("xqy", "simida", "hq", "meme", "aaa").stream()
                .map(name -> {
                    Object[] o = new Object[2];
                    o[0] = name;
                    o[1] = Math.random()*100;
                    return o;
                })
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO course(name, mark) VALUES (?,?)", courses);
    }

    public List<TCourse> getAllCourse() {

        List<TCourse> list = new ArrayList<>();
        jdbcTemplate.query(
                "SELECT id, name, mark FROM course",
                (rs, rowNum) -> new TCourse(rs.getInt("id"), rs.getString("name"), rs.getInt("mark"))
        ).forEach(tCourse -> {
            System.out.println(tCourse.toString());
            list.add(tCourse);
        });
        return list;
    }

    public void clean(){
        jdbcTemplate.execute("delete from course");
    }
}
