package org.usc.file.operater.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.usc.file.operater.rules.ConvertFactory;
import org.usc.file.operater.rules.ConvertRule;
import org.usc.file.operater.rules.Rule;

/**
 * 文件操作工具
 * 
 * @author <a href="http://www.blogjava.net/lishunli/" target="_blank">ShunLi</a>
 * @notes Created on 2010-12-11<br>
 *        Revision of last commit:$Revision$<br>
 *        Author of last commit:$Author$<br>
 *        Date of last commit:$Date$<br>
 *        <p>
 */
public class FileOperaterTool {
    private ConvertRule convertRule;
    private StatisticsInfo statisticsInfo;

    public FileOperaterTool() {
    }

    public FileOperaterTool(Rule rule) {
        this.init();
        this.convertRule = ConvertFactory.createConvertRule(rule);
    }

    public void init() {
        this.statisticsInfo = new StatisticsInfo();
    }

    public StringBuffer getStatistics() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.statisticsInfo.toString());
        return sb;
    }

    /**
     * 修改path路径下所有的文件名
     * 
     * @param path
     */
    public String fileRename(String path, Boolean isConvertFolder) {

        StringBuffer info = new StringBuffer();

        File file = new File(path);

        String[] tempList = file.list();

        if (tempList != null) {
            File temp = null;

            if (tempList.length == 0) {
                info.append("\"" + path + "\"路径下没有文件" + "\n");
            }

            for (int i = 0; i < tempList.length; i++) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if (temp.isFile()) {
                    this.statisticsInfo.addSumFileNum();
                    info.append(fileRename(temp) + "\n");
                }
                if (temp.isDirectory()) {
                    this.statisticsInfo.addSumFolderNum();
                    String folderName = path + "\\" + tempList[i];

                    info.append(fileRename(folderName, isConvertFolder));
                    if (isConvertFolder) {
                        info.append(folderRename(folderName) + "\n\n");
                    }
                }
            }
        } else {
            info.append("\"" + path + "\"路径不存在" + "\n");
        }

        return info.toString();
    }

    /**
     * 修改path路径下所有的文件名
     * 
     * @param path
     */
    public String fileRename(String path, String fix, String newFix, Boolean isConvertFolder) {

        StringBuffer info = new StringBuffer();

        File file = new File(path);

        String[] tempList = file.list();

        if (tempList != null) {
            File temp = null;

            if (tempList.length == 0) {
                info.append("\"" + path + "\"路径下没有文件" + "\n");
            }

            for (int i = 0; i < tempList.length; i++) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if (temp.isFile()) {
                    this.statisticsInfo.addSumFileNum();
                    info.append(fileRename(temp, fix, newFix) + "\n");
                }
                if (temp.isDirectory()) {
                    this.statisticsInfo.addSumFolderNum();
                    String folderName = path + "\\" + tempList[i];

                    info.append(fileRename(folderName, fix, newFix, isConvertFolder));
                    if (isConvertFolder) {
                        info.append(folderRename(folderName, fix, newFix, true) + "\n\n");
                    }
                }
            }
        } else {
            info.append("\"" + path + "\"路径不存在" + "\n");
        }

        return info.toString();
    }

    /**
     * 修改文件名
     * 
     * @param file
     *            文件
     * @return N/A
     */
    private String fileRename(File file) {

        String info = null;

        String oldName = file.getName();
        String newName = this.convertRule.reNameByRule(oldName);

        if (!oldName.equals(newName)) {
            Boolean result = file.renameTo(new File(file.getParent() + "\\" + newName));

            if (!result) {
                info = "文件\"" + file.getParent() + "\\" + oldName + "\"转换失败，请查看是否存在文件重名";
            } else {
                this.statisticsInfo.addConvertFileNum();
                info = "文件\"" + file.getParent() + "\\" + oldName + "\"转换为\"" + file.getParent() + "\\" + newName + "\"";
            }

        } else {
            info = "文件\"" + file.getParent() + "\\" + oldName + "\"不需要转换";
        }

        return info;

    }

    /**
     * 修改文件夹名
     * 
     * @param file
     *            文件夹
     * @return String msg
     */
    private String folderRename(String folderName) {
        String info = null;

        String oldPath = folderName;
        String newPath = this.convertRule.reNameByRule(oldPath);

        if (!oldPath.equals(newPath)) {
            info = moveFolder(oldPath, newPath);
        } else {
            info = "文件夹\"" + oldPath + "\"不需要转换";
        }

        return info;

    }

    /**
     * 修改文件名
     * 
     * @param file
     *            文件
     * @return N/A
     */
    private String fileRename(File file, String fix, String newFix) {

        String info = null;

        String oldName = file.getName();
        String newName = this.convertRule.reNameByRule(oldName, fix, newFix);

        if (!oldName.equals(newName)) {
            Boolean result = file.renameTo(new File(file.getParent() + "\\" + newName));

            if (!result) {
                info = "文件\"" + file.getParent() + "\\" + oldName + "\"转换失败，请查看是否存在文件重名";
            } else {
                this.statisticsInfo.addConvertFileNum();
                info = "文件\"" + file.getParent() + "\\" + oldName + "\"转换为\"" + file.getParent() + "\\" + newName + "\"";
            }

        } else {
            info = "文件\"" + file.getParent() + "\\" + oldName + "\"不需要转换";
        }

        return info;

    }

    /**
     * 修改文件夹名
     * 
     * @param file
     *            文件夹
     * @return String msg
     */
    private String folderRename(String folderName, String fix, String newFix, Boolean isFolder) {
        String info = null;

        String oldPath = folderName;
        String newPath = this.convertRule.reNameByRule(oldPath, fix, newFix, isFolder);

        if (!oldPath.equals(newPath)) {
            info = moveFolder(oldPath, newPath);
        } else {
            info = "文件夹\"" + oldPath + "\"不需要转换";
        }

        return info;
    }

    // /////////////////
    // Internal use
    // ////////////////
    private String moveFolder(String oldPath, String newPath) {
        StringBuffer infos = new StringBuffer();
        try {
            FileUtils.moveDirectory(new File(oldPath), new File(newPath));

            this.statisticsInfo.addConvertFolderNum();

            infos.append("文件夹\"" + oldPath + "\"转换为\"" + newPath + "\"");
        } catch (IOException e) {
            infos.append("文件夹\"" + oldPath + "\"转换失败，请查看是否存在文件夹重名\n");
            infos.append(e.getMessage());
        }

        return infos.toString();
    }

}