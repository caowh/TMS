package tms.spring.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tms.spring.exception.LinuxMonitorException;
import tms.spring.utils.RegExpValidatorUtils;

import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by user on 2017/10/26.
 */
public class LinuxMonitorHandler implements InvocationHandler {

    private Object targetObject;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String url=args[0].toString();
        if(RegExpValidatorUtils.IsUrl(url)){
            return method.invoke(targetObject, args);
        }else {
            logger.error("输入的url地址不规范，url:"+url);
            throw new LinuxMonitorException("请输入正确的url地址");
        }
    }
}
