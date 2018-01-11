/**
 * MessageFormat模式（主要部分）：
 * FormatElement: { ArgumentIndex } { ArgumentIndex , FormatType } {
 * ArgumentIndex , FormatType , FormatStyle }
 * FormatType: number
 * date
 * time
 * choice（需要使用ChoiceFormat）
 * FormatStyle: short medium long full integer currency percent
 * SubformatPattern（子模式）
 * 还以str为例，在这个字符串中：
 * 1、{0}和{1,number,short}和{2,number,#.#};都属于FormatElement，0,1,2是ArgumentIndex。
 * 2、{1,number,short}里面的number属于FormatType，short则属于FormatStyle。
 * 3、{1,number,#.#}里面的#.#就属于子格式模式。
 * 指定FormatType和FormatStyle是为了生成日期格式的值、不同精度的数字、百分比类型等等。
 * 
 * @author donald 2018年1月11日 上午9:15:49
 */
package org.messageformat;