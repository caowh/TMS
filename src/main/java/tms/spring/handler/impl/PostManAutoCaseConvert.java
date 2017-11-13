package tms.spring.handler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import tms.spring.entity.AutoCase;
import tms.spring.exception.AutoCaseRepertoryException;
import tms.spring.handler.AutoCaseConvertHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/11/8.
 */
public class PostManAutoCaseConvert implements AutoCaseConvertHandler{

    public List<AutoCase> stringToAutoCase(String content) throws AutoCaseRepertoryException {
        List<AutoCase> list=new ArrayList<AutoCase>();
        if(content==null||content.equals("")){
            throw new AutoCaseRepertoryException("内容为空");
        }
        try {
            Map<String,JSONArray> map= JSON.parseObject(content,Map.class);
            JSONArray mapList=map.get("requests");
            if(mapList==null||mapList.size()==0){
                return list;
            }
            for(Object caseObj:mapList){
                Map<String,String> caseMap=(Map<String, String>) caseObj;
                String caseTest=caseMap.get("tests");
                int caseIdBegin=caseTest.indexOf("tests[\"");
                if(caseIdBegin<0){
                    continue;
                }
                int caseIdEnd=caseTest.indexOf("@",caseIdBegin);
                if(caseIdEnd<0){
                    continue;
                }
                String caseId=caseTest.substring(caseIdBegin+7,caseIdEnd);
                String caseContent=JSON.toJSONString(caseMap);
                String caseDescribe=caseMap.get("name");
                AutoCase autoCase=new AutoCase();
                autoCase.setDescribes(caseDescribe);
                autoCase.setContent(caseContent);
                autoCase.setCase_id(caseId);
                list.add(autoCase);
            }
        }catch (Exception e){
            throw new AutoCaseRepertoryException("解析出错");
        }
        return list;
    }



}
