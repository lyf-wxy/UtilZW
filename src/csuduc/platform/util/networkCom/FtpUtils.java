/**
 * <p>title:FtpUtils.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月5日下午4:34:05
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util.networkCom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import csuduc.platform.util.FileUtils;

/**  
 * 创建时间：2015年11月5日 下午4:34:05  
 * 项目名称：zwplatform   
 * 文件名称：FtpUtils.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月5日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: FtpUtils<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2015年11月5日
 */
public class FtpUtils {

	private static Logger logger = Logger.getLogger(FtpUtils.class);

	/**
	 * 获取FTPClient对象
	 * 
	 * @param ftpHost
	 *            FTP主机服务器
	 * @param ftpPassword
	 *            FTP 登录密码
	 * @param ftpUserName
	 *            FTP登录用户名
	 * @param ftpPort
	 *            FTP端口 默认为21
	 * @return
	 */
	public static FTPClient getFTPClient(String ftpHost, String ftpPassword,
			String ftpUserName, int ftpPort) {
		FTPClient ftpClient = null;
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
			ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
			if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				System.out.println("未连接到FTP，用户名或密码错误。");
				ftpClient.disconnect();
				ftpClient = null;
			} else {
				System.out.println("FTP连接成功。");
			}
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("FTP的IP地址可能错误，请正确配置。");
			ftpClient = null;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("FTP的端口错误,请正确配置。");
			ftpClient = null;
		}
		
		
		
		return ftpClient;
	}

	/**
	 * 去 服务器的FTP路径下上读取文件
	 * 
	 * @param ftpUserName
	 * @param ftpPassword
	 * @param ftpPath
	 * @param FTPServer
	 * @return
	 */
	public boolean downloadFile(String ftpHost, int ftpPort,String ftpUserName, String ftpPassword,
			 String ftpPath, String fileName,
			String localfilePath, String localfileName) {
		FTPClient ftpClient = null;
		OutputStream output = null;
		System.out.println("开始读取绝对路径" + ftpPath + "文件!");
		try {
			ftpClient = FtpUtils.getFTPClient(ftpHost, ftpPassword,
					ftpUserName, ftpPort);
			if (null == ftpClient) {
				System.out.println("登录失败！！！");
				return false;
			}
			ftpClient.setControlEncoding("UTF-8"); // 中文支持
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.enterLocalPassiveMode();
			ftpClient.changeWorkingDirectory(ftpPath);
			FTPFile[] remoteFiles = ftpClient.listFiles();
			int indexFile = -1;
			if (remoteFiles != null) {
				System.out.println("---" + remoteFiles.length);
				for (int i = 0; i < remoteFiles.length; i++) {
					System.out.println("---"+remoteFiles[i].getName());
					if (remoteFiles[i].getName().equals(fileName)) {

						indexFile = i;
						break;
					}
				}
			} else {
				System.out.println(ftpPath + "目录为空");
				return false;
			}
			if (-1 == indexFile) {
				System.out.println("没有找到" + fileName + "文件");
				return false;
			}
			File localFile = new File(localfilePath+"/"+localfileName);
			long sizeFile = remoteFiles[indexFile].getSize();

			output = new FileOutputStream(localFile);
			System.out.println(ftpPath + fileName + "---" + sizeFile);
			ftpClient.retrieveFile(fileName, output);
			output.flush();
			ftpClient.logout();
		} catch (FileNotFoundException e) {
			System.out.println("没有找到" + ftpPath + "文件");
			e.printStackTrace();
			return false;
		} catch (SocketException e) {
			System.out.println("连接FTP失败.");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件读取错误。");
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (output != null) {
					output.close();
					System.out.println("关闭文件流!");
					output = null;
				}
				if (ftpClient != null) {
					ftpClient.disconnect();
					System.out.println("断开连接!");
					ftpClient = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	/**
	 * 本地上传文件到FTP服务器
	 * 
	 * @param ftpPath
	 *            远程文件路径FTP
	 * @throws IOException
	 */
	public void uploadfile(String ftpHost, int ftpPort, String ftpUserName,
			String ftpPassword, String ftpPath, String ftpfileName,
			String localpath, String localfilename) {
		FTPClient ftpClient = null;
		System.out.println("开始上传文件到FTP.");
		System.out.format("ftpHost:%s--port:%d--\nftpUserName:%s"
				+ "--ftpPassword:%s--\nftpPath:%s--ftpfileName:%s\n"
				+ "localpath:%s--localfilename:%s"
				, ftpHost
				,ftpPort
				,ftpUserName
				,ftpPassword
				,ftpPath
				,ftpfileName
				,localpath
				,localfilename);
		try {
			ftpClient = FtpUtils.getFTPClient(ftpHost, ftpPassword,
					ftpUserName, ftpPort);
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			File f = new File(localpath + "/" + localfilename);
			InputStream in = new FileInputStream(f);
			ftpClient.storeFile(ftpPath+"/"+ftpfileName, in);
			in.close();
			System.out.println("上传文件" + ftpfileName + "到FTP成功!");
			//f.delete();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ftpClient.disconnect();
				System.out.println("断开连接!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 本地上传文件到FTP服务器
	 * 
	 * @param ftpPath
	 *            远程文件路径FTP
	 * @throws IOException
	 */
	public void upload(String ftpPath, String ftpUserName, String ftpPassword,
			String ftpHost, int ftpPort, String fileContent,
			String writeTempFielPath) {
		FTPClient ftpClient = null;
		System.out.println("开始上传文件到FTP.");
		try {
			ftpClient = FtpUtils.getFTPClient(ftpHost, ftpPassword,
					ftpUserName, ftpPort);
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			// 对远程目录的处理
			String remoteFileName = ftpPath;
			if (ftpPath.contains("/")) {
				remoteFileName = ftpPath
						.substring(ftpPath.lastIndexOf("/") + 1);
			}
			// FTPFile[] files = ftpClient.listFiles(new
			// String(remoteFileName));
			// 先把文件写在本地。在上传到FTP上最后在删除
			boolean writeResult = write(remoteFileName, fileContent,
					writeTempFielPath);
			if (writeResult) {
				File f = new File(writeTempFielPath + "/" + remoteFileName);
				InputStream in = new FileInputStream(f);
				ftpClient.storeFile(remoteFileName, in);
				in.close();
				System.out.println("上传文件" + remoteFileName + "到FTP成功!");
				f.delete();
			} else {
				System.out.println("写文件失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ftpClient.disconnect();
				System.out.println("断开连接!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 把配置文件先写到本地的一个文件中取
	 * 
	 * @param ftpPath
	 * @param str
	 * @return
	 */
	public boolean write(String fileName, String fileContext,
			String writeTempFielPath) {
		try {
			System.out.println("开始写配置文件");
			File f = new File(writeTempFielPath + "/" + fileName);
			if (!f.exists()) {
				if (!f.createNewFile()) {
					System.out.println("文件不存在，创建失败!");
				}
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(fileContext.replaceAll("\n", "\r\n"));
			bw.flush();
			bw.close();
			return true;
		} catch (Exception e) {
			logger.error("写文件没有成功");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * <p>
	 * Title: main<／p>
	 * <p>
	 * Description: <／p>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FtpUtils ftpClient = new FtpUtils();
		boolean res = false;
//		res = ftpClient.downloadFile("ducfileshare", "ducfileshare", "192.168.0.50",21,
//				"upload", "plugin_hj.jar",
//				"D:/ftplocal/plugin_hj.jar");
		
		//！！！！connection reset 异常，则需要检查客户端和服务器端关闭防火墙
		
		res = ftpClient.downloadFile( "192.168.0.70",21,
		"anonymous", "anonymous",
				"/", "plugin_hj.jar",
				"D:/ftplocal", "plugin_hj.jar");
		
		if (res) {
			System.out.println("下载成功！！");
		}else{
			System.out.println("下载失败！！");
		}
		ftpClient
				.uploadfile("192.168.0.70", 21, "anonymous", "anonymous",
						"/localUser","plugin_SYY.jar","D:/ftplocal", "plugin_SYY.jar");
	}

}
