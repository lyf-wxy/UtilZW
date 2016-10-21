/**
 * <p>title:JsonUtils.java<／p>
 * <p>Description: <／p>
 * @date:2015年11月5日上午9:48:32
 * @author：ZhongwengHao email:zhongweng.hao@qq.com
 * @version 1.0
 */
package csuduc.platform.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

/**  
 * 创建时间：2015年11月5日 上午9:48:32  
 * 项目名称：zwplatform   
 * 文件名称：JsonUtils.java  
 * 类说明：  
 *
 * Modification History:   
 * Date        Author         Version      Description   
 * ----------------------------------------------------------------- 
 * 2015年11月5日     Zhongweng       1.0         1.0 Version   
 */
/**
 * <p>
 * Title: JsonUtils<／p>
 * <p>
 * Description: <／p>
 * 
 * @author ZhongwengHao
 * @date 2015年11月5日
 */
public class JsonUtils {

	private static final Logger logger = (Logger) Logger
			.getLogger(JsonUtils.class);

	// private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 将对象序列化为JSON字符串
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	public static String serialize(Object object) {
		if (null == object) {
			return "";
		}
		Writer write = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.writeValue(write, object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			logger.error(
					"JsonGenerationException when serialize object to json", e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			logger.error("JsonMappingException when serialize object to json",
					e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException when serialize object to json", e);
		}
		objectMapper = null;
		return write.toString();
	}

	/**
	 * 将JSON字符串反序列化为对象
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	public static <T> T deserialize(String json, Class<T> clazz) {
		Object object = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			object = objectMapper.readValue(json, TypeFactory.rawClass(clazz));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		objectMapper = null;
		return (T) object;
	}

	/**
	 * 将JSON字符串反序列化为对象
	 * 
	 * @param object
	 * @return JSON字符串
	 */
	public static <T> T deserialize(String json, TypeReference<T> typeRef) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return (T) objectMapper.readValue(json, typeRef);
		} catch (JsonParseException e) {
			e.printStackTrace();
			logger.error("JsonParseException when deserialize json", e);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			logger.error("JsonMappingException when deserialize json", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IOException when deserialize json", e);
		}
		objectMapper = null;
		return null;
	}

	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	public static JavaType getCollectionType(ObjectMapper mapper,
			Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}

	/**
	 * <p>Title: deserialize<／p>
	 * <p>Description: <／p>
	 * @param json
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static <T> T deserialize(String json, Class<T> collectionClass, Class<?>... elementClasses) {
		Object object = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			object = objectMapper.readValue(json, getCollectionType(objectMapper, collectionClass, elementClasses));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		objectMapper = null;
		return (T) object;
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

		// ！！！！注意：Json化类 需要有无参构造函数
		// TODO Auto-generated method stub
		List<TestEntity> listMdls = new LinkedList<TestEntity>();
		listMdls.add(new TestEntity(1));
		listMdls.add(new TestEntity(2));
		String str1 = JsonUtils.serialize(listMdls);

		System.out.println(str1);

		@SuppressWarnings("unchecked")
		List<TestEntity> listMdls2 =JsonUtils.deserialize(
				str1, List.class, TestEntity.class);

		for (TestEntity testEntity : listMdls2) {
			System.out.println(testEntity.toString());
		}

		System.out.println("=====================================");
		TestEntity testMdlEntity = new TestEntity(3);
		String resString = JsonUtils.serialize(testMdlEntity);
		System.out.println(resString);
		TestEntity josnEntity = JsonUtils.deserialize(resString,
				TestEntity.class);
		System.out.println(josnEntity.toString());
	}

}
