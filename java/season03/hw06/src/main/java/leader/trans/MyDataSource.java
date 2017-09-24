package leader.trans;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by xqy on 17/9/21.
 */
@Component
public class MyDataSource {
    DataSource dataSource;
    JdbcTemplate jdbcTemplate;
    PlatformTransactionManager transactionManager;

    public MyDataSource(){
        this.initDs();
    }
    public void initDs(){
        String connectionString = "jdbc:mysql://localhost:3306/leader";
        dataSource = new DriverManagerDataSource(connectionString,"root","123456");
        jdbcTemplate = new JdbcTemplate(dataSource);
        transactionManager = new EmployeeTransactionManager(dataSource);
    }
    public Connection getConnection(){
        try {
            System.out.println("--- get connection ...");
            Connection conn = dataSource.getConnection();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        try {
            if(dataSource.getConnection() != null && !dataSource.getConnection().isClosed()) {
                dataSource.getConnection().close();
            }
            System.out.println("--- close connection ...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public DataSource getDataSource(){
        return dataSource;
    }
    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }
    public PlatformTransactionManager getTransactionManager(){
        return transactionManager;
    }
}




