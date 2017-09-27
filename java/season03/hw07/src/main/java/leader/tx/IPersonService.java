package leader.tx;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xqy on 17/9/26.
 */
public interface IPersonService {
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    List<?> queryAll();

    List<?> queryPerson(String keyname);

    Person getPerson(int id);

}
