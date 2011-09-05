package org.usc.file.operater.rules;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 小写转大写规则
 * 
 * @author <a href="http://www.blogjava.net/lishunli/" target="_blank">ShunLi</a>
 * @notes Created on 2010-12-11<br>
 *        Revision of last commit:$Revision$<br>
 *        Author of last commit:$Author$<br>
 *        Date of last commit:$Date$<br>
 *        <p>
 */
public class SmallToBigConvertRule implements ConvertRule {

    private static java.util.Map<String, String> SmallToBigMap = new HashMap<String, String>();

    static {
        SmallToBigMap.put(String.valueOf(0), "零");
        SmallToBigMap.put(String.valueOf(1), "一");
        SmallToBigMap.put(String.valueOf(2), "二");
        SmallToBigMap.put(String.valueOf(3), "三");
        SmallToBigMap.put(String.valueOf(4), "四");
        SmallToBigMap.put(String.valueOf(5), "五");
        SmallToBigMap.put(String.valueOf(6), "六");
        SmallToBigMap.put(String.valueOf(7), "七");
        SmallToBigMap.put(String.valueOf(8), "八");
        SmallToBigMap.put(String.valueOf(9), "九");
        SmallToBigMap.put(String.valueOf(10), "十");
        SmallToBigMap.put(String.valueOf(100), "百");
        SmallToBigMap.put(String.valueOf(1000), "千");
        SmallToBigMap.put(String.valueOf(10000), "万");
        SmallToBigMap.put(String.valueOf(100000000), "亿");
    }

    public static String format(String num) {

        // 先将末尾的零去掉
        String numString = String.valueOf(num).replaceAll("[.][0]+$", "");
        // 分别获取整数部分和小数部分的数字
        String intValue;
        String decValue = "";

        if (".".equals(num.trim())) {
            return ".";
        } else if (numString.indexOf(".") != -1) {
            String[] intValueArray = String.valueOf(numString).split("\\.");
            String[] decVauleArray = String.valueOf(num).split("\\.");

            intValue = (intValueArray != null && intValueArray.length > 0 ? intValueArray[0] : "0");
            decValue = (decVauleArray != null && decVauleArray.length > 1 ? decVauleArray[1] : "0");
        } else {
            intValue = String.valueOf(numString);
        }
        // 翻译整数部分。
        intValue = formatLong(Long.parseLong(String.valueOf(intValue)));
        // 翻译小数部分
        decValue = formatDecnum(decValue);
        String resultString = intValue;
        if (!decValue.equals(""))
            resultString = resultString + "点" + decValue;

        return resultString.replaceAll("^一十", "十");
    }

    /**
     * 将阿拉伯整数数字翻译为汉语小写数字。 其核心思想是按照中文的读法，从后往前每四个数字为一组。每一组最后要加上对应的单位，分别为万、亿等。 每一组中从后往前每个数字后面加上对应的单位，分别为个十百千。 每一组中如果出现零千、零百、零十的情况下去掉单位。 每组中若出现多个连续的零，则通读为一个零。 若每一组中若零位于开始或结尾的位置，则不读。
     * 
     * @param num
     * @return
     */
    public static String formatLong(Long num) {
        Long unit = 10000L;
        Long perUnit = 10000L;
        String sb = new String();
        String unitHeadString = "";
        while (num > 0) {
            Long temp = num % perUnit;
            sb = formatLongLess10000(temp) + sb;
            // 判断是否以单位表示为字符串首位，如果是，则去掉，替换为零
            if (!"".equals(unitHeadString))
                sb = sb.replaceAll("^" + unitHeadString, "零");
            num = num / perUnit;
            if (num > 0) {
                // 如果大于当前单位，则追加对应的单位
                unitHeadString = SmallToBigMap.get(String.valueOf(unit));
                sb = unitHeadString + sb;
            }
            unit = unit * perUnit;
        }
        return sb == null || sb.trim().length() == 0 ? "零" : sb;
    }

    /**
     * 将小于一万的整数转换为中文汉语小写
     * 
     * @param num
     * @return
     */
    public static String formatLongLess10000(Long num) {
        StringBuffer sb = new StringBuffer();
        for (Long unit = 1000L; unit > 0; unit = unit / 10) {
            Long _num = num / unit;
            // 追加数字翻译
            sb.append(SmallToBigMap.get(String.valueOf(_num)));
            if (unit > 1 && _num > 0)
                sb.append(SmallToBigMap.get(String.valueOf(unit)));
            num = num % unit;
        }
        // 先将连续的零联合为一个零，再去掉头部和末尾的零
        return sb.toString().replaceAll("[零]+", "零").replaceAll("^零", "").replaceAll("零$", "");
    }

    public static String formatDecnum(String num) {
        StringBuffer sBuffer = new StringBuffer();
        char[] chars = num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            sBuffer.append(SmallToBigMap.get(String.valueOf(chars[i])));
        }

        return sBuffer.toString();
    }

    public static String parseString(String oldName) {
        String[] str = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "." };
        List<String> num = Arrays.asList(str);

        StringBuffer stringBuffer = new StringBuffer();

        int index = oldName.lastIndexOf("\\");
        if (index != -1) {
            stringBuffer.append(oldName.substring(0, index + 1));
            oldName = oldName.substring(index + 1);
        }

        StringBuffer numBuffer = new StringBuffer();

        for (int i = 0; i < oldName.length(); i++) {
            if (num.contains(oldName.substring(i, i + 1))) {
                numBuffer.append(oldName.substring(i, i + 1));
            } else {
                if (numBuffer != null && numBuffer.length() > 0) {
                    String convertTemp = numBuffer.toString();
                    // 去掉最后的小数点
                    if (convertTemp.endsWith(".") && !".".equals(convertTemp)) {
                        convertTemp = convertTemp.substring(0, convertTemp.length() - 1);
                        stringBuffer.append(format(convertTemp)).append(".");
                    } else {
                        stringBuffer.append(format(convertTemp));
                    }

                    numBuffer.delete(0, numBuffer.length());
                } else {
                    stringBuffer.append(numBuffer.toString());
                    numBuffer.delete(0, numBuffer.length());
                }

                stringBuffer.append(oldName.substring(i, i + 1));
            }
        }

        if (numBuffer != null && numBuffer.length() > 0 && numBuffer.indexOf(".") == -1) {
            stringBuffer.append(format(numBuffer.toString()));
        } else {
            stringBuffer.append(numBuffer.toString());
        }

        return stringBuffer.toString();

    }

    @Override
    public String reNameByRule(String oldName) {
        return parseString(oldName);

    }

    @Override
    public String reNameByRule(String oldName, String fix, String newFix) {
        return reNameByRule(oldName);
    }

    @Override
    public String reNameByRule(String oldName, String fix, String newFix, Boolean isFolder) {
        return reNameByRule(oldName);
    }

}
