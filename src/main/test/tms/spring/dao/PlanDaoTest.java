package tms.spring.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.Plan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by user on 2017/8/28.
 */
public class PlanDaoTest extends BaseDaoTest{

    @Autowired
    private PlanDao planDao;

    @Test
    public void insertPlans() throws Exception {
//        Plan plan=new Plan();
//        List<Plan> plans=new ArrayList<Plan>();
//        plans.add(plan);
//        planDao.insertPlans(plans);
    }

    @Test
    public void selectPlanResults(){
        Plan plan=new Plan();
        plan.setName("GVML规范");
        plan.setVersion("v1.0");
        plan.setNode("0");
        plan.setType("SEVERITY");
        List<String> results=planDao.selectPlanResults(plan);
        assertTrue(results.size()>0);
    }

}