package org.jmx.mbean.status;

import java.lang.reflect.Constructor;
import java.util.Iterator;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.ReflectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态MBean
 * 
 * @author donald 2018年1月23日 下午9:28:21
 */
public class JvmDynamicStatus implements DynamicMBean {
	private static Logger log = LoggerFactory.getLogger(JvmDynamicStatus.class);
	private Long memerySize;
	private Integer coreCpuProssors;
	private String applicationName;
	private String statusInfo;

	public JvmDynamicStatus(String applicationName) {
		super();
		this.applicationName = applicationName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public Long getMemerySize() {
		memerySize = Runtime.getRuntime().freeMemory();
		return memerySize;
	}

	public Integer getCoreCpuProssors() {
		coreCpuProssors = Runtime.getRuntime().availableProcessors();
		return coreCpuProssors;
	}

	public String getStatusInfo() throws AttributeNotFoundException, MBeanException, ReflectionException {
		memerySize = (Long) getAttribute("memerySize");
		coreCpuProssors = (Integer) getAttribute("coreCpuProssors");
		this.statusInfo = String.format("%s应用状态，处理器数：%d，空闲内存大小：%f G", 
				applicationName, coreCpuProssors,
				memerySize.floatValue() / 1024 / 1024 / 1024);
		return statusInfo;

	}
    
	public String printStatusInfo() {
		String statusInfo = "";
		try {
//			statusInfo = getStatusInfo();
			statusInfo = (String) getAttribute("statusInfo");
			log.info(statusInfo);
		} catch (AttributeNotFoundException e) {
			log.error("Mbean属性没有发现异常",e);
		} catch (MBeanException e) {
			log.error("Mbean异常",e);
		} catch (ReflectionException e) {
			log.error("反射调用Mbean对象方法异常",e);
		}
		return statusInfo;
	}

	@Override
	public Object getAttribute(String attribute)
			throws AttributeNotFoundException, MBeanException, ReflectionException {
		if (attribute == null) {
			throw new AttributeNotFoundException();
		}
		if ("applicationName".equalsIgnoreCase(attribute)) {
			return getApplicationName();
		}
		if ("memerySize".equalsIgnoreCase(attribute)) {
			return getMemerySize();
		}
		if ("coreCpuProssors".equalsIgnoreCase(attribute)) {
			return getCoreCpuProssors();
		}
		if ("statusInfo".equalsIgnoreCase(attribute)) {
			return getStatusInfo();
		}
		throw new AttributeNotFoundException();
	}

	@Override
	public void setAttribute(Attribute attribute)
			throws AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException {
		String attributeName = attribute.getName();
		Object value = attribute.getValue();
		if ("applicationName".equalsIgnoreCase(attributeName)) {
			this.setApplicationName(String.valueOf(value));
			return;
		}
		throw new AttributeNotFoundException();

	}

	@Override
	public AttributeList getAttributes(String[] attributes) {
		if (attributes == null)  
            return null;  
        AttributeList resultList = new AttributeList();  
        // if attributeNames is empty, return an empty result list  
        if (attributes.length == 0)  
            return resultList;  
        for (int i = 0; i < attributes.length; i++) {  
				try {
					Object value;
					value = getAttribute(attributes[i]);
					resultList.add(new Attribute(attributes[i], value)); 
				} catch (AttributeNotFoundException e) {
					e.printStackTrace();
				} catch (MBeanException e) {
					e.printStackTrace();
				} catch (ReflectionException e) {
					e.printStackTrace();
				}  
                 
        }  
        return resultList;
	}

	@Override
	public AttributeList setAttributes(AttributeList attributes) {
		if (attributes == null)  
            return null;  
        AttributeList resultList = new AttributeList();  
        // if attributeNames is empty, nothing more to do  
        if (attributes.isEmpty())  
            return resultList;  
        // for each attribute, try to set it and add to the result list if successfull  
        for (Iterator i = attributes.iterator(); i.hasNext();) {  
            Attribute attr = (Attribute) i.next();  
            try {  
                setAttribute(attr);  
                String name = attr.getName();  
                Object value = getAttribute(name);  
                resultList.add(new Attribute(name, value));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return resultList;  
	}

	@Override
	public Object invoke(String actionName, Object[] params, String[] signature)
			throws MBeanException, ReflectionException {
		String result = "";
		if ("printStatusInfo".equals(actionName)) {
			result = printStatusInfo();
		}
		if ("setApplicationName".equals(actionName)) {
			try {
				setAttribute(new Attribute("applicationName",params[0]));
			} catch (AttributeNotFoundException e) {
				log.error("Mbean属性没有发现异常",e);
			} catch (InvalidAttributeValueException e) {
				log.error("无效属性异常",e);
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public MBeanInfo getMBeanInfo() {
		 //设定构造函数  
//        Constructor[] localConstructors = this.getClass().getConstructors(); 
		Constructor localConstructor = null;
        try {
			 localConstructor = this.getClass().getConstructor(String.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
        //构造方法
        MBeanConstructorInfo[] constructors  = new MBeanConstructorInfo[]{
        		new MBeanConstructorInfo("Constructs a JvmDynamicStatus with applicationName",localConstructor)};
		MBeanAttributeInfo[] attributes = new MBeanAttributeInfo[] {
				new MBeanAttributeInfo("applicationName", "String", "应用名", true, true, false),
				new MBeanAttributeInfo("memerySize", "Long", "可用内存（B）", true, false, false),
				new MBeanAttributeInfo("coreCpuProssors", "Integer", "可用处理器数量", true, false, false),
				new MBeanAttributeInfo("statusInfo", "String", "状态信息", true, false, false) };// 属性
		MBeanOperationInfo[] operationInfos = { new MBeanOperationInfo("printStatusInfo", "print now statsu", null,
				"void", MBeanOperationInfo.ACTION) };// 操作
		MBeanInfo info = new MBeanInfo(this.getClass().getName(), "JvmDynamicStatus", attributes, constructors, operationInfos,
				null);
		return info;
	}
}
