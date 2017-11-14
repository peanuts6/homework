package leader.sso.mapping;

import java.util.List;
import leader.sso.domain.Authority;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface AuthorityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authorities
     *
     * @mbg.generated
     */
    @Insert({
        "insert into authorities (id, username, ",
        "authority)",
        "values (#{id,jdbcType=BIGINT}, #{username,jdbcType=VARCHAR}, ",
        "#{authority,jdbcType=VARCHAR})"
    })
    int insert(Authority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authorities
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, username, authority",
        "from authorities"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="authority", property="authority", jdbcType=JdbcType.VARCHAR)
    })
    List<Authority> selectAll();
}