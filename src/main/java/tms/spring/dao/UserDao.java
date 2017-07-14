package tms.spring.dao;

import tms.spring.entity.User;

/**
 * Created by user on 2017/7/14.
 */
public interface UserDao {

    public User findUserByName(String name);

}
