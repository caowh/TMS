package tms.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tms.spring.entity.Plan;
import tms.spring.entity.PlanHelper;
import tms.spring.entity.TreeNode;
import tms.spring.exception.AutoCaseRepertoryException;
import tms.spring.exception.CaseAnalysesException;
import tms.spring.handler.CaseAnalyseHandler;
import tms.spring.utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/8/28.
 */
@Service
public class CaseAnalyseService {

    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    @Autowired
    private Map<String,CaseAnalyseHandler> caseAnalyses;


    public Map<String,Object> analyse(Map<String,String> jsonMap) throws CaseAnalysesException {
        String type=jsonMap.get("type");
        if(type==null){
            throw new CaseAnalysesException("请选择要分析类型");
        }
        CaseAnalyseHandler handler=caseAnalyses.get(type);
        if(handler==null){
            throw new CaseAnalysesException("输入的分析类型不存在");
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

    public int getCaseTotalCount(Map<String,String> jsonMap) throws CaseAnalysesException {
        String planName=jsonMap.get("planName");
        String version=jsonMap.get("version");
        String node=jsonMap.get("node");
        String type=jsonMap.get("type");
        String detail=jsonMap.get("detail");
        if(planName==null||node==null||type==null||detail==null){
            throw new CaseAnalysesException("输入的信息不完善");
        }
        if(version==null||version.equals("")){
            version=caseAnalyseUtil.getNewVersion(planName);
        }
        if(!(type.toUpperCase().equals(PlanDataType.EXECUTE.name())||type.toUpperCase().equals(PlanDataType.SEVERITY.name()))){
            throw new CaseAnalysesException("输入的类型无法进行统计");
        }
        Plan plan=new Plan();
        plan.setName(planName);
        plan.setVersion(version);
        plan.setNode(node);
        plan.setType(type.toUpperCase());
        Map map=(Map)caseAnalyseUtil.getResult(plan);
        int result=0;
        if(detail.contains(",")){
            String[] details=detail.split(",");
            for(int i=0;i<details.length;i++){
                result+=Integer.parseInt(String.valueOf(map.get(details[i])));
            }
        }else {
            result=Integer.parseInt(String.valueOf(map.get(detail)));
        }
        return result;
    }

    public List<PlanHelper> getPlanHelperList(){
        List<PlanHelper> planHelperList=new ArrayList<PlanHelper>();
        List<Map> planList=caseAnalyseUtil.getPlanList();
        if(planList!=null&&planList.size()>0){
            Map<String, Boolean> updateMap=caseAnalyseUtil.getPlanUpdateStatus();
            for (Map testPlan : planList){
                PlanHelper planHelper=new PlanHelper();
                String currentPlanName=String.valueOf(testPlan.get("name"));
                String[] planArr=currentPlanName.split("_");
                String planName=planArr[0];
                String version=planArr[1];
                Map<String,String> map=caseAnalyseUtil.getMessage(planName,version);
                planHelper.setPlanName(planName);
                planHelper.setVersion(version);
                planHelper.setUut(map.get("uut"));
                planHelper.setEnvironment(map.get("environment"));
                planHelper.setLeader(map.get("leader"));
                String planId=String.valueOf(testPlan.get("id"));
                planHelper.setPlanId(planId);
                planHelper.setUpdate(updateMap.get(planId));
                planHelperList.add(planHelper);
            }
        }
        return planHelperList;
    }

    public List<String> getPlanNameList(){
        List<String> list=new ArrayList<String>();
        List<Map> planList=caseAnalyseUtil.getPlanList();
        if(planList!=null&&planList.size()>0){
            for (Map testPlan : planList){
                String currentPlanName=String.valueOf(testPlan.get("name"));
                String[] planArr=currentPlanName.split("_");
                String planName=planArr[0];
                if(!list.contains(planName)){
                    list.add(planName);
                }
            }
        }
        return list;
    }

    public TreeNode getModuleTree(Map<String, String> jsonMap) throws CaseAnalysesException {
        String planName=jsonMap.get("planName");
        String planVersion=jsonMap.get("planVersion");
        if(planName==null||planVersion==null){
            throw new CaseAnalysesException("输入的信息不完善");
        }
        String[] versions=planVersion.split(",");
        Plan plan=new Plan();
        plan.setName(planName);
        plan.setType(PlanDataType.SUITES.name());
        plan.setNode("0");
        int root_id=0;
        List<TreeNode> treeNodes=new ArrayList<TreeNode>();
        for(String version:versions){
            plan.setVersion(version);
            List<Map> testSuites= (List<Map>) caseAnalyseUtil.getResult(plan);
            for(Map map:testSuites){
                int cid=Integer.parseInt(String.valueOf(map.get("id")));
                int pid=Integer.parseInt(String.valueOf(map.get("parent_id")));
                String name=String.valueOf(map.get("name"));
                if(root_id==0||root_id>cid){
                    root_id=cid;
                }
                TreeNode treeNode=new TreeNode();
                treeNode.setCid(cid);
                treeNode.setPid(pid);
                treeNode.setName(name);
                if(!treeNodes.contains(treeNode)){
                    treeNodes.add(treeNode);
                }
            }
        }
        TreeUtil treeUtil=new TreeUtil(treeNodes);
        return treeUtil.generateTreeNode(root_id);
    }

    public List<String> getSupportType(Map<String,String> jsonMap) throws AutoCaseRepertoryException {
        String version=jsonMap.get("planVersion");
        List<String> list=new ArrayList<String>();
        if(version==null||version.equals("")){
            throw new AutoCaseRepertoryException("输入的版本号为空");
        }
        String[] versions=version.split(",");
        if(versions.length>1){
            list.add("severityCompare");
            list.add("caseBugRatioCompare");
            list.add("bugCountCompare");
        }else {
            list.add("severity");
            list.add("caseBugCount");
            list.add("caseBugTimeChange");
        }
        return list;
    }
}
