/**
 * <p>title:ImpServSecuCore.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月9日下午4:22:44
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.servSecu.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import csuduc.platform.util.generID.UUIDGener;

/**  
 * 创建时间：2015年11月9日 下午4:22:44  
 * 项目名称：zwplatform   
 * 文件名称：ImpServSecuCore.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月9日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: ImpServSecuCore<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年11月9日
 */
public class ImpServSecuCore implements InfServSecuCore{


	private static ConcurrentMap<String, ServSecuMdl> mapToken = new ConcurrentHashMap<String, ServSecuMdl>();
	private static InfServSecuCore instanceSingle = new ImpServSecuCore();
	
	public static InfServSecuCore getInstance(){
		return instanceSingle;
	}
	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see duc.platform.serviceSecu.core.InfServSecuCore#getTokenTimestampMdl()
	 */
	public ServSecuMdl getTokenTimestampMdl(){
		ServSecuMdl mdl = new ServSecuMdl();
		mdl.setTokenStr(getTokenOnce());
		mdl.setTimestamp_b(getTimeTag());
		mapToken.put(mdl.getTokenStr(), mdl);
		return mdl;
	}
	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see duc.platform.serviceSecu.core.InfServSecuCore#getTokenOnce()
	 */
	@Override
	public String getTokenOnce() {
		// TODO Auto-generated method stub
		return UUIDGener.getUUID();
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see duc.platform.serviceSecu.core.InfServSecuCore#getTimeTag()
	 */
	@Override
	public long getTimeTag() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis();
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param tokenStr
	 * @return
	 * @see duc.platform.serviceSecu.core.InfServSecuCore#isValid(java.lang.String)
	 */
	@Override
	public boolean isValid(String tokenStr) {
		// TODO Auto-generated method stub
		ServSecuMdl mdl = mapToken.get(tokenStr);
		if (null == mdl) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param timestamp
	 * @return
	 * @see duc.platform.serviceSecu.core.InfServSecuCore#isValid(long)
	 */
	@Override
	public boolean isValid(long timestamp) {
		// TODO Auto-generated method stub
		if ((System.currentTimeMillis() - timestamp) > ServSecuConf.TIME_OUT) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @param tokenStr
	 * @param timestamp
	 * @return
	 * @see duc.platform.serviceSecu.core.InfServSecuCore#isValid(java.lang.String, long)
	 */
	@Override
	public boolean isValid(ServSecuMdl mdl) {
		// TODO Auto-generated method stub
		if (!isValid(mdl.getTokenStr())) {
			return false;
		}
		if (!isValid(mdl.getTimestamp_b())) {
			mapToken.remove(mdl);
			return false;
		}
		
		//设置失效
		mapToken.remove(mdl);
		return true;
	}

}
