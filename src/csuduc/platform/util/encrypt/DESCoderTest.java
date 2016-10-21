/**
 * <p>title:DESCoderTest.java<／p>
 * <p>Description: <／p>
 * @date:2015年10月19日下午11:50:53
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.encrypt;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**  
 * 创建时间：2015年10月19日 下午11:50:53  
 * 项目名称：UtilZW   
 * 文件名称：DESCoderTest.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年10月19日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: DESCoderTest<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年10月19日
 */
public class DESCoderTest {

	/**
	 * <p>Title: setUp<／p>
	 * <p>Description: <／p>
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <p>Title: tearDown<／p>
	 * <p>Description: <／p>
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception{
		String inputStr = "DEShttp://127.0.0.1:8080/dataServ/ww";  
        String key = DESCoder.initKey();  
        System.err.println("原文:\t" + inputStr);  
  
        System.err.println("密钥:\t" + key);  
  
        byte[] inputData = inputStr.getBytes();  
        inputData = DESCoder.encrypt(inputData, key);  
  
        System.err.println("加密后:\t" + DESCoder.encryptBASE64(inputData));  
  
        byte[] outputData = DESCoder.decrypt(inputData, key);  
        String outputStr = new String(outputData);  
  
        System.err.println("解密后:\t" + outputStr);  
  
        assertEquals(inputStr, outputStr);  
	}

}
