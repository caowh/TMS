package tms.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by user on 2017/7/14.
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        logger.info("open index page!");
        return "index";
    }


    @RequestMapping(value = "loginTest", method = RequestMethod.GET)
    public String loginTest(Model model) {
        logger.info("open index loginTest page!");
        return "/view/index1";
    }
}
