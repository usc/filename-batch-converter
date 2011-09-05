package org.usc.file.operater.utils;

public class StatisticsInfo {
    private int sumFileNum = 0;
    private int sumFolderNum = 0;
    private int convertFileNum = 0;
    private int convertFolderNum = 0;

    public StatisticsInfo() {
        super();
    }

    public StatisticsInfo(int sumFileNum, int sumFolderNum, int convertFileNum, int convertFolderNum) {
        this.sumFileNum = sumFileNum;
        this.sumFolderNum = sumFolderNum;
        this.convertFileNum = convertFileNum;
        this.convertFolderNum = convertFolderNum;
    }

    public int getSumFileNum() {
        return sumFileNum;
    }

    public void setSumFileNum(int sumFileNum) {
        this.sumFileNum = sumFileNum;
    }

    public int getSumFolderNum() {
        return sumFolderNum;
    }

    public void setSumFolderNum(int sumFolderNum) {
        this.sumFolderNum = sumFolderNum;
    }

    public int getConvertFileNum() {
        return convertFileNum;
    }

    public void setConvertFileNum(int convertFileNum) {
        this.convertFileNum = convertFileNum;
    }

    public int getConvertFolderNum() {
        return convertFolderNum;
    }

    public void setConvertFolderNum(int convertFolderNum) {
        this.convertFolderNum = convertFolderNum;
    }

    public void addSumFileNum() {
        this.sumFileNum++;
    }

    public void addSumFolderNum() {
        this.sumFolderNum++;
    }

    public void addConvertFileNum() {
        this.convertFileNum++;
    }

    public void addConvertFolderNum() {
        this.convertFolderNum++;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("\n转换文件夹").append(this.sumFolderNum)
                .append("个,文件").append(this.sumFileNum)
                .append("个\n成功转换文件夹").append(this.convertFolderNum)
                .append("个,文件").append(this.convertFileNum).append("个\n\n");

        return sb.toString();
    }

}
