package xqy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by xqy on 17/10/5.
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class AppConfig {

    @Bean
    DataSource dataSource(){
        String connStr = "jdbc:mysql://localhost:3306/leader";
        DataSource ds = new DriverManagerDataSource(connStr,"root","123456");

//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setUrl(connStr);
//        ds.setUsername("root");
//        ds.setPassword("123456");
        return ds;
    }
}
