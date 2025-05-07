package com.yu.wrench.test;

import com.yu.wrench.ftp.domain.model.valobj.DownloadStatus;
import com.yu.wrench.ftp.domain.model.valobj.UploadStatus;
import com.yu.wrench.ftp.utils.FTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/***
 * @Author lzy
 * @Description
 **/
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiTest {

    @Resource
    private FTPUtils ftpUtils;

    @Value(value = "/file/upload")
    private String saveRemotePath;

    @Value(value = "/file/download")
    private String saveLocalPath;

    @Test
    public void uploadRemoteFile(String fileUrl) throws IOException{
        InputStream in = new URL(fileUrl).openStream();
//        InputStream in = Files.newInputStream(new File(fileURL).toPath());
        UploadStatus upload = ftpUtils.upload(saveRemotePath, "上传文件-文件名01", in);

        log.info(upload.getCode());
        if(upload.equals(UploadStatus.UploadNewFileFailed)) {
            throw new IOException("文件上传失败,IO异常");
        }
    }

    @Test
    public void downloadRemoteFile(String fileName) throws IOException {
        DownloadStatus downloadStatus = ftpUtils.downloadFile(saveRemotePath, fileName, saveLocalPath);
        log.info(downloadStatus.getCode());
    }

    @Test
    public void removeRemoteFile(String fileName) {
        boolean deleted = ftpUtils.deleteFile(saveRemotePath, fileName);
        log.info("文件删除{}", deleted ? "成功" : "失败");
    }
}
