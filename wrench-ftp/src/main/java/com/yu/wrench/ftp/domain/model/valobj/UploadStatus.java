package com.yu.wrench.ftp.domain.model.valobj;

/***
 * @Author lzy
 * @Description
 **/
public enum UploadStatus {

    UploadNewFileSuccess("S0001", "文件上传成功"),
    CreateDirectorySuccess("S0002", "创建目录成功"),
    UploadFromBreakSuccess("S0003", "断点续传继续成功"),

    UploadNewFileFailed("E0001", "文件上传失败"),
    FileExits("E0002", "远程文件已存在"),
    RemoteFileBiggerThanLocalFile("E0003", "远程文件大于本地文件"),
    UploadFromBreakFailed("E0004", "断点续传继续失败"),
    DeleteRemoteFaild("E0005", "上传失败后删除文件"),
    CreateDirectoryFail("E0006", "创建目录失败"),
    ;

    private String code;
    private String info;

    UploadStatus() {
    }

    UploadStatus(String code, String info) {
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
