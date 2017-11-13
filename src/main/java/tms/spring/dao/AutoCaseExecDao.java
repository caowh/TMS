package tms.spring.dao;

import tms.spring.entity.AutoCaseExec;

/**
 * Created by user on 2017/11/10.
 */
public interface AutoCaseExecDao {

    void insert(AutoCaseExec autoCaseExec);
    Long selectMaxId();
    AutoCaseExec selectById(Long id);
}
