package tms.spring.handler;

import tms.spring.entity.AutoCase;
import tms.spring.exception.AutoCaseRepertoryException;

import java.util.List;

/**
 * Created by user on 2017/11/8.
 */
public interface AutoCaseConvertHandler {

    List<AutoCase> stringToAutoCase(String content) throws AutoCaseRepertoryException;
}
