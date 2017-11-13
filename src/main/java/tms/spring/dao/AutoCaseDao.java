package tms.spring.dao;

import tms.spring.entity.AutoCase;

import java.util.List;

/**
 * Created by user on 2017/11/9.
 */
public interface AutoCaseDao {

    void insertsAutoCases(List<AutoCase> autoCases);
    List<AutoCase> selectAutoCases(AutoCase autoCase);
    List<Integer> selectAutoCaseTypes(List<Long> ids);
    AutoCase selectById(Long id);
    void updateAutoCase(AutoCase autoCase);
}
