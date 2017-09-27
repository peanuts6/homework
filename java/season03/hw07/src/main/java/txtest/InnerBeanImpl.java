package txtest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xqy on 17/9/25.
 */
@Service
public class InnerBeanImpl implements InnerBean {
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void testRequired() {
        throw new RuntimeException("Rollback this transaction!");
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void testRequiresNew() {
        throw new RuntimeException("Rollback this transaction!");
    }
}
