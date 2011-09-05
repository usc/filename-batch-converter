package org.usc.file.operater;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usc.file.operater.rules.SuffixConvertRule;

/**
 * 
 * 
 * @author <a href="http://www.blogjava.net/lishunli/" target="_blank">ShunLi</a>
 * @notes Created on 2010-12-11<br>
 *        Revision of last commit:$Revision$<br>
 *        Author of last commit:$Author$<br>
 *        Date of last commit:$Date$<br>
 *        <p>
 */
public class SuffixConvertRuleTest {
    private SuffixConvertRule rule = new SuffixConvertRule();
    private String fix = "[XXX]";
    private String newFix = "[李顺利]";

    @Test
    public void reNameBySuffixConvertRuleTest() {
        assertEquals("第十讲[李顺利].avi", rule.reNameByRule("第十讲.avi", "", newFix));
        assertEquals("XXX[李顺利].rar", rule.reNameByRule("XXX[XXX].rar", fix, newFix));
        assertEquals("XXX.rar", rule.reNameByRule("XXX[XXX].rar", fix, ""));
        assertEquals("XXX[XXX].rar", rule.reNameByRule("XXX[XXX].rar", "", ""));
        assertEquals("XXX[XXX].rar", rule.reNameByRule("XXX[XXX].rar", "[XX]", newFix));

        assertEquals("C:\\Temp\\XXX[XXX][李顺利].rar", rule.reNameByRule("C:\\Temp\\XXX[XXX].rar", "", newFix));
        assertEquals("C:\\Temp\\XXX[XXX].rar[李顺利]", rule.reNameByRule("C:\\Temp\\XXX[XXX].rar", "", newFix, true));
    }

}
