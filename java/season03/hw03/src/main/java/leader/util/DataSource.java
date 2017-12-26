package leader.util;

import leader.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by leader on 17/8/30.
 */
public interface DataSource {
    List<User> getAll();
    Map<String, String> getAcount();
}
