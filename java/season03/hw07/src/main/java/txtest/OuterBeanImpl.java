package txtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xqy on 17/9/25.
 */
@Service
public class OuterBeanImpl implements OuterBean {
    @Autowired
    private TestDAO testDAO;

    @Autowired
    private InnerBean innerBean;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void testRequired(User user) {
        testDAO.insertUser(user);
        try{
            innerBean.testRequired();
        } catch(RuntimeException e){
            // handle exception
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void testRequiresNew(User user) {
        testDAO.insertUser(user);
        try{
            innerBean.testRequiresNew();
        } catch(Exception e){
            // handle exception
        }
    }
}


//CREATE TABLE person (
//        id int NOT NULL AUTO_INCREMENT,
//        First_Name  varchar(25) NULL,
//        Last_Name   varchar(25) NULL,
//        Street_Name varchar(25) NULL,
//        City        varchar(25) NULL,
//        State       varchar(25) NULL,
//        Country     varchar(25) NULL,
//        PRIMARY KEY (id)
//);
