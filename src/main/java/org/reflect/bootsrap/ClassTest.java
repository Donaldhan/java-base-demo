package org.reflect.bootsrap;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

import org.annotation.DataSource;
import org.annotation.SystemLog;

import static java.lang.System.out;

/**
 * Type接口的实现类，是我们工作中常用到的一个对象；
 * 在Java中，每个.class文件在程序运行期间，都对应着一个Class对象，
 * 这个对象保存有这个类的全部信息；因此，Class对象也称之为Java反射的基础；
 * @author donald
 * 2017年7月29日
 * 下午3:47:42
 */
@DataSource("dev")
public class ClassTest {
	private ClassTest classTest;
	public List<String> address;
	public String phone;
	private String name;
	protected Integer age;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, 
	ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
	IllegalArgumentException, InvocationTargetException {
//		getTypeTest();
//		forNameTest();
//		newInstanceTest();
//		getAnnotationTest();
//		getFieldsTest();
//		getDeclaredFieldsTest();
//		getMethodsTest();
		getDeclaredMethodsTest();
//		getConstructorsTest();
//		getDeclaredConstructorsTest();
		
	}
	/**
	 * 当我们没有声明泛型的时候，我们普通的对象就是一个Class类型，是Type中的一种；
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	private static void getTypeTest() throws NoSuchFieldException, SecurityException{
		Field classTestFeild = ClassTest.class.getDeclaredField("classTest");
		Type classTestType =  classTestFeild.getGenericType();
		//org.reflect.bootsrap.ClassTest
		out.println("===classTest 类型:"+classTestType);
	}
	/**
	 * 根据Class name 加载对应的Class类型
	 * @throws ClassNotFoundException
	 */
	@SystemLog(description="测试forName加载类")
	private static void forNameTest() throws ClassNotFoundException{
		Class t = Class.forName("java.lang.Thread");
		//java.lang.Thread
		out.println("===Class.forName 类型："+t);
//		forName(String name, boolean initialize,ClassLoader loader)
	}
	/**
	 * 测试Class创建实例方法
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static void newInstanceTest() throws  InstantiationException, IllegalAccessException{
		ClassTest classTest = ClassTest.class.newInstance();
		//org.reflect.bootsrap.ClassTest
		out.println("===classTest 类型："+classTest.getClass().getName());
	}
   /**
    * 
    */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void getAnnotationTest(){
		//判断类型是否被DataSource注解
		out.println("===DataSource是否为注解："+DataSource.class.isAnnotation());//true
		//判断类型是否为接口
		out.println("===ClassTest是否为 接口："+ClassTest.class.isInterface());//false
		ClassTest instance = new ClassTest();
		//判断对象是否为类型实例，true
		out.println("===instance是否为ClassTest实例："+ClassTest.class.isInstance(instance));
		Class clazz = ClassTest.class;
		//判断clazz是否为指定类型
		out.println("===clazz是否为Object："+clazz.isAssignableFrom(Object.class));//false
		out.println("===clazz是否为ClassTest："+clazz.isAssignableFrom(ClassTest.class));//true
		if(clazz.isAnnotationPresent(DataSource.class)){
			//获取类型注解
			DataSource dataSouce = (DataSource) clazz.getAnnotation(DataSource.class);
			out.print("数据源为："+dataSouce.value());//dev
		}
		//java.lang.Object
		out.println("===clazz Superclass："+clazz.getSuperclass());
		/**
		 * Returns the Class representing the component type of an array. 
		 * If this class does not represent an array class this method returns null.
		 */
		clazz.getComponentType();
		
		/**
		 * Returns an array of TypeVariable objects that represent 
		 * the type variables declared by the generic declaration represented 
		 * by this GenericDeclaration object, in declaration order. 
		 * Returns an array of length 0 
		 * if the underlying generic declaration declares no type variables.
		 */
		clazz.getTypeParameters();
		/**
		 * 获取类型直接实现的接口类
		 */
		Type[] clazzInterfaceTypes = clazz.getGenericInterfaces();
		/**
		 * Returns the Type representing the direct superclass of the entity 
		 * (class, interface, primitive type or void) represented by this Class. 
		 */
		clazz.getGenericSuperclass();
	}
    /**
     * 获取Class的所有public修饰的声明变量
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
	private static void getFieldsTest() throws NoSuchFieldException, SecurityException{
		//获取类型公有变量
		Field[] fields = ClassTest.class.getFields();
		//address,phone
		for(int i=0;i<fields.length;i++){
			out.println("公有变量"+i+"名称为："+fields[i].getName());
		}
		Field nameField = ClassTest.class.getField("address");
		//获取声明变量Class,java.util.List
		out.println("address类型Type为："+nameField.getType());
		//获取声明变量Type,java.util.List<java.lang.String>
		out.println("address类型Type为："+nameField.getGenericType());
		//获取变量声明类：org.reflect.bootsrap.ClassTest
		out.println("address变量声明类为："+nameField.getDeclaringClass());
	}
	/**
	 * 获取Class的所有声明变量，包括public，protected，private
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * 
	 */
	private static void getDeclaredFieldsTest() throws NoSuchFieldException, SecurityException{
		//获取类型所有变量
		Field[] declaredField = ClassTest.class.getDeclaredFields();
		//classTest,address,phone,name,age
		for(int i=0;i<declaredField.length;i++){
			out.println("变量"+i+"名称为："+declaredField[i].getName());
		}
		Field nameField = ClassTest.class.getDeclaredField("name");
		//获取声明变量Class,java.lang.String
		out.println("name类型Type为："+nameField.getType());
		//获取声明变量Type,java.lang.String
		out.println("name类型Type为："+nameField.getGenericType());
		//获取变量声明类：org.reflect.bootsrap.ClassTest
		out.println("name变量声明类为："+nameField.getDeclaringClass());
	}
	/**
	 * 获取类型公有方法
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@SuppressWarnings("unchecked")
	private static void getMethodsTest() throws NoSuchMethodException, SecurityException{
		Class clazz = ClassTest.class;
		//获取类型公有方法
		Method[] methods = clazz.getMethods();
		//main,getAdress,hashCode,wait,equals,toString,notifyAll,notify
		for(int i=0;i<methods.length;i++){
			out.println("公有方法"+i+"名称为："+methods[i].getName());
		}
		Method getAddressMethod = clazz.getMethod("getAdress", null);
		//判断方法是否被SystemLog注解
		if(getAddressMethod.isAnnotationPresent(SystemLog.class)){
			SystemLog systemLog = getAddressMethod.getAnnotation(SystemLog.class);
			//获取地址信息
			out.println("getAdress方法作用："+systemLog.description());
		}
		//java.util.List
		out.println("getAdress方法返回类型："+getAddressMethod.getReturnType());
	}
	/**
	 * 获取类型所有方法，包括public，protected，private
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void getDeclaredMethodsTest() throws NoSuchMethodException, SecurityException,
				InstantiationException,IllegalAccessException, 
				IllegalArgumentException, InvocationTargetException{
		Class clazz = ClassTest.class;
		//获取类型所有方法
		Method[] declaredMethods = clazz.getDeclaredMethods();
		//main,getAdress,getMethodsTest,getDeclaredMethodsTest,
		//getFieldsTest,getDeclaredFieldsTest,getConstructorsTest,getDeclaredConstructorsTest
		//newInstanceTest,getAnnotationTest,getTypeTest,forNameTest,setNameAndAge
		for(int i=0;i<declaredMethods.length;i++){
			out.println("方法"+i+"名称为："+declaredMethods[i].getName());
		}
		Method getAddressMethod = clazz.getDeclaredMethod("setNameAndAge", String.class,Integer.class);
		ClassTest classTest = (ClassTest) clazz.newInstance();
		if(getAddressMethod !=null){
			getAddressMethod.invoke(classTest, "donald",29);
			//年龄：donald,29
			out.println("classTest的用户名，年龄："+classTest.name+","+classTest.age);
		}
	}
	/**
	 * 获取clazz公有构造方法
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void getConstructorsTest() throws NoSuchMethodException, SecurityException,
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class clazz = ClassTest.class;
		//获取clazz公有构造方法
		Constructor[] clazzConstructor = clazz.getConstructors();
		//org.reflect.bootsrap.ClassTest,org.reflect.bootsrap.ClassTest
		for(int i=0;i<clazzConstructor.length;i++){
			out.println("公有构造方法"+i+"名称为："+clazzConstructor[i].getName());
		}
		//获取指定参数类型的构造方法
		Constructor phoneConstructor = clazz.getConstructor(String.class);
		//创建实例
		ClassTest classTest = (ClassTest) phoneConstructor.newInstance("13697068686");
		out.print("classTest phone:"+classTest.phone);//13697068686
	 }
	/**
	 * 获取clazz所有构造方法
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void getDeclaredConstructorsTest() throws NoSuchMethodException, SecurityException,
	InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class clazz = ClassTest.class;
		//获取clazz公有构造方法
		Constructor[] clazzConstructor = clazz.getDeclaredConstructors();
		//org.reflect.bootsrap.ClassTest,org.reflect.bootsrap.ClassTest,org.reflect.bootsrap.ClassTest
		for(int i=0;i<clazzConstructor.length;i++){
			out.println("构造方法"+i+"名称为："+clazzConstructor[i].getName());
		}
		//获取指定参数类型的构造方法
		Constructor nameAgeConstructor = clazz.getDeclaredConstructor(String.class,Integer.class);
		//创建实例
		ClassTest classTest = (ClassTest) nameAgeConstructor.newInstance("donald",29);
		out.print("classTest name，age:"+classTest.name+","+classTest.age);
	 }
	/**
	 * 用于测试getDeclaredMethods
	 * @param name
	 * @param age
	 */
	@SystemLog(description="设置姓名和年龄")
	private void setNameAndAge(String name,Integer age){
		this.name = name;
		this.age = age;
	}
	/**
	 * 用于测试getMethod
	 * @return
	 */
	@SystemLog(description="获取地址信息")
	public List<String> getAdress(){
		return this.address;
	}
	/**
	 * 
	 */
	public ClassTest() {
		super();
	}
	/**
	 * 
	 * @param phone
	 */
	public ClassTest(String phone) {
		super();
		this.phone = phone;
	}
	/**
	 * 
	 * @param name
	 * @param age
	 */
	private ClassTest(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	
}
