package org.reflect.bootsrap;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.lang.System.out;
/**
 * 测试ParameterizedType类型的相关方法
 * ParameterizedType表示参数化类型，也就是泛型，例如List<T>、Set<T>等；
 * @author donald
 * 2017年7月28日
 * 下午2:28:05
 * @param <T>
 */
public class ParameterizedTypeTest<T> {
	private List<T> list = null;
	private Set<T> set = null;
	private Map<String,Integer> map = null;
	private List<Map<String,Integer>> listMap = null;
	private Map.Entry<String,Integer> mapEntry = null;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		getTypeTest();
		getActualTypeArgumentsTest();
		getRawTypeTest();
		getOwnerTypeTest();
		
	}
	/**
	 *  ParameterizedType表示参数化类型，也就是泛型，例如List<T>、Set<T>等；
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static void getTypeTest() throws NoSuchFieldException, SecurityException{
		Field listFeild = ParameterizedTypeTest.class.getDeclaredField("list");
		//获取List的泛型类型
		Type listType = listFeild.getGenericType();
		//java.util.List<T>
		out.println("====List泛型类型Type："+listType);
		//获取List实际的Type类型：sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		out.println("====List泛型类型Type Name："+listType.getClass().getName());
		out.println("====List泛型类型Type Name："+listType.getClass().getSimpleName());
		
		Field setFeild = ParameterizedTypeTest.class.getDeclaredField("set");
		//获取Set的泛型类型
		Type setType = setFeild.getGenericType();
		//java.util.Set<T>
		out.println("====Set泛型类型Type："+setType);
		//获取Set实际的Type类型：sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		out.println("====Set泛型类型Type Name："+setType.getClass().getName());
		out.println("====Set泛型类型Type Name："+setType.getClass().getSimpleName());
		
	}
	/**
	 * 获取泛型中的实际类型，可能会存在多个泛型，例如Map<K,V>,所以会返回Type[]数组；
	 * 值得注意的是，无论<>中有几层嵌套(List<Map<String,Integer>)，
	 * getActualTypeArguments()方法永远都是脱去最外层的<>(也就是List<>)，
	 * 将口号内的内容（Map<String,Integer>）返回；
	 * 我们经常遇到的List<T>，通过getActualTypeArguments()方法，得到的返回值是TypeVariableImpl对象
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	private static void getActualTypeArgumentsTest() throws NoSuchFieldException, SecurityException{
		Field mapFeild = ParameterizedTypeTest.class.getDeclaredField("map");
		//获取Map的泛型类型:sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		Type mapType = mapFeild.getGenericType();
		//java.util.Map<java.lang.String, java.lang.Integer>
		out.println("====Map泛型类型Type："+mapType);
		//sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		out.println("====Map泛型类型Type Name："+mapType.getClass().getName());
		ParameterizedType mapParameterizedType = (ParameterizedType) mapType;
		//获取泛型中的实际类型java.lang.String,java.lang.Integer
		Type[] mapTypes = mapParameterizedType.getActualTypeArguments();
		//java.lang.String,java.lang.Integer
		for(int i=0;i<mapTypes.length;i++){
			out.println("泛型中的实际类型"+i+":"+mapTypes[i]);
		}
		/*
		 * 测试getActualTypeArguments()方法永远都是脱去最外层的<>
		 */
		Field listMapFeild = ParameterizedTypeTest.class.getDeclaredField("listMap");
		//获取Map的泛型类型:sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		Type listMapType = listMapFeild.getGenericType();
		//java.util.List<java.util.Map<java.lang.String, java.lang.Integer>>
		out.println("====Map泛型类型Type："+listMapType);
		//sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		out.println("====Map泛型类型Type Name："+listMapType.getClass().getName());
		ParameterizedType listMapTypeParameterizedType = (ParameterizedType) listMapType;
		//获取泛型中的实际类型java.util.Map<java.lang.String, java.lang.Integer>
		Type[] listMapTypes = listMapTypeParameterizedType.getActualTypeArguments();
		//java.util.Map<java.lang.String, java.lang.Integer>
		for(int i=0;i<listMapTypes.length;i++){
			out.println("List-Map泛型中的实际类型"+i+":"+listMapTypes[i]);
		}
		
		Field listFeild = ParameterizedTypeTest.class.getDeclaredField("list");
		Type listType = listFeild.getGenericType();//获取List的泛型类型
		out.println("====List泛型类型Type："+listType);//java.util.List<T>
		//获取List实际的Type类型：sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		out.println("====List泛型类型Type Name："+listType.getClass().getName());
		ParameterizedType listParameterizedType = (ParameterizedType) listType;
		//获取泛型中的实际类型T
		Type[] listTypes = listParameterizedType.getActualTypeArguments();
		for(int i=0;i<listTypes.length;i++){
			//T
			out.println("List泛型中的实际类型"+i+":"+listTypes[i]);
			//sun.reflect.generics.reflectiveObjects.TypeVariableImpl
			out.println("List泛型中的实际类型Type"+i+":"+listTypes[i].getClass().getName());
		}
	}
	/**
	 * 获取声明泛型的类或者接口，也就是泛型中<>前面的那个值；
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	private static void getRawTypeTest() throws NoSuchFieldException, SecurityException{
		Field mapFeild = ParameterizedTypeTest.class.getDeclaredField("map");
		//获取Map的泛型类型:sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		Type mapType = mapFeild.getGenericType();
		ParameterizedType mapParameterizedType = (ParameterizedType) mapType;
		Type mapRawType = mapParameterizedType.getRawType();//获取声明泛型类/接口
		out.println("map实际类型:"+mapRawType);//java.util.Map
		
		Field listMapFeild = ParameterizedTypeTest.class.getDeclaredField("listMap");
		//获取Map的泛型类型:sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		Type listMapType = listMapFeild.getGenericType();
		ParameterizedType listMapTypeParameterizedType = (ParameterizedType) listMapType;
		Type listMapRawType = listMapTypeParameterizedType.getRawType();//获取声明泛型类/接口
		out.println("listMap实际类型:"+listMapRawType);//java.util.List
		
		Field listFeild = ParameterizedTypeTest.class.getDeclaredField("list");
		Type listType = listFeild.getGenericType();//获取List的泛型类型
		ParameterizedType listParameterizedType = (ParameterizedType) listType;
		Type listRawType = listParameterizedType.getRawType();//获取声明泛型类/接口
		out.println("list实际类型:"+listRawType);//java.util.List
	}
	/**
	 * 通过方法的名称，我们大概了解到，此方法是获取泛型的拥有者，那么拥有者是个什么意思？
	 * Returns a {@code Type} object representing the type that this type 
     * is a member of.  For example, if this type is {@code O.I},    
     * return a representation of {@code O}.  （摘自JDK注释）
     * 通过注解，我们得知，“拥有者”表示的含义--内部类的“父类”，通过getOwnerType()方法可以获取到内部类的“拥有者”；
     * 例如： Map  就是 Map.Entry<String,String>的拥有者；
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	private static void getOwnerTypeTest() throws NoSuchFieldException, SecurityException{
		Field mapEntryFeild = ParameterizedTypeTest.class.getDeclaredField("mapEntry");
		//获取Map的泛型类型:sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		Type mapEntryType = mapEntryFeild.getGenericType();
		ParameterizedType mapEntryParameterizedType = (ParameterizedType) mapEntryType;
		Type mapEntryOwnType = mapEntryParameterizedType.getOwnerType();//获取泛型拥有者
		out.println("mapEntry泛型拥有者:"+mapEntryOwnType);//java.util.Map
	}
	
}
