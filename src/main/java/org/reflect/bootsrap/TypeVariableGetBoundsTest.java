package org.reflect.bootsrap;
import static java.lang.System.out;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
/**
 * 测TypeVariable类型的相关方法
 * 泛型的类型变量，指的是List<T>、Map<K,V>中的T，K，V等值，
 * 实际的Java类型是TypeVariableImpl（TypeVariable的子类）；
 * 此外，还可以对类型变量加上extend限定，这样会有类型变量对应的上限。
 * 在TypeVariable接口中，有3个方法，分别为getBounds()、getGenericDeclaration()、getName()；
 * getBounds：
 * 获得该类型变量的上限，也就是泛型中extend右边的值；例如 List<T extends Number> ，
 * Number就是类型变量T的上限；如果我们只是简单的声明了List<T>（无显式定义extends），那么默认为Object；
 * 值得注意的是，类型变量的上限可以为多个，必须使用&符号相连接，
 * 例如 List<T extends Number & Serializable>；其中，& 后必须为接口；
 * getGenericDeclaration：
 * 获取声明该类型变量实体，也就是TypeVariableTest<T>中的TypeVariableTest；
 * getName：
 * 获取类型变量在源码中定义的名称。
 * 关联类{@link TypeVariableTest,GenericDeclarationTest}
 * @author donald
 * 2017年7月29日
 * 上午10:37:30
 * @param <T>
 */
public class TypeVariableGetBoundsTest<T extends Number & Serializable & Comparable> {
	private T t = null;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		getBoundsTest();
	} 
	/**
	 * 获得该类型变量的上限，也就是泛型中extend右边的值；例如 List<T extends Number> ，
	 * Number就是类型变量T的上限；如果我们只是简单的声明了List<T>（无显式定义extends），
	 * 那么默认为Object；
	 * 值得注意的是，类型变量的上限可以为多个，必须使用&符号相连接，
	 * 例如 List<T extends Number & Serializable>；其中，& 后必须为接口；
	 * 当前方法测试，一种情况；
	 * 另外一种只测试无显式定义extends，默认为Object的情况。
	 * see#org.reflect.bootsrap.TypeVariableTest#getBoundsTest方法。
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@SuppressWarnings("rawtypes")
	private static void getBoundsTest() throws NoSuchFieldException, SecurityException{
		Field tFeild = TypeVariableGetBoundsTest.class.getDeclaredField("t");
		TypeVariable tTypeVariable = (TypeVariable) tFeild.getGenericType();
		//获取类型变量t的上边界
		Type[] tTypes = tTypeVariable.getBounds();
		//0.java.lang.Number
		//1.java.io.Serializable
		//2.java.lang.Comparable
		for(int i=0;i<tTypes.length;i++){
			out.println("变量t的上边界"+i+"为："+tTypes[i]);
		}
	}
}
