package tms.spring.shiro.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends AccessControlFilter {
	private final  Logger log = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response, Object mappedValue) throws Exception {

		if(null != SecurityUtils.getSubject().getPrincipal() || isLoginRequest(request, response)){
            return Boolean.TRUE;
        } 
		if (ShiroFilterUtils.isAjax(request)) {
			Map<String,String> resultMap = new HashMap<String, String>();
			log.debug("当前用户没有登录，并且是Ajax请求！");
			resultMap.put("code", "0");
			resultMap.put("error", "当前用户没有登录");
			ShiroFilterUtils.out(response, resultMap);
		}
		return Boolean.FALSE ;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		//保存Request和Response 到登录后的链接
		saveRequestAndRedirectToLogin(request, response);
		return Boolean.FALSE ;
	}
	

}
