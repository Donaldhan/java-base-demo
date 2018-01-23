package org.jmx.mbean.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注意对于标注MBean模式，MBean接口要与具体的实现在同一包；
 * 并且实现MBean的命令为MBean接口的前缀。
 * 比如：RunStatusMBean-》RunStatus。
 * @author donald
 * 2018年1月23日
 * 下午12:51:21
 */
public class RunStatus implements RunStatusMBean {
	private static Logger log = LoggerFactory.getLogger(RunStatus.class);

	private Long memerySize;
	private Integer coreCpuProssors;
	private String applicationName;
	private String statusInfo;
	
	public RunStatus(String applicationName, Integer coreCpuProssors, Long memerySize) {
		super();
		this.applicationName = applicationName;
		this.coreCpuProssors = coreCpuProssors;
		this.memerySize = memerySize;
	}
	@Override
	public Long getMemerySize() {
		return memerySize;
	}
	@Override
	public void setMemerySize(Long memerySize) {
		this.memerySize = memerySize;
	}
	@Override
	public Integer getCoreCpuProssors() {
		return coreCpuProssors;
	}
	@Override
	public void setCoreCpuProssors(Integer coreCpuProssors) {
		this.coreCpuProssors = coreCpuProssors;
	}
	@Override
	public String getApplicationName() {
		return applicationName;
	}
	@Override
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	@Override
	public void printStatusInfo() {
		String statusInfo = getStatusInfo();
		log.info(statusInfo);
	}
	@Override
	public String getStatusInfo() {
		this.statusInfo = String.format("%s应用状态，处理器数：%d，空闲内存大小：%f G",
				applicationName,coreCpuProssors,memerySize.floatValue()/1024/1024/1024);
		return statusInfo;
		
	}
}
