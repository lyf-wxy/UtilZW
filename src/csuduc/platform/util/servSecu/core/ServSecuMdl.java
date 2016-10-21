/**
 * <p>title:ServSecuMdl.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月9日下午10:32:09
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.servSecu.core;
/**  
 * 创建时间：2015年11月9日 下午10:32:09  
 * 项目名称：zwplatform   
 * 文件名称：ServSecuMdl.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月9日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: ServSecuMdl<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年11月9日
 */
public class ServSecuMdl {
	protected String tokenStr;
	protected Long timestamp_b;
	
	
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
	 * @return the timestamp_b
	 */
	public Long getTimestamp_b() {
		return timestamp_b;
	}
	/**
	 * @param timestamp_b the timestamp_b to set
	 */
	public void setTimestamp_b(Long timestamp_b) {
		this.timestamp_b = timestamp_b;
	}
	
	public String getTimestamp_bStr(){
		return timestamp_b.toString();
	}
	
	public void setTimestamp_bStr(String timeString){
		this.timestamp_b = Long.parseLong(timeString);
	}
}
