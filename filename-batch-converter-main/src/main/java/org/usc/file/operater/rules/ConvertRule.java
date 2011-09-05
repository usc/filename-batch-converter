package org.usc.file.operater.rules;

/**
 * 转换规则接口
 * 
 * @author <a href="http://www.blogjava.net/lishunli/" target="_blank">ShunLi</a>
 * @notes Created on 2010-12-11<br>
 *        Revision of last commit:$Revision$<br>
 *        Author of last commit:$Author$<br>
 *        Date of last commit:$Date$<br>
 *        <p>
 */
public abstract interface ConvertRule {

    public String reNameByRule(String oldName);

    public String reNameByRule(String oldName, String fix, String newFix);

    public String reNameByRule(String oldName, String fix, String newFix, Boolean isFolder);

}
