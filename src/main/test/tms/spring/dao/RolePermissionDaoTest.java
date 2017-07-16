package tms.spring.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.RolePermission;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class RolePermissionDaoTest extends BaseDaoTest{

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Test
    public void insertRolePermission() throws Exception {
        RolePermission rolePermission=new RolePermission();
        rolePermission.setPid(new Long(4));
        rolePermission.setRid(new Long(1));
        rolePermissionDao.insertRolePermission(rolePermission);
    }

    @Test
    public void deleteRolePermission() throws Exception {
        RolePermission rolePermission=new RolePermission();
        rolePermission.setPid(new Long(4));
        rolePermission.setRid(new Long(1));
        rolePermissionDao.deleteRolePermission(rolePermission);
    }

}