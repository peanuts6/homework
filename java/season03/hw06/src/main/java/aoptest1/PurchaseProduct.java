package aoptest1;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by xqy on 17/9/21.
 */
public class PurchaseProduct {
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void buyProduct(String username , int productId){
        try {
            int price = jdbcTemplate.queryForObject( "SELECT PRICE FROM T_PRODUCTS WHERE Id = ?",
                    new Object[] { new Integer(productId) }, Integer.class);
            jdbcTemplate.update("UPDATE T_PRODUCTS SET STOCK = STOCK - 1 WHERE ID = ?",
                    new Object[] { new Integer(productId) });
            jdbcTemplate.update( "UPDATE T_ACCOUNT SET AMOUNT = AMOUNT - ?  WHERE USERNAME = ?",
                    new Object[] { price, username });
        } catch (DataAccessException e){
            throw e;
        }
    }

    public void displayState(String username, int productId){
        System.out.println("Database state after purchase ");

        Product product = jdbcTemplate.queryForObject("SELECT * FROM T_PRODUCTS WHERE ID = ? ",
                new Object[]{new Integer(productId)},
                (rs, rowNum) -> {
                    Product p = new Product();
                    p.setPrice(rs.getInt("price"));
                    p.setProductId(rs.getInt("id"));
                    p.setProductName(rs.getString("name"));
                    p.setStock(rs.getInt("stock"));
                    return p;
                });

        System.out.println(product);
        Account account = jdbcTemplate.queryForObject("SELECT * FROM T_ACCOUNT WHERE USERNAME = ? ",
                new Object[]{username},
                (rs, rowNum) -> {
                    Account  a = new Account();
                    a.setBalance(rs.getInt("amount"));
                    a.setUsername(rs.getString("username"));
                    return a;
                });
        System.out.println(account);
    }
}
