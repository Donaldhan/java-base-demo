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

import org.jmx.mbean.status.RunStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试标准静态MBean
 * @author donald
 * 2018年1月23日
 * 下午12:51:24
 */
public class TestStandardMbeanServer {
	private static Logger log = LoggerFactory.getLogger(TestStandardMbeanServer.class);

	public static void main(String[] args) {
		// 创建MBeanServer
		MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			// 新建MBean ObjectName, 在MBeanServer里标识注册的MBean
			ObjectName mbeanObjectName = new ObjectName("org.jmx.mbean:type=RunStatusMbean");
			// 创建MBean
			RunStatus runStatus = new RunStatus("RunStatus",
					Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().freeMemory());
			// 在MBeanServer里注册MBean, 标识为ObjectName(org.jmx.mbean:type=RunStatusMbean)
			mbeanServer.registerMBean(runStatus, mbeanObjectName);
			// 在MBeanServer里调用已注册的RunStatus的getStatusInfo方法
			/**
			 * // doc comment inherited from MBeanServerConnection
			 */
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
