package tms.spring.service.impl;

import tms.spring.exception.LinuxMonitorException;
import tms.spring.service.LinuxMonitorService;
import tms.spring.utils.HttpRequestUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/10/17.
 */
public class LinuxMonitorServiceByPydashImpl implements LinuxMonitorService{

    public List getCpuUsage(String url) throws LinuxMonitorException {
        return HttpRequestUtils.httpGet(url+"/info/cpuusage/",List.class);
    }

    public Map<String,String> getMemUsage(String url) throws LinuxMonitorException {
        return HttpRequestUtils.httpGet(url+"/info/memory/",Map.class);
    }

    public Map<String, String> getTraffic(String url) throws LinuxMonitorException {
        return HttpRequestUtils.httpGet(url+"/info/gettraffic/",Map.class);
    }

    public List getDiskIo(String url) throws LinuxMonitorException {
        return HttpRequestUtils.httpGet(url+"/info/getdiskio/",List.class);
    }
}
