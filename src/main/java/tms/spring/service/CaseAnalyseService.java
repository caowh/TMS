package tms.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.Plan;
import tms.spring.exception.CaseAnalyseException;
import tms.spring.handler.CaseAnalyseHandler;
import tms.spring.utils.CaseAnalyseUtil;
import tms.spring.utils.PlanDataType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/8/28.
 */
public class CaseAnalyseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    private Map<String,CaseAnalyseHandler> caseAnalyses;

    public void setCaseAnalyses(Map<String, CaseAnalyseHandler> caseAnalyses) {
        this.caseAnalyses = caseAnalyses;
    }

    public Map<String,Object> analyse(Map<String,String> jsonMap) throws CaseAnalyseException {
        String type=jsonMap.get("type");
        if(type==null){
            throw new CaseAnalyseException("请选择要分析类型");
        }
        CaseAnalyseHandler handler=caseAnalyses.get(type);
        if(handler==null){
            throw new CaseAnalyseException("输入的分析类型不存在");
        }
        return  handler.analyse(jsonMap);
    }

    public Map<String,String> getMessage(String planName,String version){
        return caseAnalyseUtil.getMessage(planName,version);

    }

    public Map<String,String> getNewMessage(String planName){
        String version=caseAnalyseUtil.getNewVersion(planName);
        return getMessage(planName,version);
    }

    public int getCaseTotalCount(Map<String,String> jsonMap) throws CaseAnalyseException {
        String planName=jsonMap.get("planName");
        String version=jsonMap.get("version");
        String node=jsonMap.get("node");
        String type=jsonMap.get("type");
        if(planName==null||node==null||type==null){
            throw new CaseAnalyseException("输入的信息不完善");
        }
        if(version==null||version.equals("")){
            version=caseAnalyseUtil.getNewVersion(planName);
        }
        if(!(type.toUpperCase().equals(PlanDataType.EXECUTE.name())||type.toUpperCase().equals(PlanDataType.SEVERITY.name()))){
            throw new CaseAnalyseException("输入的类型无法进行统计");
        }
        Plan plan=new Plan();
        plan.setName(planName);
        plan.setVersion(version);
        plan.setNode(node);
        plan.setType(type.toUpperCase());
        Map map=(Map)caseAnalyseUtil.getResult(plan);
        return Integer.parseInt(String.valueOf(map.get("total")));
    }
}
