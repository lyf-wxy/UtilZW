/**
 * <p>title:CertificateCoderTest.java<／p>
 * <p>Description: <／p>
 * @date:2015年10月20日上午12:10:54
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.encrypt;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**  
 * 创建时间：2015年10月20日 上午12:10:54  
 * 项目名称：UtilZW   
 * 文件名称：CertificateCoderTest.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年10月20日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: CertificateCoderTest<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年10月20日
 */
public class CertificateCoderTest {

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

	private String password = "123456";  
    private String alias = "www.zlex.org";  
    private String certificatePath = "d:/zlex.cer";  
    private String keyStorePath = "d:/zlex.keystore";  
  
    @Test  
    public void test() throws Exception {  
        System.err.println("公钥加密——私钥解密");  
        String inputStr = "Ceritifcate";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encrypt = CertificateCoder.encryptByPublicKey(data,  
                certificatePath);  
  
        byte[] decrypt = CertificateCoder.decryptByPrivateKey(encrypt,  
                keyStorePath, alias, password);  
        String outputStr = new String(decrypt);  
  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
  
        // 验证数据一致  
        assertArrayEquals(data, decrypt);  
  
        // 验证证书有效  
        assertTrue(CertificateCoder.verifyCertificate(certificatePath));  
  
    }  
  
    @Test  
    public void testSign() throws Exception {  
        System.err.println("私钥加密——公钥解密");  
  
        String inputStr = "sign";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = CertificateCoder.encryptByPrivateKey(data,  
                keyStorePath, alias, password);  
  
        byte[] decodedData = CertificateCoder.decryptByPublicKey(encodedData,  
                certificatePath);  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        assertEquals(inputStr, outputStr);  
  
        System.err.println("私钥签名——公钥验证签名");  
        // 产生签名  
        String sign = CertificateCoder.sign(encodedData, keyStorePath, alias,  
                password);  
        System.err.println("签名:\r" + sign);  
  
        // 验证签名  
        boolean status = CertificateCoder.verify(encodedData, sign,  
                certificatePath);  
        System.err.println("状态:\r" + status);  
        assertTrue(status);  
  
    }  

}
