package org.reflect.bootsrap;
import static java.lang.System.out;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
/**
 * ？---通配符表达式，表示通配符泛型，但是WildcardType并不属于Java-Type中的一种；
 * 例如：List<? extends Number> 和 List<? super Integer>；
 * 在WildcardType接口中，有2个方法，分别为getUpperBounds()、getLowerBounds();
 * getUpperBounds:
 * 获取泛型变量的上边界（extends）;
 * getLowerBounds:
 * 获取泛型变量的下边界（super）。
 * <? extends T>限定参数类型的上界：参数类型必须是T或T的子类型
 * <? super T> 限定参数类型的下界：参数类型必须是T或T的超类型
 * 总结为：
 * 1.<? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，
 * 但是入参类型无法确定，只能接受null的传入
 * 2.<? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
 * 3.? 既不能用于入参也不能用于返参
 * {@code ExtendsSuperTest}
 * @author donald
 * 2017年7月29日
 * 下午11:28:33
 */
public class WildcardTypeTest {
	private List<? extends Number> listNumber;
	private List<? super String> listString;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
//		getTypeTest();
//		getUpperBoundsTest();
		getLowerBoundsTest();
	}
	/**
	 * ？---通配符表达式，表示通配符泛型，但是WildcardType并不属于Java-Type中的一种；
	 * 例如：List<? extends Number> 和 List<? super Integer>；
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static void getTypeTest() throws NoSuchFieldException, SecurityException{
		Field listNumberFeild = WildcardTypeTest.class.getDeclaredField("listNumber");
		Type listNumberType = listNumberFeild.getGenericType();
		ParameterizedType listNumberParameterizedType = (ParameterizedType) listNumberType;
		//获取泛型中的实际类型
		Type[] listNumberTypes = listNumberParameterizedType.getActualTypeArguments();
		//通配符类型sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
		out.println("====listNumber通配符类型："+listNumberTypes[0].getClass());
		Field listStringFeild = WildcardTypeTest.class.getDeclaredField("listNumber");
		Type listStringType = listStringFeild.getGenericType();
		ParameterizedType listStringParameterizedType = (ParameterizedType) listStringType;
		//获取泛型中的实际类型
		Type[] listStringTypes = listStringParameterizedType.getActualTypeArguments();
		//通配符类型sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
		out.println("====listString通配符类型："+listStringTypes[0].getClass());
	}
	/**
	 * 获取泛型变量的上边界（extends）
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	private static void  getUpperBoundsTest() throws NoSuchFieldException, SecurityException{
		Field listNumberFeild = WildcardTypeTest.class.getDeclaredField("listNumber");
		Type listNumberType = listNumberFeild.getGenericType();
		ParameterizedType listNumberParameterizedType = (ParameterizedType) listNumberType;
		//获取泛型中的实际类型
		Type[] listNumberTypes = listNumberParameterizedType.getActualTypeArguments();
		//通配符类型sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
		out.println("====listNumber通配符类型："+listNumberTypes[0].getClass());
		WildcardType listNumberWildcardType = (WildcardType) listNumberTypes[0];
		//获取泛型变量的上边界（extends）
		Type[] listNumberWildcardTypes = listNumberWildcardType.getUpperBounds();
		//java.lang.Number
		out.println("====listNumber上边界（extends）："+listNumberWildcardTypes[0]);
	}
	/**
	 * 获取泛型变量的下边界（super）
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	private static void  getLowerBoundsTest() throws NoSuchFieldException, SecurityException{
		Field listStringFeild = WildcardTypeTest.class.getDeclaredField("listString");
		Type listStringType = listStringFeild.getGenericType();
		ParameterizedType listStringParameterizedType = (ParameterizedType) listStringType;
		//获取泛型中的实际类型
		Type[] listStringTypes = listStringParameterizedType.getActualTypeArguments();
		//通配符类型sun.reflect.generics.reflectiveObjects.WildcardTypeImpl
		out.println("====listString通配符类型："+listStringTypes[0].getClass());
		WildcardType listStringWildcardType = (WildcardType) listStringTypes[0];
		//获取泛型变量的下边界（super）
		Type[] listStringWildcardTypes = listStringWildcardType.getLowerBounds();
		//java.lang.String
		out.println("====listString下边界（super）："+listStringWildcardTypes[0]);
	}
}
