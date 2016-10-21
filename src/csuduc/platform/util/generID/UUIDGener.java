/**
 * <p>title:UUIDGener.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月9日下午4:30:00
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.generID;

import java.util.UUID;

/**  
 * 创建时间：2015年11月9日 下午4:30:00  
 * 项目名称：UtilZW   
 * 文件名称：UUIDGener.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月9日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: UUIDGener<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2015年11月9日
 */
public class UUIDGener {
	 /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
    	UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //System.out.println("getUUID:"+s);
        StringBuffer sb = new StringBuffer(64);
        sb.append(s.substring(0,8));
        sb.append(s.substring(9,13));
        sb.append(s.substring(14,18));
        sb.append(s.substring(19,23));
        sb.append(s.substring(24));
        //去掉“-”符号 
        return sb.toString(); 
    } 
    
    /** 
     * 获得指定数目的UUID 
     * @param number int 需要获得的UUID数量 
     * @return String[] UUID数组 
     */ 
    public static String[] getUUID(int number){ 
        if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++){ 
            ss[i] = getUUID(); 
        } 
        return ss; 
    } 
    public static String getUUIDShort(){ 
    	UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        //System.out.println("getUUID:"+s);
        StringBuffer sb = new StringBuffer(16);
      //去掉“-”符号 
        sb.append(s.substring(0,8));
        sb.append(s.substring(9,13));
        sb.append(s.substring(14,18));
        
        return sb.toString(); 
    } 

    public static String[] getUUIDShort(int number){
    	if(number < 1){ 
            return null; 
        } 
        String[] ss = new String[number]; 
        for(int i=0;i<number;i++){ 
            ss[i] = getUUIDShort(); 
        } 
        return ss; 
    }
    public static void main(String[] args){ 
        String[] ss = getUUID(10); 
        for(int i=0;i<ss.length;i++){ 
            System.out.println(ss[i]); 
        } 
    } 

}
