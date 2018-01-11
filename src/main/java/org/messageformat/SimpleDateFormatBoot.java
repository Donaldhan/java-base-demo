package org.messageformat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleDateFormat 是一个以国别敏感的方式格式化和分析数据的具体类。 它允许格式化 (date -> text)、语法分析 (text ->
 * date)和标准化。
 * 
 * SimpleDateFormat 允许以为日期-时间格式化选择任何用户指定的方式启动。 但是，希望用 DateFormat 中的
 * getTimeInstance、 getDateInstance 或 getDateTimeInstance 创建一个日期-时间格式化程序。
 * 每个类方法返回一个以缺省格式化方式初始化的日期／时间格式化程序。 可以根据需要用 applyPattern 方法修改格式化方式。
 * SimpleDateFormat函数语法：
 *  G 年代标志符
 *  y 年
 *  M 月
 *  d 日
 *  h 时 在上午或下午 (1~12)
 *  H 时 在一天中 (0~23)
 *  m 分
 *  s 秒
 *  S 毫秒
 *  E 星期
 *  D 一年中的第几天
 *  F 一月中第几个星期几
 *  w 一年中第几个星期
 *  W 一月中第几个星期
 *  a 上午 / 下午 标记符
 *  k 时 在一天中 (1~24)
 *  K 时 在上午或下午 (0~11)
 *  z 时区
 *  @author donald 2018年1月11日 下午3:17:00
 */

public class SimpleDateFormatBoot {
	private static final Logger log = LoggerFactory.getLogger(SimpleDateFormatBoot.class);
	public static void main(String[] args) {
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm");
        SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//等价于now.toLocaleString()
        SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        SimpleDateFormat myFmt4=new SimpleDateFormat("一年中的第 D 天 一年中第w个星期 一月中第W个星期 在一天中k时 z时区");
        Date now=new Date();
        log.info("yyyy年MM月dd日 HH时mm分ss秒:{}",myFmt.format(now));
        log.info("yy/MM/dd HH:mm:{}",myFmt1.format(now));
        log.info("yyyy-MM-dd HH:mm:ss:{}",myFmt2.format(now));
        log.info("yyyy年MM月dd日 HH时mm分ss秒 E :{}",myFmt3.format(now));
        log.info(":{}",myFmt4.format(now));
        log.info("GMT:{}",now.toGMTString());
        log.info("Locale:{}",now.toLocaleString());
        log.info("ToString:{}",now.toString());

	}

}
