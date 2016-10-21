/**
 * <p>title:SerializeObjUtil.java<／p>
 * <p>Description: <／p>
 * @date:2016年2月25日下午10:47:50
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 创建时间：2016年2月25日 下午10:47:50
 * 项目名称：UtilZW
 * 文件类型：SerializeObjUtil.java
 * 类说明：
 *
 *  
 *修改日志：
 * Date			Author		Version		Description
 *---------------------------------------------------
 *2016年2月25日		Zhongweng	1.0			1.0Version
 */

/**
 * <p>Title: SerializeObjUtil<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年2月25日
 */
public class SerializeObjUtil {
	
	public static boolean saveModel(String fileName, Object obj) {
		BufferedOutputStream bufferedOut = null;
		ObjectOutputStream out = null;
		try {
			bufferedOut = new BufferedOutputStream(new FileOutputStream(
					fileName)); // 文件路径
			out = new ObjectOutputStream(bufferedOut);
			out.writeObject(obj);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				out = null;
			}
		}
		return true;
	}

	public static Object loadModel(String fileName) {
		BufferedInputStream inStream = null;
		ObjectInputStream in = null;
		Object object = null;
		try {
			inStream = new BufferedInputStream(new FileInputStream(fileName)); // 文件路径
			in = new ObjectInputStream(inStream);
			object = in.readObject();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// TODO: handle exception
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
				in = null;
			}
		}

		return object;
	}

}
