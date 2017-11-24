package tms.spring.service;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tms.spring.dao.AutoCaseDao;
import tms.spring.dao.AutoCaseExecDao;
import tms.spring.dao.PostManAssoDao;
import tms.spring.dao.UserDao;
import tms.spring.entity.*;
import tms.spring.exception.AutoCaseRepertoryException;
import org.apache.commons.io.IOUtils;
import tms.spring.exception.CaseAnalysesException;
import tms.spring.handler.AutoCaseConvertHandler;
import tms.spring.handler.impl.GVMLAutoCaseConvert;
import tms.spring.handler.impl.PostManAutoCaseConvert;
import tms.spring.shiro.filter.ShiroFilterUtils;
import tms.spring.utils.*;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 2017/11/8.
 */
@Service
public class AutoCaseRepertoryService {

    /***
     * type取值 1：postman方式解析    2：gvml方式解析
     * 修改存储与查找用例时的planName为planId
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;

    @Autowired
    private AutoCaseDao autoCaseDao;

    @Autowired
    private AutoCaseExecDao autoCaseExecDao;

    @Autowired
    private PostManAssoDao postManAssoDao;

    @Autowired
    private CaseAnalyseUtil caseAnalyseUtil;

    public void upload(HttpServletRequest request) throws AutoCaseRepertoryException, IOException {
        String type=request.getParameter("type");
        if(type.equals("1")){
            savePostManAutoCase(request);
        }else if(type.equals("2")){
            saveGVMLAutoCase(request);
        }else{
            throw new AutoCaseRepertoryException("用例的类型不支持");
        }
    }

    @Transactional
    private void saveGVMLAutoCase(HttpServletRequest request) throws IOException, AutoCaseRepertoryException {
        String name=request.getParameter("name");
        String updateReason=request.getParameter("updateReason");
        String node=request.getParameter("node");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files=multipartRequest.getFiles("multipartFiles");
        for(MultipartFile file:files){
            String fileName=file.getOriginalFilename();
            logger.info("开始解析自动化测试文件："+fileName);
            InputStream inputStream=file.getInputStream();
            String content=IOUtils.toString(inputStream,"utf-8");
            List<AutoCase> autoCases=new ArrayList<AutoCase>();
            try {
                autoCases.addAll(new GVMLAutoCaseConvert().stringToAutoCase(content));
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
                autoCase.setType(2);
                if(!autoCase.getContent().contains("Roujiamo")){
                    String newContent= convertToNewGVMLAutoCaseContent(autoCase.getContent(),autoCase.getCase_id());
                    autoCase.setContent(newContent);
                }else {
                    autoCase.setContent(autoCase.getContent());
                }
                autoCase.setWriter(name);
                autoCase.setUploaderId(user.getId());
                autoCase.setTime(currentTime);
                autoCase.setVersion(String.valueOf(Constant.PROJECT_ID));
                autoCase.setNode(node);
                autoCase.setUpdateReason(updateReason);
            }
            autoCaseDao.insertsAutoCases(autoCases);
        }
    }

    @Transactional
    private void savePostManAutoCase(HttpServletRequest request) throws AutoCaseRepertoryException, IOException {
        String name=request.getParameter("name");
        String updateReason=request.getParameter("updateReason");
        String node=request.getParameter("node");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files=multipartRequest.getFiles("multipartFiles");
        List<MultipartFile> envFiles=multipartRequest.getFiles("envFile");
        List<MultipartFile> readMeFiles=multipartRequest.getFiles("readMeFile");
        Long time=new Date().getTime();
        String rootPath=request.getSession().getServletContext().getRealPath(File.separator)+"useFile"+File.separator+time+File.separator;
        PostManAsso postManAsso=new PostManAsso();
        if(envFiles.get(0).getBytes().length>0){
            MultipartFile envFile=envFiles.get(0);
            FileUtil.saveFile(envFile.getInputStream(),envFile.getOriginalFilename(),rootPath);
            postManAsso.setEnv_url(rootPath+envFile.getOriginalFilename());
        }
        if(readMeFiles.get(0).getBytes().length>0){
            MultipartFile readMeFile=readMeFiles.get(0);
            FileUtil.saveFile(readMeFile.getInputStream(),readMeFile.getOriginalFilename(),rootPath);
            postManAsso.setReadme_url(rootPath+readMeFile.getOriginalFilename());
        }
        Long tpm_id = null;
        if(postManAsso.getEnv_url()!=null||postManAsso.getReadme_url()!=null){
            postManAssoDao.insert(postManAsso);
            tpm_id=postManAsso.getId();
        }
        for(MultipartFile file:files){
            String fileName=file.getOriginalFilename();
            logger.info("开始解析自动化测试文件："+fileName);
            InputStream inputStream=file.getInputStream();
            String content=IOUtils.toString(inputStream,"utf-8");
            List<AutoCase> autoCases=new ArrayList<AutoCase>();
            try {
                autoCases.addAll(new PostManAutoCaseConvert().stringToAutoCase(content));
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
                autoCase.setType(1);
                autoCase.setContent(autoCase.getContent());
                autoCase.setWriter(name);
                autoCase.setUploaderId(user.getId());
                autoCase.setTime(currentTime);
                autoCase.setVersion(String.valueOf(Constant.PROJECT_ID));
                autoCase.setNode(node);
                autoCase.setUpdateReason(updateReason);
                if(tpm_id!=null){
                    autoCase.setTpm_id(tpm_id);
                }
            }
            autoCaseDao.insertsAutoCases(autoCases);
        }
    }

    public List<AutoCase> searchAutoCase(String node) throws CaseAnalysesException {
        List<AutoCase> list=new ArrayList<AutoCase>();
        List<String> nodes=findAllCNode(node);
        AutoCase autoCase=new AutoCase();
        autoCase.setVersion(String.valueOf(Constant.PROJECT_ID));
        for(int i=0;i<nodes.size();i++){
            autoCase.setNode(nodes.get(i));
            List<AutoCase> autoCases=autoCaseDao.selectAutoCases(autoCase);
            if(autoCases!=null&&autoCases.size()>0){
                list.addAll(autoCases);
            }
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

    private List<String> findAllCNode(String node) throws CaseAnalysesException {
        TreeUtil treeUtil=new TreeUtil(caseAnalyseUtil.getProjectTree());
        List<String> cNodeList=new ArrayList<String>();
        if(node.contains(",")){
            String[] nodes=node.split(",");
            for(String nodeEach:nodes){
                if(!cNodeList.contains(nodeEach)){
                    cNodeList.add(nodeEach);
                }
                treeUtil.getDeepChildNode(cNodeList,Integer.parseInt(nodeEach));
            }
        }else {
            treeUtil.getDeepChildNode(cNodeList,Integer.parseInt(node));
            cNodeList.add(node);
        }
        return cNodeList;
    }


    public List<AutoCaseHelper> convertToAutoCaseHelper(List<AutoCase> autoCases) throws CaseAnalysesException {
        List<AutoCaseHelper> list=new ArrayList<AutoCaseHelper>();
        if(autoCases==null||autoCases.size()==0){
            return list;
        }
        List<TreeNode> treeNodes=caseAnalyseUtil.getProjectTree();
        for(AutoCase autoCase:autoCases){
            AutoCaseHelper autoCaseHelper=new AutoCaseHelper();
            autoCaseHelper.setId(autoCase.getId().toString());
            autoCaseHelper.setCaseId(autoCase.getCase_id());
            autoCaseHelper.setDescribe(autoCase.getDescribes());
            autoCaseHelper.setNode(caseAnalyseUtil.selectNodeNameById(autoCase.getNode(),treeNodes));
            autoCaseHelper.setContent(autoCase.getContent());
            autoCaseHelper.setUpdateReason(autoCase.getUpdateReason());
            autoCaseHelper.setWriter(autoCase.getWriter());
            autoCaseHelper.setUploader(userDao.selectUserById(autoCase.getUploaderId()).getUsername());
            autoCaseHelper.setVersion(caseAnalyseUtil.selectNodeNameById(autoCase.getVersion(),treeNodes));
            if(autoCase.getType()==1){
                autoCaseHelper.setType("postman");
            }else if(autoCase.getType()==2){
                autoCaseHelper.setType("GVML");
            }
            Date date=autoCase.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            autoCaseHelper.setTime(dateString);
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


    public String saveGVMLExecutePlan(String strategy, String sendToTestlink, String before, String statement, String planName,String ids) throws AutoCaseRepertoryException {
        int isSendToTestlink;
        if(sendToTestlink==null){
            isSendToTestlink=0;
            if(planName==null){
                planName="";
            }
        }else {
            isSendToTestlink=1;
            if(planName==null||planName.equals("")){
                throw new AutoCaseRepertoryException("要发送的计划名称不存在！");
            }
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
        Long id = getExecuteId(key);
        AutoCaseExec autoCaseExec=autoCaseExecDao.selectById(id);
        String script="window.arrglobal=[];window.waitTimeGlobal=0;window.arrCasePrepareRun=[];";
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
        return script+"ResultTotestlink()";
    }

    public byte[] createPostManTest(HttpServletRequest request) throws AutoCaseRepertoryException, IOException {
        Long time=new Date().getTime();
        String key=request.getParameter("key");
        Long id = getExecuteId(key);
        AutoCaseExec autoCaseExec=autoCaseExecDao.selectById(id);
        String ids=autoCaseExec.getIds();
        String planName=autoCaseExec.getPlanname();
        String statement=autoCaseExec.getStatement();
        String content=autoCaseExec.getContent();
        String runScript="CALL newman run testCases.json  --disable-unicode  --insecure";
        Map<String,String> runCond=JSON.parseObject(content,Map.class);
        if(runCond.get("redirect").equals("0")){
            runScript+=" --ignore-redirects";
        }
        if(runCond.get("bail").equals("1")){
            runScript+=" --bail";
        }
        runScript+=" --timeout-request "+runCond.get("waitTime");
        int isSendToTestlink=autoCaseExec.getIsSendToTestlink();
        List<Long> autoCaseIds=JSON.parseArray(ids,Long.class);
        List<Long> tpm_ids=new ArrayList<Long>();
        List<AutoCase> autoCaseNos=new ArrayList();
        Map<Long,List<AutoCase>> listMap=new HashMap<Long, List<AutoCase>>();
        for(Long autoCaseId:autoCaseIds){
            AutoCase autoCase=autoCaseDao.selectById(autoCaseId);
            Long tmp_id=autoCase.getTpm_id();
            if(tmp_id==null){
                autoCaseNos.add(autoCase);
            }else if (!tpm_ids.contains(tmp_id)){
                tpm_ids.add(tmp_id);
                List list=new ArrayList();
                list.add(autoCase);
                listMap.put(tmp_id,list);
            }else {
                List list=listMap.get(tmp_id);
                list.add(autoCase);
                listMap.put(tmp_id,list);
            }
        }
        String rootPath=request.getSession().getServletContext().getRealPath(File.separator)+"cacheFile"+File.separator;
        int count=0;
        if(autoCaseNos.size()>0){
            count++;
            zipPMCase(autoCaseNos,planName,statement,rootPath+time+File.separator,null,isSendToTestlink,runScript);
        }
        for(Long tpm_id:tpm_ids){
            count++;
            zipPMCase(listMap.get(tpm_id),planName,statement,rootPath+time+File.separator,tpm_id,isSendToTestlink,runScript);
        }
        if(count==1){
            File file=new File(rootPath+time).listFiles()[0];
            byte[] bytes=FileUtil.readFile(file);
            FileUtil.deleteDir(new File(rootPath+time));
            return bytes;
        }else if(count>0){
            try {
                String zipFileName=rootPath+"postmanAutoCase"+"_"+time+"_"+statement+".zip";
                FileUtil.zip(zipFileName,rootPath+time);
                FileUtil.deleteDir(new File(rootPath+time));
                File file=new File(zipFileName);
                byte[] bytes=FileUtil.readFile(file);
                file.delete();
                return bytes;
            } catch (Exception e) {
                throw new AutoCaseRepertoryException(e.getMessage());
            }
        }else {
            throw new AutoCaseRepertoryException("未能生成任何压缩包！");
        }
    }

    private void zipPMCase(List<AutoCase> autoCases, String planName, String statement,String path, Long tpm_id,int isSendToTestlink,String runScript) throws AutoCaseRepertoryException {
        Map<String,Object> caseMap=new HashMap<String, Object>();
        Long time=new Date().getTime();
        if(planName==null||planName.equals("")){
            planName=String.valueOf(time);
        }
        caseMap.put("name",planName);
        List<Map> requests=new ArrayList<Map>();
        String id=null;
        for(AutoCase autoCase:autoCases){
            String content=autoCase.getContent();
            Map map=JSON.parseObject(content,Map.class);
            if(id==null){
                id=map.get("collectionId").toString();
            }
            requests.add(map);
        }
        caseMap.put("id",id);
        caseMap.put("requests",requests);
        ByteArrayInputStream stream = new ByteArrayInputStream(JSON.toJSONBytes(caseMap));
        FileUtil.saveFile(stream,"testCases.json",path+time);
        if(tpm_id!=null){
            PostManAsso postManAsso=postManAssoDao.selectById(tpm_id);
            String readmeUrl=postManAsso.getReadme_url();
            String envUrl=postManAsso.getEnv_url();
            if(readmeUrl!=null){
                try {
                    FileUtil.saveFile(new FileInputStream(new File(readmeUrl)),"readme.txt",path+time);
                } catch (FileNotFoundException e) {
                    throw new AutoCaseRepertoryException("找不到文件："+readmeUrl);
                }
            }
            if(envUrl!=null){
                try {
                    FileUtil.saveFile(new FileInputStream(new File(envUrl)),"env.json",path+time);
                    runScript+=" --environment env.json";
                } catch (FileNotFoundException e) {
                    throw new AutoCaseRepertoryException("找不到文件："+envUrl);
                }
            }
        }
        try {
            if(isSendToTestlink==1){
                runScript+=" --reporters cli,html,junit --reporter-junit-export result/xmlOut.xml --reporter-html-export result/htmlOutput.html";
                runScript+="\npython testLink.py";
                String testLinkPath=path+time+File.separator+"testLink.py";
                new File(testLinkPath).createNewFile();
                String testLinkContent="# coding=utf-8\n" +
                        "from xml.etree import ElementTree as ET\n" +
                        "import os\n" +
                        "import json\n" +
                        "import requests\n" +
                        "\n" +
                        "result_file = os.getcwd() + os.path.sep + \"result\" + os.path.sep + \"xmlOut.xml\"\n" +
                        "if os.path.exists(result_file):\n" +
                        "    print '开始将测试结果发送至testlink......'.decode('utf-8').encode('gbk')\n" +
                        "    root = ET.parse(result_file)\n" +
                        "    return_plans = []\n" +
                        "    plan_name = root.getroot().get('name').encode(\"utf-8\")\n" +
                        "    testcases = root.getiterator(\"testcase\")\n" +
                        "    return_data = []\n" +
                        "    return_plan = {}\n" +
                        "    for testcase in testcases:\n" +
                        "        info = testcase.get('name').encode(\"utf-8\")\n" +
                        "        arr = info.split('@', 1)\n" +
                        "        return_case = {\"caseid\": arr[0], \"message\": arr[1]}\n" +
                        "        if len(testcase.getchildren()) == 0:\n" +
                        "            return_case[\"result\"] = \"p\"\n" +
                        "        else:\n" +
                        "            return_case[\"result\"] = \"f\"\n" +
                        "        return_data.append(return_case)\n" +
                        "    return_plan[\"planname\"] = plan_name\n" +
                        "    return_plan[\"data\"] = return_data\n" +
                        "    return_plans.append(return_plan)\n" +
                        "\n" +
                        "    url = 'http://192.168.4.173:8085/api/v1/testlinkservice/fillAllResult'\n" +
                        "    r = requests.post(url, data=json.dumps(return_plans), headers={'Content-Type': 'application/json'})\n" +
                        "    if r.status_code == 200:\n" +
                        "        f = file(os.getcwd() + os.path.sep + \"result\" + os.path.sep +'filltestlinkResult.json', 'w')\n" +
                        "        content = r.content\n" +
                        "        json.dumps(content)\n" +
                        "        f.write(content)\n" +
                        "        f.close()\n" +
                        "        result = eval(content)[0]\n" +
                        "        print \"发送完毕\".decode('utf-8').encode('gbk')\n" +
                        "        print \"计划名称:\".decode('utf-8').encode('gbk')+result['planname']\n" +
                        "        total = 0\n" +
                        "        success = 0\n" +
                        "        failed = 0\n" +
                        "        for case in result['data']:\n" +
                        "            print case['caseid']+\":\"+case['result']\n" +
                        "            total = total+1\n" +
                        "            if case['result'] == 'success':\n" +
                        "                success = success+1\n" +
                        "            elif case['result'] == 'failed':\n" +
                        "                failed = failed+1\n" +
                        "        print \"总数：\".decode('utf-8').encode('gbk')+str(total)+\"，失败：\".decode('utf-8').encode('gbk')+str(failed)+\\\n" +
                        "              \"，成功：\".decode('utf-8').encode('gbk')+str(success)\n" +
                        "    else:\n" +
                        "        print \"发送失败，请确认服务器是否开启\".decode('utf-8').encode('gbk')\n";
                FileUtil.writeStringToFile(testLinkPath,testLinkContent);
            }else {
                runScript+=" --reporters cli,html,junit --reporter-html-export result/htmlOutput.html";
            }
            runScript+="\npause";
            String runPath=path+time+File.separator+"run.bat";
            new File(runPath).createNewFile();
            FileUtil.writeStringToFile(runPath,runScript);
            new File(path+time+File.separator+"result").mkdir();
            FileUtil.zip(path+"postmanAutoCase"+"_"+time+"_"+statement+".zip",path+time);
            Boolean bl=FileUtil.deleteDir(new File(path+time));
            if(!bl){
                throw new AutoCaseRepertoryException("清空过程文件失败");
            }
        }catch (Exception e){
            throw new AutoCaseRepertoryException("生成postman下载包失败，"+e.getMessage());
        }
    }

    private Long getExecuteId(String key) throws AutoCaseRepertoryException {
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
        return id;
    }

    public AutoCaseHelper searchAutoCaseById(String id) throws AutoCaseRepertoryException, CaseAnalysesException {
        if(id==null||id.equals("")){
            throw new AutoCaseRepertoryException("输入的id为空！");
        }
        AutoCase autoCase=autoCaseDao.selectById(Long.parseLong(id));
        if(autoCase==null){
            throw new AutoCaseRepertoryException("id不存在！");
        }
        List<AutoCase> autoCases=new ArrayList<AutoCase>();
        autoCases.add(autoCase);
        return convertToAutoCaseHelper(autoCases).get(0);
    }


    public void updateGVMLAutoCase(String caseId, String describe, String content, String updateReason,String id) throws AutoCaseRepertoryException {
        AutoCase autoCase=autoCaseDao.selectById(Long.parseLong(id));
        String username=SecurityUtils.getSubject().getPrincipal().toString();
        if(!autoCase.getWriter().equals(username)){
            if(!username.equals("admin")){
                throw new AutoCaseRepertoryException("你无权修改此用例！");
            }
        }
        AutoCaseConvertHandler handler=new GVMLAutoCaseConvert();
        List<AutoCase> autoCases=handler.stringToAutoCase(content);
        if(autoCases==null||autoCases.size()!=1){
            throw new AutoCaseRepertoryException("修改的内容检测不通过！");
        }
        if(!autoCases.get(0).getCase_id().equals(caseId)){
            content=content.replaceAll(autoCases.get(0).getCase_id(),caseId);
            autoCases.get(0).setCase_id(caseId);
        }
        if(!autoCases.get(0).getDescribes().equals(describe)){
            content=content.replaceAll(autoCases.get(0).getDescribes(),describe);
            autoCases.get(0).setDescribes(describe);
        }
        autoCases.get(0).setUpdateReason(updateReason);
        autoCases.get(0).setContent(content);
        autoCases.get(0).setId(Long.parseLong(id));
        autoCaseDao.updateAutoCase(autoCases.get(0));
    }

    public String convertToNewGVMLAutoCaseContent(String content, String case_id) throws AutoCaseRepertoryException {
        try{
            String functionName=content.substring(content.indexOf("function")+8,content.indexOf("()")).trim();
            content=content.replaceFirst("setTimeout","\nvar functionName='"+functionName+"';\nvar id=setTimeout");
            content=content.substring(0,content.length()-1)+"\npushBefore(functionName,id)\n"+"}\n";
            content=content.replaceAll("showAtCase","Roujiamo.showAtCase");
            content=content.replaceAll("showMtCase","Roujiamo.showMtCase");
            content=content.replaceAll("hideCase","Roujiamo.hideCase");
            content=content.replaceAll("showCaseResult","Roujiamo.showCaseResult");
            content=content.replaceAll("getCaseResult","Roujiamo.getCaseResult");
            content=content.replaceAll("addCaseToArrGlobal","Roujiamo.addCaseToArrGlobal");

            content=content.replaceAll("assertEqual","Roujiamo.assertEqual");
            content=content.replaceAll("assertNotNull","Roujiamo.assertNotNull");
            content=content.replaceAll("assertVp","Roujiamo.assertVp");
            content=content.replaceAll("assertFr","Roujiamo.assertFr");
            content=content.replaceAll("assertFrNotNull","Roujiamo.assertFrNotNull");
            content=content.replaceAll("assertISNull","Roujiamo.assertISNull");
            content=content.replaceAll("assertNotEqual","Roujiamo.assertNotEqual");
            content=content.replaceAll("assertObjEqual","Roujiamo.assertObjEqual");

            content=content.replace("var describe","removeFirst(functionName);\nvar describe");
        }catch (Exception e){
            throw new AutoCaseRepertoryException("GVML旧用例转化为新用例时出错，用例ID为："+case_id);
        }
        return content;
    }


    @Transactional
    public void deleteAutoCase(String ids) throws AutoCaseRepertoryException {
        if(ids==null&&ids.equals("")){
            throw new AutoCaseRepertoryException("删除的ids为空！");
        }
        List<Long> idLongs=new ArrayList<Long>();
        try{
            idLongs.addAll(JSON.parseArray(ids,Long.class));
        }catch (Exception e){
            throw new AutoCaseRepertoryException("输入的ids不合法，ids:"+ids);
        }
        if(idLongs.size()==0){
            throw new AutoCaseRepertoryException("输入的ids为空！");
        }
        String username=SecurityUtils.getSubject().getPrincipal().toString();
        for(Long id:idLongs){
            AutoCase autoCase=autoCaseDao.selectById(id);
            if(!autoCase.getWriter().equals(username)){
                if(!username.equals("admin")){
                    throw new AutoCaseRepertoryException("你无权删除此用例，用例ID："+autoCase.getCase_id());
                }
            }
            autoCaseDao.deleteAutoCase(id);
        }
    }


    @Transactional
    public void moveAutoCase(String ids,String nodeName) throws AutoCaseRepertoryException {
        if(ids==null&&ids.equals("")){
            throw new AutoCaseRepertoryException("移动的ids为空！");
        }
        List<Long> idLongs=new ArrayList<Long>();
        try{
            idLongs.addAll(JSON.parseArray(ids,Long.class));
        }catch (Exception e){
            throw new AutoCaseRepertoryException("输入的ids不合法，ids:"+ids);
        }
        if(idLongs.size()==0){
            throw new AutoCaseRepertoryException("输入的ids为空！");
        }
        String username=SecurityUtils.getSubject().getPrincipal().toString();
        for(Long id:idLongs){

            AutoCase autoCase=autoCaseDao.selectById(id);
            if(!autoCase.getWriter().equals(username)){
                if(!username.equals("admin")){
                    throw new AutoCaseRepertoryException("你无权移动此用例，用例ID："+autoCase.getCase_id());
                }
            }
            autoCase.setNode(nodeName);
            autoCaseDao.moveAutoCase(autoCase);
        }
    }

    public String savePMExecutePlan(HttpServletRequest request) {
        AutoCaseExec autoCaseExec=new AutoCaseExec();
        Map<String,String> map=new HashMap();

        int redirect=( request.getParameter("redirect") == null )? 0 : 1;
        map.put("redirect",String.valueOf(redirect));

        int bail=( request.getParameter("bail") == null )? 0 : 1;
        map.put("bail",String.valueOf(bail));

        String waitTime=request.getParameter("waitTime");
        map.put("waitTime",waitTime);

        int sendToTestlink=( request.getParameter("sendToTestlink") == null )? 0 : 1;
        String statement=request.getParameter("statement");
        String ids=request.getParameter("ids");
        String planName=request.getParameter("planName");
        autoCaseExec.setIds(ids);
        autoCaseExec.setPlanname(planName);
        autoCaseExec.setStatement(statement);
        autoCaseExec.setIsSendToTestlink(sendToTestlink);
        User user=userDao.selectUserByName(SecurityUtils.getSubject().getPrincipal().toString());
        autoCaseExec.setCreate_id(user.getId());
        autoCaseExec.setContent(JSON.toJSONString(map));
        autoCaseExecDao.insert(autoCaseExec);
        Long id=autoCaseExec.getId();
        return ShiroFilterUtils.encryptPassword(id.toString());
    }
}
