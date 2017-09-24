package aoptest1;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xqy on 17/9/21.
 */
public class BuyProduct {
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager( PlatformTransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    public void buyProduct(String username , int productId){
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        try
        {
            int price = jdbcTemplate.queryForObject( "SELECT PRICE FROM T_PRODUCTS WHERE Id = ?",
                    new Object[] { new Integer(productId) }, Integer.class);
            jdbcTemplate.update("UPDATE T_PRODUCTS SET STOCK = STOCK - 1 WHERE ID = ?",
                    new Object[] { new Integer(productId) });
            jdbcTemplate.update( "UPDATE T_ACCOUNT SET AMOUNT = AMOUNT - ?  WHERE USERNAME = ?",
                    new Object[] { price, username });
            transactionManager.commit(status);
        }
        catch (DataAccessException e){
            transactionManager.rollback(status);
            throw e;
        }
    }

    public void displayState(String username, int productId){
        System.out.println("Database state after purchase ");
        Product product = jdbcTemplate.queryForObject
                ("SELECT * FROM T_PRODUCTS WHERE ID = ? ", new Object[]{new Integer(productId)},
                        new RowMapper<Product>() {
                            @Override
                            public Product mapRow(ResultSet rs, int row)
                                    throws SQLException {
                                Product product = new Product();
                                product.setPrice(rs.getInt("price"));
                                product.setProductId(rs.getInt("id"));
                                product.setProductName(rs.getString("name"));
                                product.setStock(rs.getInt("stock"));
                                return product;
                            }
                        } );
        System.out.println(product);
        Account account = jdbcTemplate.queryForObject
                ("SELECT * FROM T_ACCOUNT WHERE USERNAME = ? ", new Object[]{username},
                        new RowMapper<Account>() {
                            @Override
                            public Account mapRow(ResultSet rs, int row)
                                    throws SQLException {
                                Account  account = new Account();
                                account.setBalance(rs.getInt("amount"));
                                account.setUsername(rs.getString("username"));
                                return account;
                            }
                        } );
        System.out.println(account);
    }
}

//    CREATE TABLE `t_account` (
//        `id` int(11) NOT NULL AUTO_INCREMENT,
//        `username` varchar(45) DEFAULT NULL,
//        `amount` int(11) DEFAULT NULL,
//        PRIMARY KEY (`id`)
//        );
//
//        CREATE TABLE `t_products` (
//        `id` int(11) NOT NULL,
//        `name` varchar(45) DEFAULT NULL,
//        `price` int(11) DEFAULT NULL,
//        `stock` int(11) DEFAULT NULL,
//        PRIMARY KEY (`id`)
//        );

//    INSERT INTO t_account (`username`, `amount`) VALUES ('user1', 2000);
//    INSERT INTO t_account (`username`, `amount`) VALUES ('user2', 4000);
//    INSERT INTO t_account (`username`, `amount`) VALUES ('user3', 1300);
//    INSERT INTO t_products (`id`, `name`, `price`, `stock`) VALUES (101, 'Product A', 100, 20);
//    INSERT INTO t_products (`id`, `name`, `price`, `stock`) VALUES (102, 'Product B', 200, 25);
