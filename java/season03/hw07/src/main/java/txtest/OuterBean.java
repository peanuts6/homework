package txtest;

import txtest.User;

/**
 * Created by xqy on 17/9/25.
 */
public interface OuterBean {
    void testRequired(User user);

    void testRequiresNew(User user);
}
