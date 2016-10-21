/**
 * <p>title:TestEntity.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月5日上午10:04:24
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
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**  
 * 创建时间：2015年11月5日 上午10:04:24  
 * 项目名称：zwplatform   
 * 文件名称：TestEntity.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月5日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: TestEntity<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2015年11月5日
 */
/**
 * <p>Title: TestEntity<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年2月24日
 */

/**
 * <p>Title: KVMdl<／p>
 * <p>Description: <／p>
 * @author ZhongwengHao
 * @date 2016年2月24日
 */
class KVMdl implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1123234134L;
	private String nameString;
	private int age;
	
	public KVMdl(){
		
	}
	
	public KVMdl(String name, int aage){
		this.nameString = name;
		this.age = aage;
	}
	/**
	 * @return the nameString
	 */
	public String getNameString() {
		return nameString;
	}
	/**
	 * @param nameString the nameString to set
	 */
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/* (non-Javadoc)
	 * <p>Description: <／p>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "KVMdl [nameString=" + nameString + ", age=" + age + "]";
	}
	
	
	
}
public class TestEntity implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int age;
	private String name;
	private Double weightDouble;
	private Map<String, String> mapBooks = new HashMap<String, String>();
	private Map<String, Vector<KVMdl>> mapBk = new HashMap<String, Vector<KVMdl>>();
	
	public TestEntity() {}
	
	public TestEntity(int id) {
		this.id = id;
		this.age = 25;
		this.name = "忠翁";
		this.weightDouble = 123.456;
		this.mapBooks.put("k1", "v1");
		this.mapBooks.put("k2", "v2");
		this.mapBooks.put("k3", "v3");
		
		Vector<KVMdl> vector = new Vector<KVMdl>();
		vector.add(new KVMdl("name1", 1));
		vector.add(new KVMdl("name2", 2));
		this.mapBk.put("mp2k1", vector);
	}

	public boolean saveModel(String fileName) {
		BufferedOutputStream bufferedOut = null;
		ObjectOutputStream out = null;
		try {
			bufferedOut = new BufferedOutputStream(new FileOutputStream(
					fileName)); // 文件路径
			out = new ObjectOutputStream(bufferedOut);
			out.writeObject(this);
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

	public Object loadModel(String fileName) {
		BufferedInputStream inStream = null;
		ObjectInputStream in = null;
		Object object = null;
		try {
			inStream = new BufferedInputStream(new FileInputStream(fileName)); // 文件路径
			in = new ObjectInputStream(inStream);
			object = in.readObject();
			in.close();
		} catch (Exception e) {
			return null;
		} finally {
			// TODO: handle exception
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				in = null;
			}
		}

		return object;
	}

	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the mapBk
	 */
	public Map<String, Vector<KVMdl>> getMapBk() {
		return mapBk;
	}

	/**
	 * @param mapBk the mapBk to set
	 */
	public void setMapBk(Map<String, Vector<KVMdl>> mapBk) {
		this.mapBk = mapBk;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the weightDouble
	 */
	public Double getWeightDouble() {
		return weightDouble;
	}

	/**
	 * @param weightDouble
	 *            the weightDouble to set
	 */
	public void setWeightDouble(Double weightDouble) {
		this.weightDouble = weightDouble;
	}

	/**
	 * @return the mapBooks
	 */
	public Map<String, String> getMapBooks() {
		return mapBooks;
	}

	/**
	 * @param mapBooks
	 *            the mapBooks to set
	 */
	public void setMapBooks(Map<String, String> mapBooks) {
		this.mapBooks = mapBooks;
	}

	/*
	 * (non-Javadoc) <p>Description: <／p>
	 * 
	 * @return
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+ this.id + this.age + this.name + this.weightDouble
				+ this.mapBooks.toString()+"--"+this.mapBk.toString();
	}

	public static void main(String[] args) {
		TestEntity testEntity = new TestEntity(1);

		try {
			testEntity.saveModel("E:\\testEntity.dat");
			
			TestEntity newTest = (TestEntity)testEntity.loadModel("E:\\testEntity.dat");
			System.out.println(newTest.toString());
			newTest.mapBooks.put("k", "valuenew");
			Vector<KVMdl> vector = new Vector<KVMdl>();
			vector.add(new KVMdl("name3", 3));
			vector.add(new KVMdl("name4", 4));
			newTest.mapBk.put("mp2k1", vector);
			System.out.println(newTest.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
