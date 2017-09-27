package leader.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import util.ThreadLocalUtil;

/**
 * Created by xqy on 17/9/25.
 */
@Component
public class PersonService2 {

    public PersonService2(){
        System.out.println("------ personService2 created...");
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    IPersonService personService1;

    @Transactional(propagation = Propagation.REQUIRED)
    public void create(Person person){
        System.out.println("-->> try to create  ... ");
        int rows = jdbcTemplate.update("insert into person(first_name,last_name,city,country) values(?,?,?,?)",
                new Object[]{person.getFirstName(),person.getLastName(),person.getCity(),person.getCountry()});

        if(rows == 0){
            throw new RuntimeException("  X create person failure, do rollback...");
        }
        personService1.queryPerson("");
        System.out.println("  √ create person complete ... ");
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public void updatePerson(int id,String city,String country){
        System.out.println("-->> try to update city[ "+city+" ] ... ");
        ThreadLocalUtil.dumphreadLocals();
        System.out.println("  ∆ before update: " + personService1.getPerson(id));
        int rows = jdbcTemplate.update("update person set city=?,country=? where id=?", new Object[]{city,country,id});
        if(rows == 0){
            throw new RuntimeException("  X update person failure, do rollback...");
        }
        System.out.println("  ∆ after update: " + personService1.getPerson(id));
        System.out.println("  √ update person complete ... ");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void deletePerson(int id){
        System.out.println("-->> try to delete  ... ");
        ThreadLocalUtil.dumphreadLocals();
        int rows = jdbcTemplate.update("delete from person where id=?", new Object[]{id});
        if(rows == 0){
            throw new RuntimeException("  X delete person failure, do rollback...");
        }
        personService1.queryPerson("");
        System.out.println("  √ delete person complete ... ");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Person getPerson(int id){
        System.out.println("-->> try to get a person  ... ");
        Person person = jdbcTemplate.queryForObject("select * from person where id=?",
                new Object[]{id},
                (rs, i) -> {
                    Person p = new Person(rs.getString("first_name"),rs.getString("last_name"),rs.getString("city"),rs.getString("country"));
                    p.setId(rs.getInt("id"));
                    return p;
                });
        System.out.println("  √ get person complete ... ");
        return person;
    }
}
