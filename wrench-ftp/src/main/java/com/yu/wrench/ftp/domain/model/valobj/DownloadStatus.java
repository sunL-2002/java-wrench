package com.yu.wrench.ftp.domain.model.valobj;

/***
 * @Author lzy
 * @Description
 **/
public enum DownloadStatus {
    DownloadFromBreakSuccess("S0001", "下载远程文件成功"),
    DownloadNewSuccess("S0002", "下载文件成功"),

    RemoteFileNotExist("E0001", "远程文件不存在"),
    LocalFileBiggerThanRemoteFile("E0002", "本地文件大于远程文件，下载中止"),
    DownloadFromBreakFailed("E0003", "下载远程文件失败"),
    DownloadNewFailed("E0004", "下载远程文件失败"),
    ;

    private String code;
    private String info;

    DownloadStatus() {
    }

    DownloadStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
