package txtest2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xqy on 17/9/25.
 */
public class PersonDAO implements IPersonDAO {
    private JdbcTemplate jdbcTemplate;
    private TransactionTemplate transactionTemplate;

    @Override
    public int insertUser(final Person person) {
        //insert statement returns results so we are using
        //TransactionCallback
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                try {
                    String inserQuery = "INSERT INTO person(First_Name, Last_Name, City, Country) VALUES(?, ?, ?, ?)";
                    Object[] params = new Object[] { person.getFirstName(),
                            person.getLastName(),
                            person.getCity(),
                            person.getCountry() };
                    int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
                    int value = jdbcTemplate.update(inserQuery, params,types);
                    System.out.println("\nPerson inserted to the table");
                    return value;
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
                return 0;
            }
        });
    }

    @Override
    public void deletePerson(final int personID) {
        //delete statement doesn't returns anything so we are using
        //TransactionCallbackWithoutResult
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    String deletePerson = "DELETE FROM person WHERE id =?";
                    Object[] params = new Object[] { personID };
                    int[] types = new int[] { Types.VARCHAR };
                    jdbcTemplate.update(deletePerson, params, types);
                    System.out.println("\nPerson with id 1 deleted from the table\n");
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
            }

        });
    }

    @Override
    public void selectAllPerson() {
        System.out.println("\nList of person in the table\n");
        String selectAllPerson = "SELECT * FROM person";
        List<Map<String, Object>> listOfPerson = jdbcTemplate.queryForList(selectAllPerson);
        for (Iterator<Map<String, Object>> iterator = listOfPerson.iterator(); iterator.hasNext();) {
            Map<String, Object> map = (Map<String, Object>)iterator.next();
            System.out.println(map);
        }
        System.out.println();
    }

    @Override
    public void selectPersonByName() {
        System.out.println("\nList of person name Java in the table\n");
        String selectAllPerson = "SELECT * FROM person where First_Name ='Java'";
        List<Map<String, Object>> listOfPerson = jdbcTemplate.queryForList(selectAllPerson);
        for (Iterator<Map<String, Object>> iterator = listOfPerson.iterator(); iterator.hasNext();) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            System.out.println(map);
        }
        System.out.println();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public TransactionTemplate getTransactionTemplate() {
        return transactionTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

}

//CREATE TABLE person (
//        id int NOT NULL AUTO_INCREMENT,
//        First_Name  varchar(25) NULL,
//        Last_Name   varchar(25) NULL,
//        City        varchar(25) NULL,
//        Country     varchar(25) NULL,
//        PRIMARY KEY (id)
//);
