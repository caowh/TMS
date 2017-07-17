package tms.spring.shiro;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import tms.spring.utils.INI4j;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ShiroManager {
	
	// 注意/r/n前不能有空格
	private static final String CRLF = "\r\n";
	private final Logger logger = LoggerFactory.getLogger(ShiroManager.class);

	@Resource
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;


	public String loadFilterChainDefinitions() {
		StringBuffer sb = new StringBuffer();
			sb.append(getFixedAuthRule());//固定权限，采用读取配置文件
		return sb.toString();
	}
	
	/**
	 * 从配额文件获取固定权限验证规则串
	 */
	private String getFixedAuthRule(){
		String fileName = "shiroBaseAuth.ini";
		ClassPathResource cp = new ClassPathResource(fileName);
		INI4j ini = null;
		try {
			ini = new INI4j(cp.getFile());
		} catch (IOException e) {
			logger.error("加载文件出错,"+fileName);
		}
		String section = "base_auth";
		Set<String> keys = ini.get(section).keySet();
		StringBuffer sb = new StringBuffer();
		for (String key : keys) {
			String value = ini.get(section, key);
			sb.append(key).append(" = ")
			.append(value).append(CRLF);
		}
		
		return sb.toString();

	}

	// 此方法加同步锁
	public synchronized void reCreateFilterChains() {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = (ShiroFilterFactoryBean) SpringContextUtil.getBean("shiroFilterFactoryBean");
		AbstractShiroFilter shiroFilter = null;
		try {
			shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
		} catch (Exception e) {
			logger.error("getShiroFilter from shiroFilterFactoryBean error!", e);
			throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
		}

		PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver();
		DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
				.getFilterChainManager();

		// 清空老的权限控制
		manager.getFilterChains().clear();

		shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
		shiroFilterFactoryBean.setFilterChainDefinitions(loadFilterChainDefinitions());
		// 重新构建生成
		Map<String, String> chains = shiroFilterFactoryBean
				.getFilterChainDefinitionMap();
		for (Map.Entry<String, String> entry : chains.entrySet()) {
			String url = entry.getKey();
			String chainDefinition = entry.getValue().trim().replace(" ", "");
			manager.createChain(url, chainDefinition);
		}

	}
	public void setShiroFilterFactoryBean(
			ShiroFilterFactoryBean shiroFilterFactoryBean) {
		this.shiroFilterFactoryBean = shiroFilterFactoryBean;
	}

}
