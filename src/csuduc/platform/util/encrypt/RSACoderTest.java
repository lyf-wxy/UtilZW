/**
 * <p>title:RSACoderTest.java<／p>
 * <p>Description: <／p>
 * @date:2015年10月19日下午11:58:35
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.encrypt;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;  

/**  
 * 创建时间：2015年10月19日 下午11:58:35  
 * 项目名称：UtilZW   
 * 文件名称：RSACoderTest.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年10月19日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: RSACoderTest<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年10月19日
 */
public class RSACoderTest {

	private String publicKey;  
    private String privateKey;  
  
    @Before  
    public void setUp() throws Exception {  
        Map<String, Object> keyMap = RSACoder.initKey();  
  
        publicKey = RSACoder.getPublicKey(keyMap);  
        privateKey = RSACoder.getPrivateKey(keyMap);  
        System.err.println("公钥: \n\r" + publicKey);  
        System.err.println("私钥： \n\r" + privateKey);  
    }  
  
    @Test  
    public void test() throws Exception {  
        System.err.println("公钥加密——私钥解密");  
        String inputStr = "abc";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = RSACoder.encryptByPublicKey(data, publicKey);  
  
        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,  
                privateKey);  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        assertEquals(inputStr, outputStr);  
  
    }  
  
    @Test  
    public void testSign() throws Exception {  
        System.err.println("私钥加密——公钥解密");  
        String inputStr = "sign";  
        byte[] data = inputStr.getBytes();  
  
        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);  
  
        byte[] decodedData = RSACoder  
                .decryptByPublicKey(encodedData, publicKey);  
  
        String outputStr = new String(decodedData);  
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);  
        assertEquals(inputStr, outputStr);  
  
        System.err.println("私钥签名——公钥验证签名");  
        // 产生签名  
        String sign = RSACoder.sign(encodedData, privateKey);  
        System.err.println("签名:\r" + sign);  
  
        // 验证签名  
        boolean status = RSACoder.verify(encodedData, publicKey, sign);  
        System.err.println("状态:\r" + status);  
        assertTrue(status);  
  
    }  
}
