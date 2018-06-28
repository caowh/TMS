package tms.spring.dao;

import org.springframework.stereotype.Repository;
import tms.spring.entity.Permission;


/**
 * Created by Administrator on 2017/7/15 0015.
 */
@Repository
public interface PermissionDao {

    Permission selectPermissionDaoByName(String name);
    void insertPermission(Permission permission);
    void deletePermissionById(Long id);
}
