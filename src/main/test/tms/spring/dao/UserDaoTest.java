package tms.spring.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.Role;
import tms.spring.entity.User;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Administrator on 2017/7/15 0015.
 */
public class UserDaoTest extends BaseDaoTest{

    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    @Autowired
    private UserDao userDao;

    @Test
    public void selectUserByName() throws Exception {
        User user=userDao.selectUserByName("admin");
        assertEquals("admin",user.getUsername());
    }

    @Test
    public void selectUserByEmail() throws Exception {
        User user=userDao.selectUserByEmail("1240597349@qq.com");
        assertEquals("admin",user.getUsername());
    }

    @Test
    public void insertUser() throws Exception {
        User user=new User();
        user.setUsername("jack");
        user.setPassword("admin");
        user.setNickname("老陈");
        user.setDepartment("谷歌");
        user.setStatus(new Long(1));
        user.setEmail("jack@qq.com");
        user.setCreateTime(new Date());
        userDao.insertUser(user);
        assertEquals("admin",userDao.selectUserByName("jack").getPassword());
    }

    @Test
    public void deleteUserById() throws Exception {
        User user=userDao.selectUserByName("jack");
        userDao.deleteUserById(user.getId());
        assertNull(userDao.selectUserByName("jack"));
    }

    @Test
    public void selectRolesById() throws Exception {
        Set<Role> roles=userDao.selectRolesById(new Long(1));
        assertNotNull(roles.size());
    }

    @Test
    public void selectRoleNamesById() throws Exception {
        Set<String> RoleNames=userDao.selectRoleNamesById(new Long(1));
        assertNotNull(RoleNames.size());
    }

    @Test
    public void updateLastLoginTime() throws Exception {
        User user=new User();
        user.setLastLoginTime(new Date());
        user.setUsername("admin");
        userDao.updateLastLoginTime(user);
    }

    @Test
    public void updatePasswordByEmail() throws Exception {
        User user=new User();
        user.setLastLoginTime(new Date());
        user.setEmail("1240597349@qq.com");
        user.setPassword("47a584b728e69533311a76ff05e8dc01");
        userDao.updatePasswordByEmail(user);
    }
}