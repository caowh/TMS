package tms.spring.dao;

import tms.spring.entity.RolePermission;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public interface RolePermissionDao {

    void insertRolePermission(RolePermission rolePermission);
    void deleteRolePermission(RolePermission rolePermission);
}
