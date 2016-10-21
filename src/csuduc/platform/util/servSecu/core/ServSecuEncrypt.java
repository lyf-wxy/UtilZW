/**
 * <p>title:ServSecuEncrypt.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月10日上午12:09:26
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.servSecu.core;

import csuduc.platform.util.encrypt.DESCoder;

/**  
 * 创建时间：2015年11月10日 上午12:09:26  
 * 项目名称：zwplatform   
 * 文件名称：ServSecuEncrypt.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月10日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: ServSecuEncrypt<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年11月10日
 */
public class ServSecuEncrypt {

	public static String EncryptDEC(byte[] inputData)throws Exception{
		try {
			return DESCoder.encryptBASE64(DESCoder.encrypt(inputData, ServSecuConf.KEY_DES));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public static String DescryptDES(String inputData)throws Exception{
		
		try {
			return new String(DESCoder.decrypt(DESCoder.decryptBASE64(inputData), ServSecuConf.KEY_DES));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}
