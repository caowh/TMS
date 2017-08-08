package tms.spring.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by user on 2017/8/8.
 */
public class HttpRequestUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);    //日志记录

    /**

     * post请求

     * @param url         url地址

     * @param jsonParam     参数

     * @param noNeedResponse    不需要返回结果

     * @return

     */

    public static <T> T httpPost(String url,JSONObject jsonParam, Class<T> clazz,boolean noNeedResponse){

        //post请求返回结果

        CloseableHttpClient httpClient = HttpClients.createDefault();

        T jsonResult = null;

        HttpPost method = new HttpPost(url);

        try {

            if (null != jsonParam) {

                //解决中文乱码问题

                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");

                entity.setContentEncoding("UTF-8");

                entity.setContentType("application/json");

                method.setEntity(entity);

            }

            CloseableHttpResponse result=httpClient.execute(method);

            url = URLDecoder.decode(url, "UTF-8");

            /**请求发送成功，并得到响应**/

            if (result.getStatusLine().getStatusCode() == 200) {

                String str = "";
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    str = EntityUtils.toString(result.getEntity());

                    if (noNeedResponse) {

                        return null;

                    }

                    /**把json字符串转换成json对象**/

                    jsonResult = JSON.parseObject(str,clazz);

                } catch (Exception e) {

                    logger.error("post请求提交失败:" + url, e);

                }

            }

        } catch (IOException e) {

            logger.error("post请求提交失败:" + url, e);

        }

        return jsonResult;

    }





    /**

     * 发送get请求

     * @param url    路径

     * @return

     */

    public static <T> T httpGet(String url, Class<T> clazz){

        //get请求返回结果

        T jsonResult = null;

        try {

            CloseableHttpClient httpClient = HttpClients.createDefault();

            //发送get请求

            HttpGet request = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(request);



            /**请求发送成功，并得到响应**/

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                /**读取服务器返回过来的json字符串数据**/

                String strResult = EntityUtils.toString(response.getEntity());

                /**把json字符串转换成json对象**/

                jsonResult = JSON.parseObject(strResult,clazz);

                url = URLDecoder.decode(url, "UTF-8");

            } else {

                logger.error("get请求提交失败:" + url);

            }

        } catch (IOException e) {

            logger.error("get请求提交失败:" + url, e);

        }

        return jsonResult;

    }
}
