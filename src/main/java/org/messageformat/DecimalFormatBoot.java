package org.messageformat;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 我们经常要将数字进行格式化，比如取2位小数，这是最常见的。Java 提供 DecimalFormat 类，帮你用最快的速度将数字格式化为你需要的样式。
 * 
 * DecimalFormat 类主要靠 # 和 0 两种占位符号来指定数字长度。0 表示如果位数不足则以 0 填充，#
 * 表示只要有可能就把数字拉上这个位置。上面的例子包含了差不多所有的基本用法，如果你想了解更多，请参考 DecimalFormat 类的文档。
 * 
 * @author donald 2018年1月11日 下午3:31:48
 */
public class DecimalFormatBoot {
	private static final Logger log = LoggerFactory.getLogger(MessageFormateBoot.class);

	public static void main(String[] args) {
		double pi=3.1415927;//圆周率  
        //取一位整数  
        log.info("取一位整数  :{}",new DecimalFormat("0").format(pi));//3  
        //取一位整数和两位小数  
        log.info("取一位整数和两位小数  :{}",new DecimalFormat("0.00").format(pi));//3.14  
        //取两位整数和三位小数，整数不足部分以0填补。  
        log.info("取两位整数和三位小数，整数不足部分以0填补:{}",new DecimalFormat("00.000").format(pi));// 03.142  
        //取所有整数部分  
        log.info("取所有整数部分 :{}",new DecimalFormat("#").format(pi));//3  
        //以百分比方式计数，并取两位小数  
        log.info("以百分比方式计数，并取两位小数:{}",new DecimalFormat("#.##%").format(pi));//314.16%  
        
        long c=299792458;//光速  
        //显示为科学计数法，并取五位小数  
        log.info("显示为科学计数法，并取五位小数:{}",new DecimalFormat("#.#####E0").format(c));//2.99792E8  
        //显示为两位整数的科学计数法，并取四位小数  
        log.info("显示为两位整数的科学计数法，并取四位小数:{}",new DecimalFormat("00.####E0").format(c));//29.9792E7  
        //每三位以逗号进行分隔。  
        log.info("每三位以逗号进行分隔:{}",new DecimalFormat(",###").format(c));//299,792,458  
        //将格式嵌入文本  
        log.info("将格式嵌入文本 :{}",new DecimalFormat("光速大小为每秒,###米").format(c));  
	}
}
