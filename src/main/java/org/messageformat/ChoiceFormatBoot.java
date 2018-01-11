package org.messageformat;

import java.text.ChoiceFormat;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 相当于以数字为键，字符串为值的键值对。分别使用一组double类型的数组作为键，
 * 一组String类型的数组作为值，两数组相同索引值的元素作为一对。
 * 注意：当找不到对应的键值对时，则使用第一或最后一对键值对。
 * 
 * @author donald 2018年1月11日 下午12:37:32
 */
public class ChoiceFormatBoot {
	private static final Logger log = LoggerFactory.getLogger(ChoiceFormatBoot.class);
	public static void main(String[] args) {
		double[] limit = { 0, 1, 3 };
		String[] format = { "zero", "one", "three" };
		ChoiceFormat cf = new ChoiceFormat(limit, format);
		for (int i = 0; i < 4; ++i) {
			log.info("ChoiceFormat {}:{}",i+1,cf.format(i));
		}
		MessageFormat mf = new MessageFormat("now is times :{0},next times:{1}");
		mf.setFormatByArgumentIndex(0, cf);
		mf.setFormatByArgumentIndex(1, cf);
		for (int i = 0; i < 4; ++i){
			log.info(mf.format(new Object[]{new Integer(i),new Integer(i+1)}));
		}
	}
}
