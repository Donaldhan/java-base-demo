package org.messageformat;

import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MessageFormat模式
 * 格式： ArgumentIndex[,FormatType[,FormatStyle]]
 * ArgumentIndex ，是从0开始的入参位置索引。
 * FormatType ，指定使用不同的Format子类对入参进行格式化处理。值范围如下：
 * number：调用NumberFormat进行格式化
 * date：调用DateFormat进行格式化
 * time：调用DateFormat进行格式化
 * choice：调用ChoiceFormat进行格式化
 * FormatType ，设置FormatType中使用的格式化样式。值范围如下：
 * short，medium，long，full，integer，currency，percent，SubformPattern(子格式模式，形如#.##)
 * 注意： FormatType 和 FormatStyle 主要用于对日期时间、数字、百分比等进行格式化。
 * 
 * 由于静态方法 MessageFormat.format 内部是
 * public static String format(String pattern, Object ... arguments) {
 *        MessageFormat temp = new MessageFormat(pattern);
 *        return temp.format(arguments);
 * }
 * 因此若要多次格式同一个模式的字符串，那么创建一个MessageFormat实例在执行格式化操作比较好些。
 *  String message = "oh, {0} is a hero";  
 *  MessageFormat messageFormat = new MessageFormat(message);  
 *  Object[] array = new Object[]{"donald"};  
 *  String value = messageFormat.format(array);  
 *  System.out.println(value);  
 * 
 * @see java.util.Locale
 * @see Format
 * @see NumberFormat
 * @see DecimalFormat
 * @see DecimalFormatSymbols
 * @see ChoiceFormat
 * @see DateFormat
 * @see SimpleDateFormat
 * @author donald 2018年1月11日 上午9:16:28
 */
public class MessageFormateBoot {
	private static final Logger log = LoggerFactory.getLogger(MessageFormateBoot.class);

	public static void main(String[] args) {
		double num = 1.23;
		String numFormat = MessageFormat.format("the double is：{0,number,#.#}", num);
		log.info("num {} format：{}",num,numFormat);//1.2
		log.info("normal：{}", MessageFormat.format("{0}{1}", 1, 2)); // 12
	    log.info("单引号忽略：{}", MessageFormat.format("'{0}{1}", 1, 2)); //{0}{1}
	    log.info("双引号输出原始字符串：{}", MessageFormat.format("'{0}'{1}", 1, 2)); //{0}2
	    log.info("使用双引号输出大括号 ：{}", MessageFormat.format("'{'{0}}", 2)); // {2}
	    log.info("使用双引号输出大括号 ：{}", MessageFormat.format("'{'{0}}'}'", 2)); // {2}}

	}

}
