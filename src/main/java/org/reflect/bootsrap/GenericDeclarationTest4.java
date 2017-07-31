package org.reflect.bootsrap;
/**
 * 说到TypeVariable类，就不得不提及Java-Type体系中另一个比较
 * 重要的接口---GenericDeclaration；含义为：声明类型变量的所有实体的公共接口；
 * 也就是说该接口定义了哪些地方可以定义类型变量（泛型）；
 * 通过查看源码发现，GenericDeclaration下有三个子类，
 * 分别为Class、Method、Constructor；也就是说，
 * 我们定义泛型只能在一个类中这3个地方自定义泛型；
 * 此时，我们不禁要问，我们不是经常在类中的属性声明泛型吗，
 * 怎么Field没有实现 GenericDeclaration接口呢？
 * 其实，我们在Field中并没有声明泛型，而是在使用泛型而已！不信，我们实际上代码来看看！
 * 1.首先在Class上定义泛型;{@link GenericDeclarationTest}
 * 2.在Class上定义泛型，直接在构造方法上定义泛型;{@link GenericDeclarationTest2}
 * 3.没有在Class定义泛型，直接在普通方法上定义泛型;{@link GenericDeclarationTest3}
 * 4.直接在属性上定义,对于这种情况，如果不在Class上定义，属性上并不能直接使用！所以，
 * 这也是之前说的属性上并不是定义泛型，而是使用泛型，
 * 所以Field并没有实现GenericDeclaration接口！
 * @author donald
 * 2017年7月29日
 * 上午11:25:59
 */
public class GenericDeclarationTest4<T> {
	/**
	 * 我们看到，如果不在Class上定义，属性上并不能直接使用！
	 * 所以，这也是我之前说的属性上并不是定义泛型，而是使用泛型，
	 * 所以Field并没有实现GenericDeclaration接口！
	 */
	private T t;
}
