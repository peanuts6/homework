package jdbctest;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by xqy on 17/9/18.
 */
public interface StudentDAO {

    public void setDataSource(DataSource ds);

    public void create(String name, Integer age, Integer marks, Integer year, int rs);

    public List<StudentMarks> queryAll();
}
