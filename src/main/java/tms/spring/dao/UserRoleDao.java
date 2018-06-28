package tms.spring.dao;

import org.springframework.stereotype.Repository;
import tms.spring.entity.UserRole;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Repository
public interface UserRoleDao {

    void insertUserRole(UserRole userRole);
    void deleteUserRole(UserRole userRole);
}
