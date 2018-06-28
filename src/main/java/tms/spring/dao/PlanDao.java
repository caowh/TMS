package tms.spring.dao;

import org.springframework.stereotype.Repository;
import tms.spring.entity.Plan;

import java.util.List;
import java.util.Set;

/**
 * Created by user on 2017/8/25.
 */
@Repository
public interface PlanDao {

    void insertPlans(List<Plan> plans);
    List<String> selectPlanResults(Plan plan);
}
