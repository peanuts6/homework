package temp.mapping;

import java.util.List;
import temp.domain.Authority;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.security.core.GrantedAuthority;

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
    
    @Select({
        "select",
        "id, username, authority",
        "from authorities where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
    	 @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
         @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
         @Result(column="authority", property="authority", jdbcType=JdbcType.VARCHAR)
    })
    List<Authority> getAllAuthorities(String username);
}