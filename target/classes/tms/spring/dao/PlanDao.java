package tms.spring.dao;

import tms.spring.entity.Plan;

import java.util.List;

/**
 * Created by user on 2017/8/25.
 */
public interface PlanDao {

    void insertPlans(List<Plan> plans);
}
