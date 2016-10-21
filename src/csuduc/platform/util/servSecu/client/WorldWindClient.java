/**
 * <p>title:WorldWindClient.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月10日上午8:48:39
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.servSecu.client;

import java.util.HashMap;
import java.util.Map;

import csuduc.platform.util.JsonUtils;
import csuduc.platform.util.generID.UUIDGener;
import csuduc.platform.util.networkCom.HttpClientUtil;
import csuduc.platform.util.servSecu.core.ServSecuEncrypt;
import csuduc.platform.util.servSecu.core.ServSecuMdlRespClient;

/**  
 * 创建时间：2015年11月10日 上午8:48:39  
 * 项目名称：zwplatform   
 * 文件名称：WorldWindClient.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月10日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: WorldWindClient<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年11月10日
 */
public class WorldWindClient {

	private final static String ID_NAME = "WWClient";
	private final static String URL_SECU = "http://127.0.0.1:8080/zwplatform/servSecu/cltReq";
	public void secuIdentityAuth(){
		
		//生成一个随机串
		String randomString = ID_NAME+ UUIDGener.getUUID();
		//加密随机串
		String encryptString = null;
		try {
			encryptString = ServSecuEncrypt.EncryptDEC(randomString.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}
		//发送到验证服务端
		Map<String, String> map = new HashMap<String, String>();
		map.put("radom", encryptString);
		String responseString = null;
		try {
			responseString = HttpClientUtil.httpPost(URL_SECU, map, "UTF-8");
			System.out.println(responseString);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ;
		}
		//得到返回信息
		if (responseString==null) {
			System.out.println("responseString==null");
			
			return ;
		}
		ServSecuMdlRespClient mdlRespClient = JsonUtils.deserialize(responseString, ServSecuMdlRespClient.class);
		String urlDataServerString = null;
		try {
			urlDataServerString = ServSecuEncrypt.DescryptDES(mdlRespClient.getServDataURL());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}
		System.out.println("WWClient urlDataServerString:"+urlDataServerString);
		//向数据服务器发送数据请求
		map.clear();
		map.put("tk", mdlRespClient.getTokenStr());
		map.put("tm", mdlRespClient.getTimeStampStr());
		try {
			responseString = HttpClientUtil.httpPost(urlDataServerString, map, "UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(responseString);
			return ;
		}
		//得到返回结果
		System.out.println(responseString);
	}
	
	
	/**
	 * <p>Title: main<／p>
	 * <p>Description: <／p>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WorldWindClient client = new WorldWindClient();
		client.secuIdentityAuth();
		
	}

}
