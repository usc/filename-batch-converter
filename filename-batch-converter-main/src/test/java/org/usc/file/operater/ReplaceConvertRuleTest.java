package org.usc.file.operater;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.usc.file.operater.rules.ReplaceConvertRule;

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
public class ReplaceConvertRuleTest {
    private ReplaceConvertRule rule = new ReplaceConvertRule();
    private String fix = "[XXX]";
    private String newFix = "[李顺利]";

    @Test
    public void reNameBySuffixConvertRuleTest() {
        assertEquals("XXX[李顺利].rar", rule.reNameByRule("XXX[XXX].rar", fix, newFix));
        assertEquals("C:\\temp\\XXX[李顺利].rar", rule.reNameByRule("C:\\temp\\XXX[XXX].rar", fix, newFix, true));

        assertEquals("XXX[XXX].rar", rule.reNameByRule("XXX[XXX].rar", "", newFix));
        assertEquals("C:\\temp\\XXX[XXX].rar", rule.reNameByRule("C:\\temp\\XXX[XXX].rar", "", newFix, true));

    }

}
