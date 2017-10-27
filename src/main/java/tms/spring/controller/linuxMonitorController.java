package tms.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tms.spring.service.LinuxMonitorService;
import tms.spring.service.impl.LinuxMonitorHandler;
import tms.spring.service.impl.LinuxMonitorServiceByPydashImpl;
import tms.spring.utils.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2017/8/8.
 */
@Controller
@RequestMapping("/linuxMonitor")
public class linuxMonitorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private LinuxMonitorService linuxMonitorService=(LinuxMonitorService)new LinuxMonitorHandler().newProxyInstance(new LinuxMonitorServiceByPydashImpl());

    @RequestMapping(value = "index")
    public String index(Model model,@RequestParam("ip") String ip) {
        model.addAttribute("ip", ip);
        return "linuxMonitor";
    }

    @RequestMapping(value = "cpuUsage")
    @ResponseBody
    public Map<String, Object> cpuUsage(@RequestParam("url") String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("result",linuxMonitorService.getCpuUsage(url));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "memUsage")
    @ResponseBody
    public Map<String, Object> memUsage(@RequestParam("url") String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("result",linuxMonitorService.getMemUsage(url));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "traffic")
    @ResponseBody
    public Map<String, Object> traffic(@RequestParam("url") String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("result",linuxMonitorService.getTraffic(url));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "diskIo")
    @ResponseBody
    public Map<String, Object> diskIo(@RequestParam("url") String url) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("result",linuxMonitorService.getDiskIo(url));
            map.put("code", Constant.CODE_SUCCESS);
        } catch (Exception e){
            map.put("code",Constant.CODE_FAILED);
            map.put("message",e.getMessage());
        }
        return map;
    }
}
