package leader.trans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xqy on 17/9/20.
 */
@Component
public class EmployeeService  {

    @Autowired
    MyDataSource myDataSource;

    public List<Employee> queryEmployees(){
        List<Employee> employees = new ArrayList<>();
        employees = myDataSource.getJdbcTemplate().query("select * from employee", new EmployeeMapper());
        System.out.println("    √ query "+employees.size()+" employees ... ");
        for(Employee employee:employees){
            System.out.println("        "+employee);
        }
        return employees;
    }

    public void saveEmployee(Employee e){
        System.out.println("    try to update employee ... ");
        Employee employee = myDataSource.getJdbcTemplate().queryForObject("select * from employee where empid=?",
                new Object[]{new Integer(e.getEmpid())},new EmployeeMapper());
        System.out.println("    "+employee);
        if(employee.getEmpid() == 0){
            throw new RuntimeException("A Runtime Exception...");
        }
        System.out.println("    √ save employee ... ");
    }

    public void updateEmployee(int empid, long salary){
        System.out.println("    try to update employee ... "+empid);
        Employee employee = myDataSource.getJdbcTemplate().queryForObject("select * from employee where empid=?",
                new Object[]{new Integer(empid)},new EmployeeMapper());
        System.out.println("    "+employee);
        if(employee.getEmpid() == 0){
            throw new RuntimeException("A Runtime Exception...");
        }
        myDataSource.getJdbcTemplate().update("update employee set salary = ? where empid = ?",
                new Object[]{new Long(salary),new Integer(empid)});
        System.out.println("    √ update employee ... ");
    }

    public void createEmployee(Employee e){
        System.out.println("    try to create employee ... ");
//        Employee employee = myDataSource.getJdbcTemplate().queryForObject("select * from employee where empid=?",
//                new Object[]{new Integer(e.getEmpid())},new EmployeeMapper());
//        System.out.println("    "+employee);
//        if(employee.getEmpid() == 0){
//            throw new RuntimeException("A Runtime Exception...");
//        }
        myDataSource.getJdbcTemplate().update("insert into employee(empid,name,age,salary) values(?,?,?,?)",
                new Object[]{new Integer(e.getEmpid()),new String(e.getName()),new Integer(e.getAge()),new Long(e.getSalary())});
        System.out.println("    √ create employee ... ");
    }

    public void deleteEmployee(int empid){
        System.out.println("    try to delete employee ... ");
        Employee employee = myDataSource.getJdbcTemplate().queryForObject("select * from employee where empid=?",
                new Object[]{new Integer(empid)},new EmployeeMapper());
        System.out.println("    "+employee);
        if(employee.getEmpid() == 0){
            throw new RuntimeException("A Runtime Exception...");
        }
        myDataSource.getJdbcTemplate().update("delete from employee where empid=?", empid);
        System.out.println("    √ delete employee ... ");
    }

    public Employee getEmployee(int empid){
        System.out.println("    - try to get employee ... ");
        Employee employee = myDataSource.getJdbcTemplate().queryForObject("select * from employee where empid=?",
                new Object[]{new Integer(empid)},new EmployeeMapper());
        System.out.println("    "+employee);
        if(employee.getEmpid() == 0){
            throw new RuntimeException("A Runtime Exception...");
        }
        System.out.println("    √ get employee ... ");
        return employee;
    }

    public void throwException(){
        throw new RuntimeException("A Runtime Exception...");
    }


    public class EmployeeMapper implements RowMapper<Employee>{

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Employee(rs.getInt("empid"),rs.getString("name"),rs.getInt("age"),rs.getLong("salary"));
        }
    }
}
