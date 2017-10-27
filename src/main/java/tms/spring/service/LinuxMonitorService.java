package tms.spring.service;

import tms.spring.exception.LinuxMonitorException;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2017/10/16.
 */
public interface LinuxMonitorService {

    List getCpuUsage(String url) throws LinuxMonitorException;
    Map<String,String> getMemUsage(String url) throws LinuxMonitorException;
    Map<String,String> getTraffic(String url) throws LinuxMonitorException;
    List getDiskIo(String url) throws LinuxMonitorException;
}
