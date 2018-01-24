package org.jmx.mbean;


import javax.management.MalformedObjectNameException;

import org.jmx.mbean.status.RunStatus;
import org.jmx.mbean.status.RunStatusBasePojoMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softee.management.exception.ManagementException;
import org.softee.management.helper.MBeanRegistration;

/**
 * 测试基于POJO-MBean注解的MBean
 * @author donald
 * 2018年1月23日
 * 下午12:51:24
 */
public class TestPojoMbean {
	private static Logger log = LoggerFactory.getLogger(TestPojoMbean.class);

	public static void main(String[] args) {
		try {
			RunStatusBasePojoMBean runStatus = new RunStatusBasePojoMBean("RunStatusBasePojoMBean",
					Runtime.getRuntime().availableProcessors(),Runtime.getRuntime().freeMemory());
			MBeanRegistration mBeanRegistration = new MBeanRegistration(runStatus);
			mBeanRegistration.register();
			log.info("===注册MBean完毕===");
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			log.error("线程中断异常",e);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (ManagementException e) {
			e.printStackTrace();
		}
		
	}
}
