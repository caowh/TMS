package tms.spring.dao;

import tms.spring.entity.Permission;
import tms.spring.entity.Role;

import java.util.Set;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public interface RoleDao {

    Role selectRoleByName(String name);
    void insertRole(Role role);
    void deleteRoleById(Long id);
    Set<Permission> selectPermissionsById(Long id);
    Set<String> selectPermissionUrlsById(Long id);
}
