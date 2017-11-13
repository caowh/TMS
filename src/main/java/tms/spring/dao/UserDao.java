package tms.spring.dao;

import tms.spring.entity.Role;
import tms.spring.entity.User;

import java.util.Set;

/**
 * Created by user on 2017/7/14.
 */
public interface UserDao {

     User selectUserByName(String name);
     User selectUserById(Long id);
     User selectUserByEmail(String email);
     void insertUser(User user);
     void deleteUserById(Long id);
     Set<Role> selectRolesById(Long id);
     Set<String> selectRoleNamesById(Long id);
     void updateLastLoginTime(User user);
     void updatePasswordByEmail(User user);
}
