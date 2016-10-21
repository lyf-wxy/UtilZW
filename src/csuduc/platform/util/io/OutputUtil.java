/**
 * <p>title:OutputUtil.java<／p>
 * <p>Description: <／p>
 * @date:2016年5月19日下午1:27:47
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.io;

import java.util.Map;
import java.util.Set;

/**  
 * 创建时间：2016年5月19日 下午1:27:47  
 * 项目名称：UtilZW   
 * 文件名称：OutputUtil.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年5月19日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: OutputUtil<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年5月19日
 */
public class OutputUtil {

	@SuppressWarnings("rawtypes")
	public void map(Map<String,String> parameters){  
        Set set = parameters.entrySet();  
        Map.Entry[] entries = (Map.Entry[])set.toArray(new Map.Entry[set.size()]);  
        for (int i=0;i<entries.length;i++){  
            System.out.println("Key:"+entries[i].getKey().toString());  
            System.out.println("Value:"+entries[i].getValue().toString());  
        }  
    }  
}
