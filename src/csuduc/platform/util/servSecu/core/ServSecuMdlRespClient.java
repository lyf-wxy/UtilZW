/**
 * <p>title:ServSecuMdlRespClient.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月9日下午11:25:42
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.servSecu.core;
/**  
 * 创建时间：2015年11月9日 下午11:25:42  
 * 项目名称：zwplatform   
 * 文件名称：ServSecuMdlRespClient.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月9日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: ServSecuMdlRespClient<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年11月9日
 */
public class ServSecuMdlRespClient {
	private String servDataURL = null;
	private String tokenStr = null;
	private String timeStampStr = null;
	/**
	 * @return the servDataURL
	 */
	public String getServDataURL() {
		return servDataURL;
	}
	/**
	 * @param servDataURL the servDataURL to set
	 */
	public void setServDataURL(String servDataURL) {
		this.servDataURL = servDataURL;
	}
	/**
	 * @return the tokenStr
	 */
	public String getTokenStr() {
		return tokenStr;
	}
	/**
	 * @param tokenStr the tokenStr to set
	 */
	public void setTokenStr(String tokenStr) {
		this.tokenStr = tokenStr;
	}
	/**
	 * @return the timeStampStr
	 */
	public String getTimeStampStr() {
		return timeStampStr;
	}
	/**
	 * @param timeStampStr the timeStampStr to set
	 */
	public void setTimeStampStr(String timeStampStr) {
		this.timeStampStr = timeStampStr;
	}
	
	
}
