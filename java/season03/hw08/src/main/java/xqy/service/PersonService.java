package xqy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xqy.domain.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class PersonService {

    @Autowired
    DataSource dataSource;

    public List queryPerson(){
        String selectAllPerson = "SELECT * FROM person";
        Connection conn;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(selectAllPerson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List queryPersionByName(String name){
        String queryPerson = "SELECT * FROM person where First_Name like'"+name+"%'";
        Connection conn;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(queryPerson);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertPerson(Person person){
        String inserQuery = "INSERT INTO person(First_Name, Last_Name, City, Country) VALUES(?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(inserQuery);
            stmt.setString(1,person.getFirstName());
            stmt.setString(2,person.getLastName());
            stmt.setString(3,person.getCity());
            stmt.setString(4,person.getCountry());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt !=null){
                    stmt.close();
                }
                if(conn !=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void updatePerson(int personId,String first_name,String last_name ,String city,String country){
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt !=null){
                    stmt.close();
                }
                if(conn !=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int deletePerson(int personId){
        String deletePerson = "DELETE FROM person WHERE id =?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(deletePerson);
            stmt.setInt(1,personId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt !=null){
                    stmt.close();
                }
                if(conn !=null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
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