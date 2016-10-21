/**
 * <p>title:RandomUtil.java<／p>
 * <p>Description: <／p>
 * @date:2016年5月19日下午11:30:52
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util;

import java.util.Random;

/**  
 * 创建时间：2016年5月19日 下午11:30:52  
 * 项目名称：UtilZW   
 * 文件名称：RandomUtil.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年5月19日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: RandomUtil<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年5月19日
 */
public class RandomUtil {
	private static Random rand = new Random(); 
	/**
	 * <p>Title: main<／p>
	 * <p>Description: <／p>
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int getRandom(int min, int max){
		if (min > max) {
			return max;
		}
		return rand.nextInt(max - min + 1) + min;
	}
}
