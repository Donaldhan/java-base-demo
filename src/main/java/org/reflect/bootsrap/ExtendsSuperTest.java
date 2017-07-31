package org.reflect.bootsrap;

import java.util.ArrayList;
import java.util.List;

/**
 * ？---通配符表达式，表示通配符泛型，但是WildcardType并不属于Java-Type中的一种； 例如：List<? extends Number>
 * 和 List<? super Integer>；
 * 在WildcardType接口中，有2个方法，分别为getUpperBounds()、getLowerBounds(); getUpperBounds:
 * 获取泛型变量的上边界（extends）; getLowerBounds: 获取泛型变量的下边界（super）。
 * {@code WildcardTypeTest} <? extends T>限定参数类型的上界：参数类型必须是T或T的子类型 <? super T>
 * 限定参数类型的下界：参数类型必须是T或T的超类型 总结为： 1.<? extends T>
 * 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收， 但是入参类型无法确定，只能接受null的传入 2.<? super
 * T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收 3.? 既不能用于入参也不能用于返参
 * 
 * @author donald 
 * 2017年7月30日 
 * 上午10:32:39
 */
public class ExtendsSuperTest {
	class Super {
	}

	class Self extends Super {
	}

	class Son extends Self {
	}

	public static void main(String[] args) {
		/**
		 * 参数类型上界是Self
		 */
		 //参数类型上界是Self
		 List<? extends Self> extendList = new ArrayList<>();
		 /*
		  * 不能放入任何类型，因为编译器只知道extendList中应该放入Self的某个子类，
		  * 但具体放哪种子类它并不知道，因此，除了null以外，不能放入任何类型
		  */
		 extendList.add(new Son());//error 
		 extendList.add(new Self());//error
		 extendList.add(new Super());//error
		 extendList.add(null);
		 /*
		  * 返回类型是确定的Self类，因为<? extends T> 只能用于方法返回，
		  * 告诉编译器此返参的类型的最小继承边界为T，
		  * T和T的父类都能接收，但是入参类型无法确定，只能接受null的传入
		  */
		 Self eself = extendList.get(0); 
		 Super esuper = extendList.get(0); //Self类型可以用Super接收
		 Son eson = extendList.get(0); //error:子类不能接收父类型参数
		 /**
		  * 参数类型下界是Self
		  */
		 //参数类型下界是Self
		 List<? super Self> superList = new ArrayList<>();
		 /*
		  * 只能放入T类型，且满足T类型的超类至少是Self，换句话说，就是只能放入Self的子类型
		  */
		 superList.add(new Son());//ok 
		 superList.add(new Self());//ok 本身类型也可以
		 superList.add(new Super());//error 超类不可以
		 superList.add(null);//ok
		 /*
		  * 返回类型是未知的， 因为<? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，
		  * 而返参只能用Object类接收
		  */
		 Object sObject = superList.get(0);//
		 Son sSon = superList.get(0);//error
		 Self sSlef = superList.get(0);//error
		 Super sSuper = superList.get(0);//error
		 /**
		  * 总结：
		  * 1. <? extends T> 只能用于方法返回，告诉编译器此返参的类型的最小继承边界为T，T和T的父类都能接收，
		  * 但是入参类型无法确定，只能接受null的传入
		  * 2. <? super T>只能用于限定方法入参，告诉编译器入参只能是T或其子类型，而返参只能用Object类接收
		  * 3. ? 既不能用于入参也不能用于返参
		  */
	}

}
