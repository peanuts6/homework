package leader.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import util.ThreadLocalUtil;

import java.util.List;

/**
 * Created by xqy on 17/9/25.
 */
@Component
public class PersonService1 implements IPersonService{
    public PersonService1(){
        System.out.println("------ personService1 created...");
    }
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<?> queryAll(){
        System.out.println("-->> try to query all person  ... ");
        ThreadLocalUtil.dumphreadLocals();
        return query("");
    }

    @Transactional
    public List<?> queryPerson(String keyname){
        System.out.println("-->> try to query person like "+keyname+"%  ... ");
        ThreadLocalUtil.dumphreadLocals();
        return query(keyname);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Person getPerson(int id){
        System.out.println("-->> try to get a person ... ");
        ThreadLocalUtil.dumphreadLocals();
        Person person = jdbcTemplate.queryForObject("select * from person where id=?",
                new Object[]{id},
                (rs, i) -> {
                    Person p = new Person(rs.getString("first_name"),rs.getString("last_name"),rs.getString("city"),rs.getString("country"));
                    p.setId(rs.getInt("id"));
                    return p;
                });
        System.out.println("  √ get a person: " + person);
        return person;
    }

    public List<?> query(String keyname){
        List<Person> list =  jdbcTemplate.query("select * from person where first_name like '"+keyname+"%'",
                (rs, i) -> {
                    Person person = new Person(rs.getString("first_name"),rs.getString("last_name"),rs.getString("city"),rs.getString("country"));
                    person.setId(rs.getInt("id"));
                    return person;
                });
        for(Person p:list){
            System.out.println(p);
        }
        return list;
    }

}
