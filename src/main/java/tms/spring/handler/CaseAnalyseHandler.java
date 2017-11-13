package tms.spring.handler;

import tms.spring.exception.CaseAnalysesException;

import java.util.Map;

/**
 * Created by user on 2017/8/28.
 */
public interface CaseAnalyseHandler {

    Map<String,Object> analyse(Map<String,String> map) throws CaseAnalysesException;
}
