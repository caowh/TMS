package tms.spring.shiro.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ShiroFilterUtils {
	private final static Logger log = LoggerFactory.getLogger(ShiroFilterUtils.class);
	//登录页面
	static final String LOGIN_URL = "/login.jsp";
	//踢出登录提示
	final static String KICKED_OUT = "/kickedOut.jsp";
	//没有权限提醒
	final static String UNAUTHORIZED = "/unauthorized.jsp";
	/**
	 * 是否是Ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(ServletRequest request){
		return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
	}
	
	/**
	 * response 输出JSON
	 * @param hresponse
	 * @param resultMap
	 * @throws IOException
	 */
	public static void out(ServletResponse response, Map<String, String> resultMap){
		
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			out = response.getWriter();
			out.println(JSON.toJSONString(resultMap));
		} catch (Exception e) {
			log.error("输出json内容出错");
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
}
