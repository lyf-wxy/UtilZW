/**
 * <p>title:InfServSecuCore.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月9日下午4:21:48
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.servSecu.core;
/**  
 * 创建时间：2015年11月9日 下午4:21:48  
 * 项目名称：zwplatform   
 * 文件名称：InfServSecuCore.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月9日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: InfServSecuCore<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年11月9日
 */
public interface InfServSecuCore {

	public String getTokenOnce();
	
	public long getTimeTag();
	
	public ServSecuMdl getTokenTimestampMdl();
	
	public boolean isValid(String tokenStr);
	
	public boolean isValid(long timestamp);
	
	public boolean isValid(ServSecuMdl mdl);
}
