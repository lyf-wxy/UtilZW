/**
 * <p>title:HttpClientUtil.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月4日下午9:08:43
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.networkCom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

/**  
 * 创建时间：2015年11月4日 下午9:08:43  
 * 项目名称：zwplatform   
 * 文件名称：HttpClientUtil.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月4日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: HttpClientUtil<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2015年11月4日
 */
public class HttpClientUtil {

	protected final static Logger logger = Logger.getLogger(HttpClientUtil.class);

	public static String httpPostJonson(String url, Map<String, String> params,
			boolean noNeedResponse)throws Exception{
		String response = null;
		
		return response;
	}
	/**
	 * <p>Title: httpPostString<／p>
	 * <p>Description: <／p>
	 * @param url
	 * @param params
	 * @param noNeedResponse
	 * @return
	 * @throws Exception
	 */
	public static String httpPostString(String url, Map<String, String> params,
			boolean noNeedResponse)throws Exception{
		String response = null;
		
		return response;
	}
	/**
	 * <p>
	 * Title: httpPost<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param url
	 * @param jsonParam
	 * @param noNeedResponse
	 * @return
	 */
	public static String httpPost(String url, Map<String, String> params, String charset)throws Exception {

		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 StringBuffer sb_response = new StringBuffer();
	        try {
	            HttpPost httppost = new HttpPost(url);
	            if ((params!=null) && (!params.isEmpty()) ) {
	            	List<NameValuePair> formparams = new ArrayList<NameValuePair>();
	            	for (Map.Entry<String, String> mapEntry : params.entrySet()) {
	            		//这里有null值得可能！！！
	            		formparams.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue()));
					}
	            	UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
	            	httppost.setEntity(entity);
				}
	            CloseableHttpResponse response = httpclient.execute(httppost);
	            try {
	            	HttpEntity entity = null;
	            
	                int status = response.getStatusLine().getStatusCode();
                    if (status == 200 || status == 302) {
                    	// Get hold of the response entity
                         entity = response.getEntity();
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }

	                // If the response does not enclose an entity, there is no need
	                // to bother about connection release
	                if (entity != null) {
	                	InputStreamReader inptStrRead = null;
	                	BufferedReader reader =null;
	                    try {
	                    	inptStrRead = new InputStreamReader(entity.getContent(),charset);
	                    	 reader = new BufferedReader(inptStrRead); 
                             String line;
                             while ((line = reader.readLine()) != null) { 
                            	 sb_response.append(line); 
                             } 
	                    } catch (IOException ex) {
	                        // In case of an IOException the connection will be released
	                        // back to the connection manager automatically
	                        throw ex;
	                    } finally {
	                        // Closing the input stream will trigger connection release
	                    	if (reader !=null) {
	                    		reader.close(); 
							}
	                    	if (inptStrRead!=null) {
	                    		inptStrRead.close();
							}
	                    	
	                    }
	                }else{
	                	throw new ClientProtocolException("Response contains no content");
	                }
	            } finally {
	                response.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        return sb_response.toString();
	}

	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result="-1";
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                result="-1";
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
	/**
	 * <p>
	 * Title: main<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "v1");
		map.put("k2", "v2");
		String urlString = "http://localhost:8080/greeting";
		String paramiterString = "name=456";
		try {
			System.out.println(HttpClientUtil.httpPost(urlString, map, "UTF-8"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//发送 GET 请求
        String s=HttpClientUtil.sendGet(urlString, "");
        System.out.println(s);
        
        //发送 POST 请求
        String sr=HttpClientUtil.sendPost(urlString, paramiterString);
        System.out.println(sr);
		
	}

}
