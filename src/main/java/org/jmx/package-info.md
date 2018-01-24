[JMX学习笔记(一)-MBean](http://blog.csdn.net/qiao000_000/article/details/6061808)      
[JMX和MBean以及pojo-mbean学习](http://iamzhongyong.iteye.com/blog/1900613)  
[从零开始玩转JMX(一)——简介和Standard MBean](http://blog.csdn.net/u013256816/article/details/52800742)  
[Java方法签名格式](http://blog.csdn.net/okingniko/article/details/51121723)  
[开源框架是如何通过JMX来做监控的(一)-JMX简介和Standard MBean][]  
[JMX—标准MBean和模型MBean演示][]

[开源框架是如何通过JMX来做监控的(一)-JMX简介和Standard MBean]:(https://www.cnblogs.com/trust-freedom/p/6842332.html) "开源框架是如何通过JMX来做监控的"''
[JMX—标准MBean和模型MBean演示]:http://smallbug-vip.iteye.com/blog/2276132 "JMX—标准MBean和模型MBean演示"

# JMX以及Mbean中的概念

# MBean
通常是一个java类，他提供接口，可以是这个类具有管理 功能。
Standard Mbean是最简单的MBean，他能管理的资源必须定义在接口中，然后MBean必须实现这个接口，命名必须遵守一定的规范。
## MBean interface
* 名字必须以MBean结尾
* 必须与签名暴漏属性和操作
## MBean implement
* 必须有个名字和接口中"MBean"的前缀相同
* 实现接口中的方法

Dynamic Mbean必须实现DynamicMBean，所有的属性和方法都在运行时定义。也就是说它有什么属性和方法是可以动态改变的。动态MBean主要利用一 些辅助类（构造函数类MBeanConstructorInfo、属类MBeanAttributeInfo、方法类 MBeanOperationInfo）来完成这个功能，所有的动态MBean必须实现DynamicMBean接。

* 必须实现DynamicMBean接口
* 在实现类中实现DynamicMBean中的方法

[JMX一步步来]　4、动态MBean：DynamicMBean](http://bluestar.iteye.com/blog/58083)

# MBean Server

管理MBean的一个java类，需要向MBean Server中注册一个MBean之后，这个MBean才会具有管理功能，MBeanServer还提供了查询和注解监听器的功能，不同的JMX实现中MBean Server实现也不同。

# JMX agent

agent是为了管理一些列的MBean而提供的一系列服务。agent可以利用protocal adapters（例如HTTP）和connectors使不同的客户端可以访问MBean。

#Open MBean 和Mode MBean的作用

​其实这两类也是动态的MBean，OpenMBean和其他MBean不同之处在于公共接口的返回值有所限制，只能是基本类型或者openmbean包内的类型。（可能主要考虑到远端管理系统，可能不具备MBean接口中的特殊的类）

普通的动态的MBean缺少一些管理系统的支持，例如MBean的状态持久化或者日志记录等，因为JMX厂商提供不同的MobleMBean的实现，方便用户进行操作。使用 *pojo-mbean* 来减少MBean繁琐的操作。

基于POJO-MBean来做JMX，通过注解来实现一些繁琐的步骤，让动态MBean不在那么蛋疼。[pojo-mbean][]提供了一种基于注解来减少繁琐的MBean的方式。pojo-mbean没有依赖任何第三方的包，需要JDK5以上，通过注解标示一个类，是这个类作为MBean，包括属性、操作以及参数等。

[pojo-mbean]:http://code.google.com/p/pojo-mbean/ "pojo-mbean"   
版本依赖：
```xml
<dependency>
    <groupId>org.softee</groupId>
    <artifactId>pojo-mbean</artifactId>
    <version>1.1</version>
</dependency>
```
主要原理：

* 在注册MBeanServer的时候，扫描注解的类文件
* 然后转换MBean的Meta信息，把相关的信息通过反射完成
* 抽象类实现DynamicMBean


# 命令规范
MBean其实也是JavaBean的一种，但是MBean往往代表的是JMX中的一种可以被管理的资源。MBean会通过接口定义，给出这些资源的一些特定操作：
    属性的读和写操作；get/set
    可以被执行的操作；非以set和get开头非方法
    关于自己的描述信息；
MBean可以看作是JavaBean的一种特殊形式，其定义是符合JavaBean的规范的。但是MBean在定义是，首先需要定义一个名字结尾为“MBean”的接口，
例如：HelloMBean，然后这个接口的实现类的名字必须叫做Hello。
注意两者要在同一包下。
而MXBean与MBean的区别主要是在于在接口中会引用到一些其他类型的类时，其表现方式的不一样。在MXBean中，如果一个MXBean的接口定义了一个属性是一个自定义类型，
如MemoryMXBean中定义了heapMemoryUsage属性，这个属性是MemoryUsage类型的，当JMX使用这个MXBean时，这个MemoryUsage就会被转换成一种标准的类型，这些类型被称为开放类型

具体监控可以使用基于HTML或RMI或者JMX。
HtmlAdaptorServer 基于HTML的JMX管理，默认监听器端口为8082，如果想要修改端口可以以如下形式定义：
HtmlAdaptorServer adapterServer = new HtmlAdaptorServer(8082);
访问HMTL的JMX:http://localhost:8082/
需要添加以下依赖：
```xml
<dependency>  
    <groupId>com.sun.jdmk</groupId>  
    <artifactId>jmxtools</artifactId>  
    <version>1.2.1</version>  
</dependency>  
```
具体可以参考[JMX—标准MBean和模型MBean演示][],同时我们也可以暴露的接口进行配置。如下为参考实例
```Java
package org.jmx.mbean;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.jmx.mbean.status.JvmDynamicStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jdmk.comm.HtmlAdaptorServer;

/**
 * HtmlAdaptorServer 基于HTML的JMX管理，默认监听器端口为8082，如果想要修改端口可以以如下形式定义：
 * HtmlAdaptorServer adapterServer = new HtmlAdaptorServer(8082);
 * 访问HMTL的JMX:http://localhost:8082/
 * @author donald 2018年1月24日 上午9:11:30
 */
public class TestHtmlAdaptorServer {
	private static Logger log = LoggerFactory.getLogger(TestHtmlAdaptorServer.class);

	public static void main(String[] args) {
		MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName mbeanObjectName = new ObjectName("org.jmx.mbean:type=JvmDynamicStatus");
			JvmDynamicStatus jvmDynamicStatus = new JvmDynamicStatus("JvmStatus");
			mbeanServer.registerMBean(jvmDynamicStatus, mbeanObjectName);
			ObjectName adapterName = new ObjectName("org.jmx.mbean:type=HtmlAdaptorServer,port=8082");
			HtmlAdaptorServer adapterServer = new HtmlAdaptorServer(8082);
			mbeanServer.registerMBean(adapterServer, adapterName);
			adapterServer.start();
			/**
			 * // doc comment inherited from MBeanServerConnection
			 */
			mbeanServer.invoke(mbeanObjectName, "printStatusInfo", null, null);
			/**
			 * 直接调用setApplicationName方法设置属性有效
			 */
			mbeanServer.invoke(mbeanObjectName, "setApplicationName", new Object[] { "JvmDynamicStatus" },
					new String[] { "java.lang.String" });
			mbeanServer.invoke(mbeanObjectName, "printStatusInfo", null, null);
			Thread.sleep(Long.MAX_VALUE);
		} catch (InstanceAlreadyExistsException e) {
			log.error("Mbean对象已经存在异常", e);
		} catch (MalformedObjectNameException e) {
			log.error("Mbean对象命名异常", e);
		} catch (MBeanRegistrationException e) {
			log.error("Mbean对象注册异常", e);
		} catch (NotCompliantMBeanException e) {
			log.error("Mbean对象注册异常", e);
		} catch (InstanceNotFoundException e) {
			log.error("Mbean对象实例没有发现异常", e);
		} catch (ReflectionException e) {
			log.error("反射调用Mbean对象方法异常", e);
		} catch (MBeanException e) {
			log.error("Mbean异常", e);
		} catch (InterruptedException e) {
			log.error("线程中断异常", e);
		}

	}
}
```
RMI或者JMX可以参考
[开源框架是如何通过JMX来做监控的(一)][]

关于基于XMl的配置，可以基于TOMCAT和JBOSS，JETTY，WEB-LOGIC参见
[Tomcat7中的JMX使用（二）Dynamic MBean](http://blog.csdn.net/caomiao2006/article/details/51527665)
[JMX一步步来]　9、基于JBoss来写MBean](http://damies.iteye.com/blog/51808)
[tomcat  mbeans-descriptor.xml](http://jgo.shenhe.gov.cn/docs/mbeans-descriptor-howto.html)
