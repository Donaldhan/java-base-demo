package org.reflect.bootsrap;
import static java.lang.System.out;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.List;
/**
 *测试GenericArrayType类型的相关方法
 * 泛型数组类型，例如List<String>[] 、T[]等；
 * @author donald
 * 2017年7月28日
 * 下午2:29:42
 * @param <T>
 */
public class GenericArrayTypeTest<T> {
	private T[] listT = null;
	private List<String>[] listArray = null;
	private T[][] listTArray = null;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		getTypeTest();
		getGenericComponentTypeTest();
	}
	/**
	 * 泛型数组类型，例如List<String>[] 、T[]等；
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static void getTypeTest() throws NoSuchFieldException, SecurityException{
		Field listTFeild = GenericArrayTypeTest.class.getDeclaredField("listT");
		Type listTType = listTFeild.getGenericType();//获取实际Type类型
		//T[]
		out.println("====listT实际类型Type："+listTType);
		//获取数组实际的Type类型：sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl
		out.println("====listT Type Name："+listTType.getClass().getName());
		out.println("====listT Type Name："+listTType.getClass().getSimpleName());
		
		
		Field listArrayFeild = GenericArrayTypeTest.class.getDeclaredField("listArray");
		Type listArrayType = listArrayFeild.getGenericType();//获取实际Type类型
		//java.util.List<java.lang.String>[]
		out.println("====listArray实际类型Type："+listArrayType);
		//获取数组实际的Type类型：sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl
		out.println("====listArray Type Name："+listArrayType.getClass().getName());
		out.println("====listArray Type Name："+listArrayType.getClass().getSimpleName());
		
		Field listTArrayFeild = GenericArrayTypeTest.class.getDeclaredField("listTArray");
		Type listTArrayType = listTArrayFeild.getGenericType();//获取实际Type类型
		//T[][]
		out.println("====listTArrayType实际类型Type："+listTArrayType);
		//获取数组实际的Type类型：sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl
		out.println("====listTArrayType Type Name："+listTArrayType.getClass().getName());
		out.println("====listTArrayType Type Name："+listTArrayType.getClass().getSimpleName());
	}
	/**
	 * 在GenericArrayType接口中，仅有1个方法，就是getGenericComponentType()；
	 * 返回泛型数组中元素的Type类型，
	 * 即List<String>[] 中的 List<String>（ParameterizedTypeImpl）、T[] 中的T（TypeVariableImpl）；
	 * 值得注意的是，无论是几维数组，getGenericComponentType()方法都只会脱去最右边的[]，返回剩下的值；
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static void getGenericComponentTypeTest() throws NoSuchFieldException, SecurityException{
		Field listTFeild = GenericArrayTypeTest.class.getDeclaredField("listT");
		Type listTType = listTFeild.getGenericType();//获取实际Type类型
		GenericArrayType listTGenericArrayType = (GenericArrayType) listTType;
		//获取泛型数组类型的component type
		Type listTGenericComponentType = listTGenericArrayType.getGenericComponentType();
		//T
		out.println("====listT component Type："+listTGenericComponentType);
		//获取泛型数组类型的component type：sun.reflect.generics.reflectiveObjects.TypeVariableImpl
		out.println("====listT component Type Name："+listTGenericComponentType.getClass().getName());
		//TypeVariableImpl
		out.println("====listT component Type Name："+listTGenericComponentType.getClass().getSimpleName());	
	
		Field listArrayFeild = GenericArrayTypeTest.class.getDeclaredField("listArray");
		Type listArrayType = listArrayFeild.getGenericType();//获取实际Type类型
		GenericArrayType listArrayGenericArrayType = (GenericArrayType) listArrayType;
		//获取泛型数组类型的component type
		Type listArrayGenericComponentType = listArrayGenericArrayType.getGenericComponentType();
		//java.util.List<java.lang.String>
		out.println("====listArray component Type："+listArrayGenericComponentType);
		//获取泛型数组类型的component type：sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		out.println("====listArray component Type Name："+listArrayGenericComponentType.getClass().getName());
		//ParameterizedTypeImpl
		out.println("====listArray component Type Name："+listArrayGenericComponentType.getClass().getSimpleName());	
		
		
		
		Field listTArrayFeild = GenericArrayTypeTest.class.getDeclaredField("listTArray");
		Type listTArrayType = listTArrayFeild.getGenericType();//获取实际Type类型
		GenericArrayType listTArrayGenericArrayType = (GenericArrayType) listTArrayType;
		//获取泛型数组类型的component type
		Type listTArrayGenericComponentType = listTArrayGenericArrayType.getGenericComponentType();
		//T[]
		out.println("====listTArray component Type："+listTArrayGenericComponentType);
		//获取泛型数组类型的component type：sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl
		out.println("====listTArray component Type Name："+listTArrayGenericComponentType.getClass().getName());
		//GenericArrayTypeImpl
		out.println("====listTArray component Type Name："+listTArrayGenericComponentType.getClass().getSimpleName());	
	}
}
