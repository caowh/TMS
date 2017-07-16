package tms.spring.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.UserRole;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class UserRoleDaoTest extends BaseDaoTest{

    @Autowired
    private UserRoleDao userRoleDao;


    @Test
    public void insertUserRole() throws Exception {
        UserRole userRole=new UserRole();
        userRole.setRid(new Long(3));
        userRole.setUid(new Long(1));
        userRoleDao.insertUserRole(userRole);
    }

    @Test
    public void deleteUserRole() throws Exception {
        UserRole userRole=new UserRole();
        userRole.setRid(new Long(3));
        userRole.setUid(new Long(1));
        userRoleDao.deleteUserRole(userRole);
    }
}