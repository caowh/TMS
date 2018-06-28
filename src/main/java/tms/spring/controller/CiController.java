package tms.spring.controller;


import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by user on 2017/7/14.
 */
@Controller
@RequestMapping("/ci")
public class CiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * ci代码提交实时监控查询页面
     */
    @RequestMapping(value="changes")
    public String ciChange(Model model) {
        String username= SecurityUtils.getSubject().getPrincipal().toString();
        model.addAttribute("username", username);
        return "changes";
    }

    /**
     * 单次ci代码提交实时监控页面
     */
    @RequestMapping(value="change")
    public String ciChange(@RequestParam("change_id") String change_id,Model model) {
        model.addAttribute("change_id",change_id);
        return "change";
    }
}
