package org.jmx.mbean.status;

/**
 * @author donald
 * 2018年1月23日
 * 下午12:51:28
 */
public interface RunStatusMBean {
	public Long getMemerySize();
	/**
	 * @param memerySize
	 */
	public void setMemerySize(Long memerySize);
	
	public Integer getCoreCpuProssors();
	/**
	 * @param coreCpuProssors
	 */
	public void setCoreCpuProssors(Integer coreCpuProssors);
	
	public String getApplicationName();
	/**
	 * @param applicationName
	 */
	public void setApplicationName(String applicationName);
	/**
	 * 获取状态 信息
	 * @return
	 */
	String getStatusInfo();
	/**
	 * 打印状态 信息
	 * @return
	 */
	void printStatusInfo();
}
