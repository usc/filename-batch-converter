package org.usc.file.operater;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usc.file.operater.rules.SmallToBigConvertRule;

public class SmallToBigConvertRuleTest {

    private SmallToBigConvertRule rule = new SmallToBigConvertRule();

    @Test
    public void reNameByBigToSmallConvertRuleTest() {

        assertEquals("一.txt", rule.reNameByRule("1.txt"));
        assertEquals("一", rule.reNameByRule("1"));
        assertEquals("一续二.txt", rule.reNameByRule("1续2.txt"));
        assertEquals("一点一.txt", rule.reNameByRule("1.1.txt"));
        assertEquals("第一讲", rule.reNameByRule("第1讲"));
        assertEquals("第十讲", rule.reNameByRule("第10讲"));
        assertEquals("第五百六十七讲", rule.reNameByRule("第567讲"));
        assertEquals("第一万讲", rule.reNameByRule("第10000讲"));
        assertEquals("第三十亿零讲", rule.reNameByRule("第3000000000讲"));
        assertEquals("第四百万讲", rule.reNameByRule("第4000000讲"));
        assertEquals("第二千亿零讲", rule.reNameByRule("第200000000000讲"));
        // assertEquals("第一百万亿讲",rule.reNameByRule("第100000000000000讲"));
        assertEquals("第十二讲.版本控制V0.10", rule.reNameByRule("第12讲.版本控制V0.10"));

    }

}
