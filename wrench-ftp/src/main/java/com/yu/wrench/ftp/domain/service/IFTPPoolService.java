package com.yu.wrench.ftp.domain.service;

import com.yu.wrench.ftp.config.FtpClientConfigProperties;
import org.apache.commons.net.ftp.FTPClient;

public interface IFTPPoolService {

    /**
     * 获取ftpClient
     * @return
     */
    FTPClient borrowObject();

    /**
     * 归还ftpClient
     * @param ftpClient
     * @return
     */
    void returnObject(FTPClient ftpClient);

    /**
     * 获取 ftp 配置信息
     * @return
     */
    FtpClientConfigProperties getFtpPoolConfig();
}
