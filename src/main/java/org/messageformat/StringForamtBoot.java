package org.messageformat;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author donald 2018年1月11日 下午12:38:44
 */
public class StringForamtBoot {
	private static final Logger log = LoggerFactory.getLogger(StringForamtBoot.class);

	public static void main(String[] args) {
		String str = null;
		str = String.format("Hi,%s", "donald");
		log.info(str);
		str = String.format("Hi,%s:%s.%s", "donald", "zhang", "han");
		log.info(str);
		System.out.printf("字母a的大写是：%c %n", 'A');
		System.out.printf("3>7的结果是：%b %n", 3 > 7);
		System.out.printf("100的一半是：%d %n", 100 / 2);
		System.out.printf("100的16进制数是：%x %n", 100);
		System.out.printf("100的8进制数是：%o %n", 100);
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
		System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
		System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
		System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
		System.out.printf("字母A的散列码是：%h %n", 'A');
		Date date = new Date();
		/**
		 * 在程序界面中经常需要显示时间和日期，但是其显示的
		 * 格式经常不尽人意，需要编写大量的代码经过各种算法才得到理想的日期与时间格式。字符串格式中还有%tx转换符没有详细介绍，
		 * 它是专门用来格式化日期和时 间的。%tx转换符中的x代表另外的处理日期和时间格式的转换符，它们的组合能够将日期和时间格式
		 * 化成多种格式。
		 * 
		 */
		// c的使用
		System.out.printf("全部日期和时间信息：%tc%n", date);
		// f的使用
		System.out.printf("年-月-日格式：%tF%n", date);
		// d的使用
		System.out.printf("月/日/年格式：%tD%n", date);
		// r的使用
		System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
		// t的使用
		System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
		// R的使用
		System.out.printf("HH:MM格式（24时制）：%tR", date);

	}
}
