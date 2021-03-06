package sso.mapping;

import java.util.List;
import sso.domain.User;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_users
     *
     * @mbg.generated
    /* */
    @Insert({
        "insert into t_users (id, username, ",
        "password, enabled)",
        "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{enabled,jdbcType=BIT})"
    })
    int insert(User record);

    @Delete({
        "delete from t_users where id = #{id,jdbcType=INTEGER}"
    })
    void delete(Integer id);

    @Select({
        "select id, username, password, enabled from t_users where id=#{id,jdbcType=INTEGER}"
    })
    User get(Integer id);

    @Update({
        "update from t_users ",
        "set enabled=#{enabled,jdbcType=BIT}, ",
        "where id= #{id,jdbcType=INTEGER}"
    })
    void update(User record);

    @Select({
        "select id, username, password, enabled from t_users where username=#{username,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT)
    })
    User getUserByName(String username);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_users
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, username, password, enabled",
        "from t_users"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT)
    })
    List<User> selectAll();
}
