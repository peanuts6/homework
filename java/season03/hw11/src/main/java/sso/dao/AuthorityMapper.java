package sso.dao;

import java.util.List;
import sso.model.Authority;

public interface AuthorityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authorities
     *
     * @mbg.generated
     */
    int insert(Authority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authorities
     *
     * @mbg.generated
     */
    List<Authority> selectAll();
}
