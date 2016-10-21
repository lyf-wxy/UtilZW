/**
 * <p>title:DSACoderTest.java<／p>
 * <p>Description: <／p>
 * @date:2015年10月20日上午12:05:22
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.encrypt;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**  
 * 创建时间：2015年10月20日 上午12:05:22  
 * 项目名称：UtilZW   
 * 文件名称：DSACoderTest.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年10月20日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: DSACoderTest<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2015年10月20日
 */
public class DSACoderTest {

	/**
	 * <p>
	 * Title: setUp<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * <p>
	 * Title: tearDown<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		String inputStr = "abc";
		byte[] data = inputStr.getBytes();

		// 构建密钥
		Map<String, Object> keyMap = DSACoder.initKey();

		// 获得密钥
		String publicKey = DSACoder.getPublicKey(keyMap);
		String privateKey = DSACoder.getPrivateKey(keyMap);

		System.err.println("公钥:\r" + publicKey);
		System.err.println("私钥:\r" + privateKey);

		// 产生签名
		String sign = DSACoder.sign(data, privateKey);
		System.err.println("签名:\r" + sign);

		// 验证签名
		boolean status = DSACoder.verify(data, publicKey, sign);
		System.err.println("状态:\r" + status);
		assertTrue(status);

	}

}
