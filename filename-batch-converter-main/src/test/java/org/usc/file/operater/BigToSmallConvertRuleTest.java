package org.usc.file.operater;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usc.file.operater.rules.BigToSmallConvertRule;

public class BigToSmallConvertRuleTest {

    private BigToSmallConvertRule rule = new BigToSmallConvertRule();

    @Test
    public void reNameByBigToSmallConvertRuleTest() {

        assertEquals("第1讲", rule.reNameByRule("第一讲"));
        assertEquals("第10讲", rule.reNameByRule("第十讲"));
        assertEquals("第10讲", rule.reNameByRule("第一十讲"));
        assertEquals("第567讲", rule.reNameByRule("第五百六十七讲"));
        assertEquals("第10000讲", rule.reNameByRule("第一万讲"));
        assertEquals("第300000000讲", rule.reNameByRule("第三亿讲"));
        assertEquals("第4000000讲", rule.reNameByRule("第四百万讲"));
        assertEquals("第200000000000讲", rule.reNameByRule("第二千亿讲"));
        assertEquals("第100000000000000讲", rule.reNameByRule("第一百万亿讲"));

    }

}
