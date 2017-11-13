package tms.spring.service;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tms.spring.dao.AutoCaseDao;
import tms.spring.dao.AutoCaseExecDao;
import tms.spring.dao.UserDao;
import tms.spring.entity.AutoCase;
import tms.spring.entity.AutoCaseExec;
import tms.spring.entity.AutoCaseHelper;
import tms.spring.entity.User;
import tms.spring.exception.AutoCaseRepertoryException;
import org.apache.commons.io.IOUtils;
import tms.spring.exception.CaseAnalysesException;
import tms.spring.handler.AutoCaseConvertHandler;
import tms.spring.handler.impl.GVMLAutoCaseConvert;
import tms.spring.handler.impl.PostManAutoCaseConvert;
import tms.spring.shiro.filter.ShiroFilterUtils;
import tms.spring.utils.CaseAnalyseUtil;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by user on 2017/11/8.
 */
@Service
public class AutoCaseRepertoryService {

    /***
     * type取值 1：postman方式解析    2：gvml方式解析
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private AutoCaseDao autoCaseDao;

    @Autowired
    private AutoCaseExecDao autoCaseExecDao;

    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    public void upload(String updateReason, String name, String type, String planName, String node,List<MultipartFile> files) throws AutoCaseRepertoryException, IOException {
        if(name==null||type==null||files==null||node==null||node.equals("")||name.equals("")||type.equals("")||files.size()==0){
            throw new AutoCaseRepertoryException("输入的参数不合法");
        }
        if(type.equals("1")){
            AutoCaseConvertHandler handler=new PostManAutoCaseConvert();
            saveAutoCase(name,planName,updateReason,node,files,handler);
        }else if(type.equals("2")){
            AutoCaseConvertHandler handler=new GVMLAutoCaseConvert();
            saveAutoCase(name,planName,updateReason,node,files,handler);
        }else{
            throw new AutoCaseRepertoryException("用例的类型不支持");
        }
    }

    private void saveAutoCase(String name, String planName, String updateReason, String node,List<MultipartFile> files, AutoCaseConvertHandler handler) throws IOException, AutoCaseRepertoryException {
        for(MultipartFile file:files){
            String fileName=file.getOriginalFilename();
            int type=0;
            if(handler instanceof GVMLAutoCaseConvert){
                type=2;
                if(!fileName.endsWith(".js")){
                    throw new AutoCaseRepertoryException("“"+fileName+"”的文件类型错误，GVML用例必须以‘.js’结尾");
                }
            }else if(handler instanceof PostManAutoCaseConvert){
                type=1;
                if(!fileName.endsWith(".json")){
                    throw new AutoCaseRepertoryException("“"+fileName+"”的文件类型错误，postman用例必须以‘.json’结尾");
                }
            }
            logger.info("开始解析自动化测试文件："+fileName);
            InputStream inputStream=file.getInputStream();
            String content=IOUtils.toString(inputStream,"utf-8");
            List<AutoCase> autoCases=new ArrayList<AutoCase>();
            try {
                autoCases.addAll(handler.stringToAutoCase(content));
            }catch (AutoCaseRepertoryException e){
                throw new AutoCaseRepertoryException("“"+fileName+"”文件解析出错，请检查文件内容是否正确");
            }
            logger.info("解析结束，得到的用例个数为："+autoCases.size());
            if(autoCases.size()==0){
                throw new AutoCaseRepertoryException("“"+fileName+"”文件中不包含任何自动化测试用例");
            }
            Date currentTime=new Date();
            User user=userDao.selectUserByName(SecurityUtils.getSubject().getPrincipal().toString());
            for(AutoCase autoCase:autoCases){
                autoCase.setType(type);
                autoCase.setWriter(name);
                autoCase.setUploaderId(user.getId());
                autoCase.setTime(currentTime);
                autoCase.setVersion(planName);
                autoCase.setNode(node);
                autoCase.setUpdateReason(updateReason);
            }
            autoCaseDao.insertsAutoCases(autoCases);
        }
    }

    public List<AutoCase> searchAutoCase(Map<String, String> jsonMap) throws CaseAnalysesException {
        List<AutoCase> list=new ArrayList<AutoCase>();
        String node=jsonMap.get("node");
        String planName=jsonMap.get("planName");
        List<String> nodes=findAllCNode(node,planName);
        AutoCase autoCase=new AutoCase();
        for(int i=0;i<nodes.size();i++){
            autoCase.setNode(nodes.get(i));
            autoCase.setVersion(planName);
            list.addAll(autoCaseDao.selectAutoCases(autoCase));
        }
//        /**list根据id去重复*/
//        List<AutoCase> list1=new ArrayList<AutoCase>();
//        for(AutoCase autoCase1:list){
//            for(AutoCase autoCase2:list1){
//                if(autoCase1.getId()==autoCase2.getId()){
//                    break;
//                }
//                list1.add(autoCase1);
//            }
//        }
        return list;
    }

    private List<String> findAllCNode(String node,String planName) throws CaseAnalysesException {
        List<String> list=new ArrayList<String>();
        if(node.contains(",")){
            String[] nodes=node.split(",");
            for(String nodeEach:nodes){
                if(!list.contains(nodeEach)){
                    list.add(nodeEach);
                }
                List<String> cNodes=caseAnalyseUtil.getAllDeepCNode(planName,nodeEach);
                for(String cNode:cNodes){
                    if(!list.contains(cNode)){
                        list.add(cNode);
                    }
                }
            }
        }else {
            List<String> cNodes=caseAnalyseUtil.getAllDeepCNode(planName,node);
            list.add(node);
            list.addAll(cNodes);
        }
        return list;
    }


    public List<AutoCaseHelper> convertToAutoCaseHelper(List<AutoCase> autoCases) throws CaseAnalysesException {
        List<AutoCaseHelper> list=new ArrayList<AutoCaseHelper>();
        if(autoCases==null||autoCases.size()==0){
            return list;
        }
        for(AutoCase autoCase:autoCases){
            AutoCaseHelper autoCaseHelper=new AutoCaseHelper();
            autoCaseHelper.setId(autoCase.getId().toString());
            autoCaseHelper.setCaseId(autoCase.getCase_id());
            autoCaseHelper.setDescribe(autoCase.getDescribes());
            autoCaseHelper.setNode(caseAnalyseUtil.selectNodeNameById(autoCase.getVersion(),autoCase.getNode()));
            list.add(autoCaseHelper);
        }
        return list;
    }

    public int checkAutoCaseType(String ids) {
        int type=0;
        if(ids==null&&ids.equals("")){
            return type;
        }
        List<Long> idLongs=new ArrayList<Long>();
        try{
            idLongs.addAll(JSON.parseArray(ids,Long.class));
        }catch (Exception e){
            logger.warn("checkAutoCaseType时，解析ids出错，ids："+ids);
        }
        if(idLongs==null||idLongs.size()==0){
            return type;
        }
        List<Integer> autoCaseTypes=autoCaseDao.selectAutoCaseTypes(idLongs);
        for(Integer autoCaseType:autoCaseTypes){
            if(type==0){
                type=autoCaseType;
            }else if(type!=autoCaseType){
                type=0;
                break;
            }
        }
        return type;
    }

    public String getPlanName(String ids) {
        List<Long> idLongs=JSON.parseArray(ids,Long.class);
        AutoCase autoCase=autoCaseDao.selectById(idLongs.get(0));
        return autoCase.getVersion();
    }

    public String saveGVMLExecutePlan(String strategy, String sendToTestlink, String before, String statement, String planName,String ids) {
        int isSendToTestlink;
        if(sendToTestlink==null){
            isSendToTestlink=0;
        }else {
            isSendToTestlink=1;
        }
        if(planName.equals("")){
            planName=getPlanName(ids);
        }
        AutoCaseExec autoCaseExec=new AutoCaseExec();
        Map<String,String> map=new HashMap();
        map.put("before",before);
        map.put("strategy",strategy);
        autoCaseExec.setContent(JSON.toJSONString(map));
        User user=userDao.selectUserByName(SecurityUtils.getSubject().getPrincipal().toString());
        autoCaseExec.setCreate_id(user.getId());
        autoCaseExec.setIsSendToTestlink(isSendToTestlink);
        autoCaseExec.setStatement(statement);
        autoCaseExec.setPlanname(planName);
        autoCaseExec.setIds(ids);
        autoCaseExecDao.insert(autoCaseExec);
        Long id=autoCaseExec.getId();
        return ShiroFilterUtils.encryptPassword(id.toString());
    }

    @Transactional
    public String createGVMLTestScript(String key) throws AutoCaseRepertoryException {
        if(key==null&&key.equals("")){
            throw new AutoCaseRepertoryException("输入的key为空！");
        }
        Long maxId=autoCaseExecDao.selectMaxId();
        if(maxId==null){
            throw new AutoCaseRepertoryException("当前不存在任何测试方案！");
        }
        Long id=0l;
        for(Long i=maxId;i>0l;i--){
            if(ShiroFilterUtils.encryptPassword(i.toString()).equals(key)){
                id=i;
                break;
            }
        }
        if(id==0l){
            throw new AutoCaseRepertoryException("不存在该测试方案！");
        }
        AutoCaseExec autoCaseExec=autoCaseExecDao.selectById(id);
        String script="window.arrglobal=[];window.waitTimeGlobal=0;";
        if(autoCaseExec.getIsSendToTestlink()==1){
            script+="window.isSendToTestlink=true;";
            script+="window.address=\"192.168.4.173:8085\";";
            script+="window.planname=\""+autoCaseExec.getPlanname()+"\";";
        }else if(autoCaseExec.getIsSendToTestlink()==0){
            script+="window.isSendToTestlink=false;";
            script+="window.planname=\"\";";
        }
        String content=autoCaseExec.getContent();
        Map<String,String> map=JSON.parseObject(content,Map.class);
        String before=map.get("before");
        script+=before+"\n";
        String strategy=map.get("strategy");
        String ids=autoCaseExec.getIds();
        List<Long> autoCaseIds=JSON.parseArray(ids,Long.class);
        String atCases="";
        String mtCases="";
        for(Long autoCaseId:autoCaseIds){
            AutoCase autoCase=autoCaseDao.selectById(autoCaseId);
            String caseContent=autoCase.getContent();
            script+=caseContent;
            int begin=caseContent.indexOf("function");
            int end=caseContent.indexOf("{",begin+8);
            if(caseContent.indexOf("showAtCase(")>=0){
                atCases+=caseContent.substring(begin+8,end).trim()+";";
            }else if(caseContent.indexOf("showMtCase(")>=0){
                mtCases+=caseContent.substring(begin+8,end).trim()+";";
            }
        }
        if(strategy.equals("0")){
            script+=atCases;
            script+=mtCases;
        }else if(strategy.equals("1")){
            script+=mtCases;
            script+=atCases;
        }
        return script;
    }
}
