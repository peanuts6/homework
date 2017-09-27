package leader.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xqy on 17/9/25.
 */
@Component
public class EmployeeService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    TransactionTemplate transactionTemplate;

    public List<Employee> queryEmployees(String name){
        List<Employee> employees = new ArrayList<>();
        employees = jdbcTemplate.query("select * from employee where name like '%"+name+"%'",
                new EmployeeMapper());
        System.out.println("    √ query "+employees.size()+" employees ... ");
        for(Employee employee:employees){
            System.out.println("        "+employee);
        }
        return employees;
    }

    public int createEmployee(Employee e){
        System.out.println("    try to create employee ... ");
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                try {
                    int rows = jdbcTemplate.update("insert into employee(empid,name,age,salary) values(?,?,?,?)",
                            new Object[]{e.getEmpid(),e.getName(),e.getAge(),e.getSalary()});
                    if(rows==0){
                        throw new RuntimeException("    X create error ");
                    }
                    System.out.println("    √ create employee completed ... ");
                    return rows;
                } catch (Exception e) {
                    System.out.println("    X create failure, do rollback...");
                    transactionStatus.setRollbackOnly();
                }
                return 0;
            }
        });
    }

    public void updateEmployee(int empid, long salary){
        System.out.println("    try to update employee ... "+empid);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    int rows = jdbcTemplate.update("update employee set salary = ? where empid = ?", new Object[]{new Long(salary),new Integer(empid)});
                    if(rows==0){
                        throw new RuntimeException("    X update error ");
                    }
                    System.out.println("    √ update employee ... ");
                } catch (Exception e) {
                    System.out.println("    X update failure, do rollback...");
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }

    public int deleteEmployee(int empid){
        System.out.println("    try to delete employee ... "+empid);
        return transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus transactionStatus) {
                try {
                    int rows = jdbcTemplate.update("delete from employee where empid=?", empid);
                    if(rows==0){
                        throw new RuntimeException("    X delete error ");
                    }
                    System.out.println("    √ delete employee ... ");
                    return rows;
                } catch (Exception e) {
                    System.out.println("    X delete failure, do rollback...");
                    transactionStatus.setRollbackOnly();
                }
                return 0;
            }
        });
    }

    public class EmployeeMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(rs.getInt("empid"),rs.getString("name"),rs.getInt("age"),rs.getLong("salary"));
        }
    }
}
