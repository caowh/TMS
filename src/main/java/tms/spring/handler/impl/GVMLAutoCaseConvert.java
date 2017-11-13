package tms.spring.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tms.spring.entity.AutoCase;
import tms.spring.exception.AutoCaseRepertoryException;
import tms.spring.handler.AutoCaseConvertHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/11/8.
 */
public class GVMLAutoCaseConvert implements AutoCaseConvertHandler{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<AutoCase> stringToAutoCase(String content) throws AutoCaseRepertoryException {
        List<AutoCase> list=new ArrayList<AutoCase>();
        if(content==null||content.equals("")){
            throw new AutoCaseRepertoryException("文件的内容为空");
        }
        convertEachCase(content, list);
        return list;
    }

    private void convertEachCase(String content, List<AutoCase> list) throws AutoCaseRepertoryException {
        int caseBegin=content.indexOf("function");
        int caseEnd=content.indexOf(",ownerWaitTime_");
        if(caseEnd<0){
            caseEnd=content.indexOf(", ownerWaitTime_");
        }
        if(caseBegin<0||caseEnd<0){
            logger.info("转化完毕，无法继续找到用例");
        }else {
            AutoCase autoCase=new AutoCase();
            String caseStr=content.substring(caseBegin+8,caseEnd);
            int caseIdEnd=caseStr.indexOf("()");
            String caseId=caseStr.substring(0,caseIdEnd).trim();
            caseId=caseId.substring(caseId.indexOf("_")+1,caseId.length()).trim();
            int caseDescribeBegin=caseStr.indexOf("\"");
            int caseDescribeEnd=caseStr.indexOf("\"",caseDescribeBegin+1);
            String caseDescribe=caseStr.substring(caseDescribeBegin+1,caseDescribeEnd).trim();
            int caseContentTrueEnd=content.indexOf("}",caseEnd);
            String caseContent=content.substring(caseBegin,caseContentTrueEnd+1).trim();
            autoCase.setCase_id(caseId);
            autoCase.setContent(caseContent);
            autoCase.setDescribes(caseDescribe);
            list.add(autoCase);
            String newContent=content.substring(caseContentTrueEnd+1,content.length()).trim();
            convertEachCase(newContent,list);
        }
    }

}
