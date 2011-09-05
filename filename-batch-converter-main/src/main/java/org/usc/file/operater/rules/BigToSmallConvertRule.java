package org.usc.file.operater.rules;

import java.util.HashMap;
import java.util.Map;

/**
 * 大写转小写规则
 * 
 * @author <a href="http://www.blogjava.net/lishunli/" target="_blank">ShunLi</a>
 * @notes Created on 2010-12-11<br>
 *        Revision of last commit:$Revision$<br>
 *        Author of last commit:$Author$<br>
 *        Date of last commit:$Date$<br>
 *        <p>
 */
public class BigToSmallConvertRule implements ConvertRule {
    private static Map<String, Long> numberMap = new HashMap<String, Long>();
    private static Map<String, Long> unitMap = new HashMap<String, Long>();
    private static Map<String, Long> levelMap = new HashMap<String, Long>();
    private static Map<String, Long> upperLevelMap = new HashMap<String, Long>();

    static {
        numberMap.put("零", 0L);
        numberMap.put("一", 1L);
        numberMap.put("二", 2L);
        numberMap.put("三", 3L);
        numberMap.put("四", 4L);
        numberMap.put("五", 5L);
        numberMap.put("六", 6L);
        numberMap.put("七", 7L);
        numberMap.put("八", 8L);
        numberMap.put("九", 9L);
        numberMap.put("十", 10L);

        unitMap.put("十", 10L);
        unitMap.put("百", 100L);
        unitMap.put("千", 1000L);
        unitMap.put("万", 10000L);
        unitMap.put("亿", 100000000L);

        levelMap.put("万", 10000L);
        levelMap.put("亿", 100000000L);

        upperLevelMap.put("亿", 100000000L);
    }

    @Override
    public String reNameByRule(String oldName) {
        Long sum = 0L;

        StringBuffer newName = new StringBuffer();

        int index = oldName.lastIndexOf("\\");
        if (index != -1) {
            newName.append(oldName.substring(0, index + 1));
            oldName = oldName.substring(index + 1);
        }

        int size = oldName.length();
        for (int i = 0; i < size; i++) {

            Boolean flag = false;

            if (numberMap.keySet().contains(oldName.substring(i, i + 1))) {
                Long small = numberMap.get(oldName.substring(i, i + 1));

                Long big = 1L;

                if ((i + 1 < size) && unitMap.keySet().contains(oldName.substring(i + 1, i + 2))) {
                    big = unitMap.get(oldName.substring(i + 1, i + 2));

                    if ("万".equals(oldName.substring(i + 1, i + 2)) || "亿".equals(oldName.substring(i + 1, i + 2))) {
                        small += sum;
                        sum = 0L;
                    }

                    if ((i + 2 < size) && levelMap.keySet().contains(oldName.substring(i + 2, i + 3))) {
                        small = small * big;
                        big = levelMap.get(oldName.substring(i + 2, i + 3));

                        if ((i + 3 < size) && upperLevelMap.keySet().contains(oldName.substring(i + 3, i + 4))) {
                            small = small * big;
                            big = upperLevelMap.get(oldName.substring(i + 3, i + 4));
                            i++;
                        }

                        i++;
                    }

                    i++;
                }

                sum = sum + small * big;

                flag = true;
            }

            if (!flag) {
                if (sum != 0) {
                    newName.append(sum.toString());
                }
                newName.append(oldName.substring(i, i + 1));
                sum = 0L;
            } else {
                if (sum == 0 && "零".equals(oldName.substring(i, i + 1))) {
                    newName.append(sum.toString());
                }
            }

        }
        if (sum != 0) {
            newName.append(sum.toString());
        }

        return newName.toString();
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
