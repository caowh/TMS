package tms.spring.dao;

import org.springframework.stereotype.Repository;
import tms.spring.entity.RolePermission;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Repository
public interface RolePermissionDao {

    void insertRolePermission(RolePermission rolePermission);
    void deleteRolePermission(RolePermission rolePermission);
}
