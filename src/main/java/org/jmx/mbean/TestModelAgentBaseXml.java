package org.jmx.mbean;

import java.io.InputStream;
import java.net.URL;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBean;

import org.apache.tomcat.util.modeler.ManagedBean;
import org.apache.tomcat.util.modeler.Registry;
import org.jmx.mbean.status.RunStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于Xml对需要暴露的接口进行配置：这个要结合TOMCAT的JMX体系使用， 具体配置见mbeans-descriptor.xml
 * 
 * @author donald 2018年1月24日 上午9:45:15
 */
public class TestModelAgentBaseXml {
	private static Logger log = LoggerFactory.getLogger(TestModelAgentBaseXml.class);
	private  Registry registry;
	private  MBeanServer mBeanServer;

	public TestModelAgentBaseXml() {
		registry = createRegistry();
		try {
			mBeanServer = registry.getMBeanServer();
		} catch (Throwable t) {
			t.printStackTrace(System.out);
			System.exit(1);
		}
	}

	public MBeanServer getMBeanServer() {
		return mBeanServer;
	}

	public Registry createRegistry() {
		Registry registry = null;
		try {
			URL url = TestModelAgentBaseXml.class.getResource("/jmx/mbeans-descriptor.xml");
			InputStream stream = url.openStream();
			registry = Registry.getRegistry(null, null);
			registry.loadMBeans(stream, Thread.currentThread().getContextClassLoader());
			stream.close();

		} catch (Throwable t) {
			System.out.println(t.toString());
		}
		return registry;
	}

	/**
	 * @param mBeanName
	 * @return
	 * @throws Exception
	 */
	public ModelMBean createModelMBean(String mBeanName) throws Exception {
		ManagedBean managed = registry.findManagedBean(mBeanName);
		if (managed == null) {
			System.out.println("ManagedBean null");
			return null;
		}
		ModelMBean mbean = (ModelMBean) managed.createMBean();
		return mbean;
	}

	/**
	 * @param objName
	 * @return
	 */
	private ObjectName createObjectName(String objName) {
		ObjectName objectName = null;
		String domain = mBeanServer.getDefaultDomain();
		try {
			objectName = new ObjectName(domain + ":type="+objName);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
		return objectName;
	}

	public static void main(String[] args) {
		TestModelAgentBaseXml agent = new TestModelAgentBaseXml();
		MBeanServer mBeanServer = agent.getMBeanServer();
		RunStatus runStatus = new RunStatus("home", Runtime.getRuntime().availableProcessors(),
				Runtime.getRuntime().freeMemory());
		System.out.println("Creating ObjectName");
		ObjectName objectName = agent.createObjectName("RunStatus");
		try {
			ModelMBean modelMBean = agent.createModelMBean("runStatus");
			modelMBean.setManagedResource(runStatus, "org.jmx.mbean.status.RunStatus");
			mBeanServer.registerMBean(modelMBean, objectName);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		// manage the bean
		try {
			Attribute applicationName = new Attribute("applicationName", "home");
			Attribute memerySize = new Attribute("memerySize", Runtime.getRuntime().freeMemory());
			Attribute coreCpuProssors = new Attribute("coreCpuProssors", Runtime.getRuntime().availableProcessors());
			mBeanServer.setAttribute(objectName, applicationName);
			mBeanServer.setAttribute(objectName, memerySize);
			mBeanServer.setAttribute(objectName, coreCpuProssors);
			String statusInfo = (String) mBeanServer.getAttribute(objectName, "statusInfo");
			log.info("应用运行状态：{}",statusInfo);
			mBeanServer.invoke(objectName, "printStatusInfo", null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
