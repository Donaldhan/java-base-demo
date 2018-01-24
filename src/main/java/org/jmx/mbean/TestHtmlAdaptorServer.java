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
 * HtmlAdaptorServer 基于HTML的JMX管理代理，默认监听器端口为8082，如果想要修改端口可以以如下形式定义：
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
