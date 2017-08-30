package xqy.util;

import xqy.domain.User;

import java.util.List;
import java.util.Map;

/**
 * Created by xqy on 17/8/30.
 */
public interface DataSource {
    List<User> getAll();
    Map<String, String> getAcount();
}
