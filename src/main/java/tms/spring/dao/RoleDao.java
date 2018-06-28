package tms.spring.dao;

import org.springframework.stereotype.Repository;
import tms.spring.entity.Permission;
import tms.spring.entity.Role;

import java.util.Set;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Repository
public interface RoleDao {

    Role selectRoleByName(String name);
    void insertRole(Role role);
    void deleteRoleById(Long id);
    Set<Permission> selectPermissionsById(Long id);
    Set<String> selectPermissionUrlsById(Long id);
}
