/**
 * <p>title:PBECoderTest.java<／p>
 * <p>Description: <／p>
 * @date:2015年10月19日下午11:55:06
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.encrypt;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**  
 * 创建时间：2015年10月19日 下午11:55:06  
 * 项目名称：UtilZW   
 * 文件名称：PBECoderTest.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年10月19日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: PBECoderTest<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年10月19日
 */
public class PBECoderTest {

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
    public void test() throws Exception {  
        String inputStr = "abc";  
        System.err.println("原文: " + inputStr);  
        byte[] input = inputStr.getBytes();  
  
        String pwd = "efg";  
        System.err.println("密码: " + pwd);  
  
        byte[] salt = PBECoder.initSalt();  
  
        byte[] data = PBECoder.encrypt(input, pwd, salt);  
  
        System.err.println("加密后: " + PBECoder.encryptBASE64(data));  
  
        byte[] output = PBECoder.decrypt(data, pwd, salt);  
        String outputStr = new String(output);  
  
        System.err.println("解密后: " + outputStr);  
        assertEquals(inputStr, outputStr);  
    }  

}
