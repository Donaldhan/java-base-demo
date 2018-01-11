package org.messageformat;

import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 在使用String.valueof(Double) 格式Double的时候，当值过大，将会输出数值的科学计数法。
 * 为了避免这种情况，我们可以使用NumberFormat进行格式化。 
 * nf.setGroupingUsed(false);//剔除分组','
 * nf.setMaximumFractionDigits(6);//设置最多小数点位数
 * nf.setMinimumFractionDigits(6);//设置最少小数点位数
 * 
 * @author donald 2018年1月11日 下午3:13:07
 */
public class NumberFormatBoot {
	private static final Logger log = LoggerFactory.getLogger(MessageFormateBoot.class);

	public static void main(String[] args) {
		Double num = 23323.3323232323;
		log.info("String vaulue:{}", String.valueOf(456465465555555.00));//4.56465465555555E14
		// 返回当前缺省语言环境的缺省数值格式。
		String result = NumberFormat.getInstance().format(num);//23,323.332
		log.info("缺省数值格式:{}", result);
		// 返回当前缺省语言环境的通用货币格式
		result = NumberFormat.getCurrencyInstance().format(num);//￥23,323.33
		log.info("缺省语言环境的通用货币格:{}", result);
		// 返回当前缺省语言环境的通用数值格式。
		result = NumberFormat.getNumberInstance().format(num);//23,323.332
		log.info("缺省语言环境的通用数值格式:{}", result);
		// 返回当前缺省语言环境的百分比格式。
		result = NumberFormat.getPercentInstance().format(0.3434);//34%
		log.info("缺省语言环境的百分比格式:{}", result);
		NumberFormat format = NumberFormat.getInstance();
		format.setGroupingUsed(false);// 剔除分组','
		format.setMinimumFractionDigits(3);// 设置数值的小数部分允许的最大位数
		format.setMaximumFractionDigits(5);// 设置数值的整数部分允许的最大位数
		format.setMinimumIntegerDigits(0);// 设置数值的小数部分允许的最小位数
		format.setMaximumIntegerDigits(6);// 设置数值的整数部分允许的最小位数
		log.info(format.format(2132323213.23266666666));//323213.23267
	}
}
