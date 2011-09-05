package org.usc.file.operater;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usc.file.operater.rules.SimpleBigToSmallConvertRule;

public class SimpleBigToSmallConvertRuleTest {

    private SimpleBigToSmallConvertRule rule = new SimpleBigToSmallConvertRule();

    @Test
    public void reNameBySimpleBigToSmallConvertRuleTest() {

        assertEquals("第1讲", rule.reNameByRule("第一讲"));
        assertEquals("第10讲", rule.reNameByRule("第十讲"));
        assertEquals("第10讲", rule.reNameByRule("第一十讲"));
        assertEquals("第567讲", rule.reNameByRule("第五百六十七讲"));

    }

}
