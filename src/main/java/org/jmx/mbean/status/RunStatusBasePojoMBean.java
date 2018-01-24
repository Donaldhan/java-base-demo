package org.jmx.mbean.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softee.management.annotation.Description;
import org.softee.management.annotation.MBean;
import org.softee.management.annotation.ManagedAttribute;
import org.softee.management.annotation.ManagedOperation;

/**
 * 基于pojo-mbean的MBean
 * @author donald
 * 2018年1月23日
 * 下午12:51:21
 */
@MBean(objectName="pojo-agent:name=RunStatusBasePojoMBean")
@Description("RunStatus MBean Drived by pojo-mbean") 
public class RunStatusBasePojoMBean {
	private static Logger log = LoggerFactory.getLogger(RunStatusBasePojoMBean.class);

	private Long memerySize;
	private Integer coreCpuProssors;
	private String applicationName;
	private String statusInfo;
	
	public RunStatusBasePojoMBean(String applicationName, Integer coreCpuProssors, Long memerySize) {
		super();
		this.applicationName = applicationName;
		this.coreCpuProssors = coreCpuProssors;
		this.memerySize = memerySize;
	}
	@ManagedAttribute
	public Long getMemerySize() {
		return memerySize;
	}
	@ManagedAttribute 
	@Description("free memery size")
	public void setMemerySize(Long memerySize) {
		this.memerySize = memerySize;
	}
	@ManagedAttribute
	public Integer getCoreCpuProssors() {
		return coreCpuProssors;
	}
	@ManagedAttribute 
	@Description("available core prossors")
	public void setCoreCpuProssors(Integer coreCpuProssors) {
		this.coreCpuProssors = coreCpuProssors;
	}
	@ManagedAttribute
	public String getApplicationName() {
		return applicationName;
	}
	@ManagedAttribute 
	@Description("application name")
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	@ManagedAttribute
	public String getStatusInfo() {
		this.statusInfo = String.format("%s应用状态，处理器数：%d，空闲内存大小：%f G",
				applicationName,coreCpuProssors,memerySize.floatValue()/1024/1024/1024);
		return statusInfo;
		
	}
	@ManagedOperation
    @Description("print application runnig status") 
	public void printStatusInfo() {
		String statusInfo = getStatusInfo();
		log.info(statusInfo);
	}
}
