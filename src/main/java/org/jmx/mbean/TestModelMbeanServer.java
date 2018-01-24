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

import org.jmx.mbean.status.JvmModelStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试模板MBean
 * @author donald
 * 2018年1月23日
 * 下午12:51:24
 */
public class TestModelMbeanServer {
	private static Logger log = LoggerFactory.getLogger(TestModelMbeanServer.class);
	public static void main(String[] args) {
		MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName mbeanObjectName = new ObjectName("org.jmx.mbean:type=JvmModelStatus");
			JvmModelStatus jvmModelStatus = new JvmModelStatus("JvmStatus");
			mbeanServer.registerMBean(jvmModelStatus, mbeanObjectName);
			/**
			 * // doc comment inherited from MBeanServerConnection
			 */
			mbeanServer.invoke(mbeanObjectName, "printStatusInfo",null,null);
			/**
			 * setAttribute方法设置属性无效
			 */
//			mbeanServer.invoke(mbeanObjectName, "setAttribute",new Object[]{new Attribute("applicationName","JvmDynamicStatus")},new String[]{"javax.management.Attribute"});
			/**
			 * 调用invoke方法设置属性无效
			 */
		/*	Object[]  params = new Object[]{"JvmDynamicStatus"};
			String[]  signature = new String[]{"java.lang.String"};
			log.info("params:{},\nsignature:{}",params.getClass().getName(),signature.getClass().getName());
			mbeanServer.invoke(mbeanObjectName, "invoke",
					new Object[]{"setApplicationName",params,signature},
					new String[]{"java.lang.String","[Ljava.lang.Object;","[Ljava.lang.String;"});*/
			/**
			 * 直接调用setApplicationName方法设置属性有效
			 */
			mbeanServer.invoke(mbeanObjectName, "setApplicationName",new Object[]{"JvmModelStatus"},new String[]{"java.lang.String"});
			mbeanServer.invoke(mbeanObjectName, "printStatusInfo",null,null);
			Thread.sleep(Long.MAX_VALUE);
		} catch (InstanceAlreadyExistsException e) {
			log.error("Mbean对象已经存在异常",e);
		} catch (MalformedObjectNameException e) {
			log.error("Mbean对象命名异常",e);
		} catch (MBeanRegistrationException e) {
			log.error("Mbean对象注册异常",e);
		} catch (NotCompliantMBeanException e) {
			log.error("Mbean对象注册异常",e);
		} catch (InstanceNotFoundException e) {
			log.error("Mbean对象实例没有发现异常",e);
		} catch (ReflectionException e) {
			log.error("反射调用Mbean对象方法异常",e);
		} catch (MBeanException e) {
			log.error("Mbean异常",e);
		} catch (InterruptedException e) {
			log.error("线程中断异常",e);
		}
		
	}
}
