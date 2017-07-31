package org.reflect.bootsrap;
import static java.lang.System.out;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
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
 * @author donald
 * 2017年7月29日
 * 上午10:37:30
 * @param <T>
 */
public class TypeVariableTest<T> {
	private List<T> list = null;
	private T t = null;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
//		getTypeTest();
		getBoundsTest();
//		getGenericDeclarationTest();
//		getNameTest();
	} 
	/**
	 * 泛型的类型变量，指的是List<T>、Map<K,V>中的T，K，V等值，
	 * 实际的Java类型是TypeVariableImpl（TypeVariable的子类）；
	 * 此外，还可以对类型变量加上extend限定，这样会有类型变量对应的上限；
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static void getTypeTest() throws NoSuchFieldException, SecurityException{
		Field listFeild = TypeVariableTest.class.getDeclaredField("list");
		Type listType = listFeild.getGenericType();//获取List的泛型类型
		out.println("====List泛型类型Type："+listType);//java.util.List<T>
		//获取List实际的Type类型：sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		out.println("====List泛型类型Type Name："+listType.getClass().getName());
		ParameterizedType listParameterizedType = (ParameterizedType) listType;
		//获取泛型中的实际类型T
		Type[] listTypes = listParameterizedType.getActualTypeArguments();
		out.println("List泛型中的实际类型:"+listTypes[0]);//T
		//泛型中的实际类型T的Type类型sun.reflect.generics.reflectiveObjects.TypeVariableImpl
		out.println("List泛型中的实际类型Type:"+listTypes[0].getClass().getName());
	}
	/**
	 * 获得该类型变量的上限，也就是泛型中extend右边的值；例如 List<T extends Number> ，
	 * Number就是类型变量T的上限；如果我们只是简单的声明了List<T>（无显式定义extends），那么默认为Object；
	 * 当前方法测试，只测试无显式定义extends，那么默认为Object这种情况。
	 * 另外一种情况see#org.reflect.bootsrap.TypeVariableGetBoundsTest#getBoundsTest方法。
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@SuppressWarnings("rawtypes")
	private static void getBoundsTest() throws NoSuchFieldException, SecurityException{
		Field tFeild = TypeVariableTest.class.getDeclaredField("t");
		TypeVariable tTypeVariable = (TypeVariable) tFeild.getGenericType();
		//获取类型变量t的上边界
		Type[] tTypes = tTypeVariable.getBounds();
		//java.lang.Object
		for(int i=0;i<tTypes.length;i++){
			out.println("变量t的上边界"+i+"为："+tTypes[i]);
		}
	}
	/**
	 * 获取声明该类型变量实体，也就是TypeVariableTest<T>中的TypeVariableTest；
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	@SuppressWarnings("rawtypes")
	private static void getGenericDeclarationTest() throws NoSuchFieldException, SecurityException{
		Field tFeild = TypeVariableTest.class.getDeclaredField("t");
		TypeVariable tTypeVariable = (TypeVariable) tFeild.getGenericType();
		//获取声明该变量（T）的实体
		GenericDeclaration tGenericDeclaration = tTypeVariable.getGenericDeclaration();
		//org.reflect.bootsrap.TypeVariableTest
		out.println("===t变量声明实体为："+tGenericDeclaration);
		TypeVariable[]  typeVariableTest = tGenericDeclaration.getTypeParameters();
		//org.reflect.bootsrap.TypeVariableTest类型变量T
		out.println("===t变量声明实体参数类型为："+typeVariableTest[0]);//T
		//TypeVariableTest类型变量Type：sun.reflect.generics.reflectiveObjects.TypeVariableImpl
		out.println("===t变量声明实体参数类型为："+typeVariableTest[0].getClass().getName());
	}
	/**
	 * 获取类型变量在源码中定义的名称；
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	@SuppressWarnings("rawtypes")
	private static void getNameTest() throws NoSuchFieldException, SecurityException{
		Field tFeild = TypeVariableTest.class.getDeclaredField("t");
		TypeVariable tTypeVariable = (TypeVariable) tFeild.getGenericType();
		//获取类型变量在源码中定义的名称
		String tTypeVariableName = tTypeVariable.getName();
		out.println("===t变量在源码中定义的名称为："+tTypeVariableName);//T
	}
}
