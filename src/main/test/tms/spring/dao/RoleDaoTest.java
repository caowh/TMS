package tms.spring.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.Permission;
import tms.spring.entity.Role;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class RoleDaoTest extends BaseDaoTest{

    @Autowired
    private RoleDao roleDao;

    @Test
    public void insertRole() throws Exception {
        Role role=new Role();
        role.setName("测试员");
        role.setType("for test");
        roleDao.insertRole(role);
        assertNotNull(roleDao.selectRoleByName("测试员"));
    }

    @Test
    public void deleteRoleById() throws Exception {
        Role role=roleDao.selectRoleByName("测试员");
        roleDao.deleteRoleById(role.getId());
        assertNull(roleDao.selectRoleByName("测试员"));
    }

    @Test
    public void selectRolesById() throws Exception {
        Role role=roleDao.selectRoleByName("系统管理员");
        Set<Permission> permissions=roleDao.selectPermissionsById(role.getId());
        assertNotNull(permissions.size());
    }

    @Test
    public void selectPermissionUrlsById() throws Exception {
        Role role=roleDao.selectRoleByName("系统管理员");
        Set<String> permissionUrls=roleDao.selectPermissionUrlsById(role.getId());
        assertNotNull(permissionUrls.size());
    }
}