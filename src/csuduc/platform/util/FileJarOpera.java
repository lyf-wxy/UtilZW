/**
 * <p>title:FileJarOpera.java<／p>
 * <p>Description: <／p>
 * @date:2016年2月24日上午11:04:27
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

/**  
 * 创建时间：2016年2月24日 上午11:04:27  
 * 项目名称：WebAppComm   
 * 文件名称：FileJarOpera.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2016年2月24日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>Title: FileJarOpera<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年2月24日
 */
public class FileJarOpera {

	/**
	 * <p>Title: backupJarFile<／p>
	 * <p>Description:
	 * 备份数据
	 *  <／p>
	 * @param jarPathCur 工作目录 带/
	 * @param jarPathBackup 备份目录 带/
	 * @param jarName jar文件名称，带.jar
	 * @throws IOException
	 */
	public static void backupJarFile(String jarPathCur,String jarPathBackup,String jarName) throws IOException{  
        dealJarFile(jarPathCur+jarName, jarPathBackup+jarName);  
    }  
      
    public static void recoverJarFile(String jarPathCur,String jarPathBackup,String jarName) throws IOException{  
    	dealJarFile(jarPathBackup +jarName, jarPathCur+jarName); 
    }  
      
    public static void dealJarFile(String filePath, String outputPath) throws IOException{  
        File jarFile = new File(filePath);  
        if(jarFile!=null && jarFile.exists()){  
            makeSupDir(outputPath);  
            writeFile(jarFile, new File(outputPath));  
        }  
    }  
      
    public static void makeSupDir(String outFileName) {   
        Pattern p = Pattern.compile("[/\\" + File.separator + "]");   
        Matcher m = p.matcher(outFileName);   
        while (m.find()) {   
            int index = m.start();   
            String subDir = outFileName.substring(0, index);   
            File subDirFile = new File(subDir);   
            if (!subDirFile.exists())   
                subDirFile.mkdir();  
        }  
    }   
      
    public static void zipFile(String folder, String jarPath) throws IOException{  
        JarOutputStream ops = new JarOutputStream(new FileOutputStream(jarPath));  
        File inFile = new File(folder);  
        zipFileEntry("", inFile, ops);  
        ops.close();  
    }  
      
    public static void zipFileEntry(String base, File inFile, JarOutputStream ops) throws IOException{  
        if(inFile.isDirectory()){  
            File[] files = inFile.listFiles();  
            ops.putNextEntry(new ZipEntry(base + "/"));  
            base = base.length() == 0 ? "" : base + "/";  
            for(File file:files){  
                zipFileEntry(base+file.getName(), file, ops);  
            }  
        }else{  
            ops.putNextEntry(new JarEntry(base));  
            InputStream ips = new FileInputStream(inFile);  
            int len = 0;  
            byte[] buffer = new byte[1024];   
            while((len = ips.read(buffer)) != -1){  
                ops.write(buffer,0,len);  
                ops.flush();   
            }  
            ips.close();  
        }  
    }  
      
    /**
     * <p>Title: unZipFile<／p>
     * <p>Description: 
     * 解压jar包，
     * <／p>
     * @param jarPath jar包的完整路径，包含文件名
     * @param folder 解压到的文件夹路径， 结尾带\\
     * @throws IOException
     */
    public static void unZipFile(String jarPath,String folder) throws IOException{  
        JarFile jarFile = new JarFile(jarPath);  
        Enumeration<JarEntry> jarEntrys = jarFile.entries();  
        while(jarEntrys.hasMoreElements()){  
            JarEntry jarEntry = jarEntrys.nextElement();  
            jarEntry.getName();  
            String outFileName = folder + jarEntry.getName();   

            File f = new File(outFileName);   
            makeSupDir(outFileName);   
            if(jarEntry.isDirectory()){  
                continue;  
            }  
            writeFile(jarFile.getInputStream(jarEntry), f);  
        }  
    }  
      
    public static void deleteFile(String jarPath) throws IOException{  
        File delFile = delFile = new File(jarPath);  
        if(delFile.exists() && delFile.isDirectory()){  
            if(delFile.listFiles().length==0){  
                delFile.delete();  
            } else {  
                for(File file:delFile.listFiles()){  
                    if(file.isDirectory()){  
                        deleteFile(file.getAbsolutePath());  
                    }  
                    file.delete();  
                }  
            }  
        }  
        if(delFile.exists() && delFile.isDirectory() && delFile.listFiles().length==0){  
            delFile.delete();  
        }   
    }  
      
    public static void writeFile(File inputFile, File outputFile) throws IOException{  
        writeFile(new FileInputStream(inputFile), outputFile);  
    }  
      
    public static void writeFile(InputStream ips, File outputFile) throws IOException{  
        OutputStream ops = new BufferedOutputStream(new FileOutputStream(outputFile));  
        try{   
            byte[] buffer = new byte[1024];   
            int nBytes = 0;   
            while ((nBytes = ips.read(buffer)) > 0){   
                ops.write(buffer, 0, nBytes);   
            }   
        }catch (IOException ioe){   
            throw ioe;   
        } finally {   
            try {   
                if (null != ops){   
                    ops.flush();   
                    ops.close();   
                }   
            } catch (IOException ioe){   
                throw ioe;   
            } finally{   
                if (null != ips){   
                    ips.close();   
                }   
            }   
        }  
    }  
      
    public static void main(String[] args){  
        try {  
            //backupJarFile(jarPath);  
            unZipFile("E:\\temp\\PluginHJ.jar","E:\\temp\\PluginHJ\\");  
            //zipFile(jarPath);  
            //deleteFile(jarPath+"/iphones");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
