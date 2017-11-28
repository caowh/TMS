package tms.spring.utils;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URL;


public class TestLinkUtil
{
	private TestLinkAPI api = null;

	public TestLinkUtil() throws Exception {
		init();
	}

	private void init() throws Exception {
		String url = "http://192.168.4.173/testlink-1.9.14/lib/api/xmlrpc/v1/xmlrpc.php";
		String devKey = "ade1298681dcb31122d2f1a22bbfd716";

		URL testLinkURL;

		try     {
			testLinkURL = new URL(url);
			api = new TestLinkAPI(testLinkURL, devKey);
		} catch ( MalformedURLException mue )   {
			throw new Exception("连接testlink失败");
		}
	}

	public void uploadCaseAttachment(MultipartFile file) throws Exception {
		String name=file.getOriginalFilename();
		int index=name.indexOf("_");
		String case_id;
		if(index>0){
			case_id=name.substring(0,name.indexOf("_"));
		}else {
			case_id=name.substring(0,name.lastIndexOf("."));
		}
		int fkId;
		try {
			fkId = api.getTestCaseByExternalId(case_id,null).getId();
		} catch (TestLinkAPIException e) {
			throw new Exception("找不到对应的用例，文件名称为："+name+"，解析后caseId为："+case_id+"；");
		}
		api.uploadAttachment(fkId, "nodes_hierarchy", null,
				null,name,null, Base64.encode(file.getBytes()).toString());
	}

//	public static void main(String[] args) throws Exception {
//		TestLinkUtil testLinkUtil =new TestLinkUtil();
//		testLinkUtil.api.getTestCaseByExternalId("GV5-5993",null);
//	}
}