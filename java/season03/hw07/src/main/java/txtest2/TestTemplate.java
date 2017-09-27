package txtest2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xqy on 17/9/25.
 */
public class TestTemplate {
    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("txtest2.xml");

        IPersonDAO personDAO = applicationContext.getBean("personDAO", IPersonDAO.class);
        Person person = new Person("Java", "Honk","NY", "USA");

        //Insert user to the table 3 times:
        for (int i = 0; i < 2; i++) {
            personDAO.insertUser(person);
        }

        //Delete first person from table
        personDAO.deletePerson(1);

        //Select all inserted user from the table
        personDAO.selectAllPerson();

        //Select data from tabel where person name is "Java"
        personDAO.selectPersonByName();

        applicationContext.close();

    }
}
