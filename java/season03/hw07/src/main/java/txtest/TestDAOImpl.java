package txtest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xqy on 17/9/25.
 */
@Service
public class TestDAOImpl implements TestDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void insertUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
