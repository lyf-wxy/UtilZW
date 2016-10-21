/**
 * <p>title:PBECoder.java<／p>
 * <p>Description: <／p>
 * @date:2015年10月19日下午11:54:13
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.encrypt;
import java.security.Key;  
import java.util.Random;  
  
import javax.crypto.Cipher;  
import javax.crypto.SecretKey;  
import javax.crypto.SecretKeyFactory;  
import javax.crypto.spec.PBEKeySpec;  
import javax.crypto.spec.PBEParameterSpec; 
/**  
 * 创建时间：2015年10月19日 下午11:54:13  
 * 项目名称：UtilZW   
 * 文件名称：PBECoder.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年10月19日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: PBECoder<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年10月19日
 */
public class PBECoder extends Coder {

	/** 
     * 支持以下任意一种算法 
     *  
     * <pre> 
     * PBEWithMD5AndDES  
     * PBEWithMD5AndTripleDES  
     * PBEWithSHA1AndDESede 
     * PBEWithSHA1AndRC2_40 
     * </pre> 
     */  
    public static final String ALGORITHM = "PBEWITHMD5andDES";  
  
    /** 
     * 盐初始化 
     *  
     * @return 
     * @throws Exception 
     */  
    public static byte[] initSalt() throws Exception {  
        byte[] salt = new byte[8];  
        Random random = new Random();  
        random.nextBytes(salt);  
        return salt;  
    }  
  
    /** 
     * 转换密钥<br> 
     *  
     * @param password 
     * @return 
     * @throws Exception 
     */  
    private static Key toKey(String password) throws Exception {  
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);  
        SecretKey secretKey = keyFactory.generateSecret(keySpec);  
  
        return secretKey;  
    }  
  
    /** 
     * 加密 
     *  
     * @param data 
     *            数据 
     * @param password 
     *            密码 
     * @param salt 
     *            盐 
     * @return 
     * @throws Exception 
     */  
    public static byte[] encrypt(byte[] data, String password, byte[] salt)  
            throws Exception {  
  
        Key key = toKey(password);  
  
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);  
  
        return cipher.doFinal(data);  
  
    }  
  
    /** 
     * 解密 
     *  
     * @param data 
     *            数据 
     * @param password 
     *            密码 
     * @param salt 
     *            盐 
     * @return 
     * @throws Exception 
     */  
    public static byte[] decrypt(byte[] data, String password, byte[] salt)  
            throws Exception {  
  
        Key key = toKey(password);  
  
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 100);  
        Cipher cipher = Cipher.getInstance(ALGORITHM);  
        cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);  
  
        return cipher.doFinal(data);  
  
    }  

}
