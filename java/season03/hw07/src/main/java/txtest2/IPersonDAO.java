package txtest2;

/**
 * Created by xqy on 17/9/25.
 */
public interface IPersonDAO {
    int insertUser(Person person);
    void deletePerson(int personID);
    void selectAllPerson();
    void selectPersonByName();
}

