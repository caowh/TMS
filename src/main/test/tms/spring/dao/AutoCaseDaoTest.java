package tms.spring.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tms.spring.entity.AutoCase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by user on 2017/11/9.
 */
public class AutoCaseDaoTest extends BaseDaoTest{

    @Autowired
    private  AutoCaseDao autoCaseDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void insertsAutoCases() throws Exception {
//        AutoCase autoCase=new AutoCase();
//        autoCase.setType(0);
//        autoCase.setVersion("test");
//        autoCase.setCase_id("test");
//        autoCase.setContent("test");
//        autoCase.setDescribes("test");
//        autoCase.setUpdateReason("test");
//        autoCase.setTime(new Date());
//        autoCase.setUploaderId(userDao.selectUserByName("admin").getId());
//        autoCase.setWriter("test");
//        autoCase.setNode("0");
//        List<AutoCase> list=new ArrayList();
//        list.add(autoCase);
//        autoCaseDao.insertsAutoCases(list);
    }

    @Test
    public void selectAutoCases() throws Exception {
        AutoCase autoCase=new AutoCase();
        autoCase.setUploaderId(userDao.selectUserByName("admin").getId());
        autoCase.setVersion("test");
        autoCase.setNode("0");
        List<AutoCase> autoCases=autoCaseDao.selectAutoCases(autoCase);
        assertNotNull(autoCases.size());
    }

}