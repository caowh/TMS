package tms.spring.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.Permission;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class PermissionDaoTest extends BaseDaoTest{

    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void insertPermission() throws Exception {
        Permission permission=new Permission();
        permission.setName("test");
        permission.setUrl("/test.jsp");
        permissionDao.insertPermission(permission);
    }

    @Test
    public void deletePermissionById() throws Exception {
        Permission permission=permissionDao.selectPermissionDaoByName("test");
        permissionDao.deletePermissionById(permission.getId());
    }

}