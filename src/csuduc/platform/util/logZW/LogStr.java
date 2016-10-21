/**
 * <p>title:LogStr.java<／p>
 * <p>Description: <／p>
 * @date:2016年9月12日下午3:52:38
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.logZW;

/**  
 * 创建时间：2016年9月12日 下午3:52:38  
 * 项目名称：UtilZW   
 * 文件名称：LogStr.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年9月12日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: LogStr<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2016年9月12日
 */
public class LogStr {

	private int depth = 0;
	private StringBuilder sb = new StringBuilder();
	private String token = "=";
	private int tokenLen = 1;
	private StringBuilder preStr = new StringBuilder();
	
	public LogStr(){
		Clear();
	}
	public LogStr(int adepth){
		if (adepth <0) {
			adepth = 0;
		}
		depth = adepth;
		for (int i = 0; i <= depth; i++) {
			preStr.append(token);
		}
	}
	public LogStr appendUp() {
		if (depth > 0) {
			depth --;
			preStr.delete(preStr.length()-token.length(),preStr.length());
		}
		return this;
	}
	public LogStr appendUp(String str) {
		if (depth > 0) {
			depth --;
			preStr.delete(preStr.length()-token.length(),preStr.length());
		}
		sb.append(preStr);
		sb.append(str);
		sb.append('\n');
		return this;
	}

	public LogStr append(String str) {
		sb.append(preStr);
		sb.append(str);
		sb.append('\n');
		return this;
	}
	public LogStr appendDown() {
		if (depth < 100) {
			depth ++;
			preStr.append(token);
		}
		
		return this;
	}
	public LogStr appendDown(String str) {
		if (depth < 100) {
			depth ++;
			preStr.append(token);
		}
		sb.append(preStr);
		sb.append(str);
		sb.append('\n');
		return this;
	}

	public LogStr appendUp(StringBuilder str) {
		if (depth > 0) {
			depth --;
			preStr.delete(preStr.length()-token.length(),preStr.length());
		}
		sb.append(preStr);
		sb.append(str);
		sb.append('\n');
		return this;
	}

	public LogStr append(StringBuilder str) {
		sb.append(preStr);
		sb.append(str);
		sb.append('\n');
		return this;
	}

	public LogStr appendDown(StringBuilder str) {
		if (depth < 100) {
			depth ++;
			preStr.append(token);
		}
		sb.append(preStr);
		sb.append(str);
		sb.append('\n');
		return this;
	}

	public String toString() {

		return sb.toString();
	}
	public LogStr Clear(){
		depth = 0;
		if (sb.length()>0) {
			sb.delete(0, sb.length());
		}
		tokenLen = token.length();
		if (preStr.length() > 0) {
			preStr.delete(0, preStr.length());
		}
		preStr.append(token);
		return this;
	}
	public static void main(String[] args){
		LogStr logStr = new LogStr();
		logStr.append("nihao,woshi 0");
		logStr.append("nihao woshi 2");
		logStr.appendDown("low1");
		logStr.appendDown("low2");
		logStr.appendUp("up1");
		logStr.appendUp("up2");
		logStr.append("nihao woshi 3");
		System.out.println(logStr.toString());
		logStr.Clear();
		logStr.append("nihao,woshi 0");
		logStr.append("nihao woshi 2");
		logStr.appendDown("low1");
		logStr.appendDown("low2");
		logStr.appendUp("asdfasdfie");
		logStr.appendUp("up1");
		logStr.appendUp("up2");
		logStr.append("nihao woshi 3");	
		System.out.println(logStr.toString());
		logStr.append("nihao,woshi 0");
		logStr.append("nihao woshi 2");
		logStr.appendDown("low1");
		logStr.appendDown("low2");
		logStr.appendUp("asdfasdfie");
		logStr.appendUp("up1");
		logStr.appendUp("up2");
		logStr.append("nihao woshi 3");
		System.out.println(logStr.toString());
	}
}
