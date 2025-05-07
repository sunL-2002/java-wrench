package com.yu.wrench.ftp.domain.service;

import com.yu.wrench.ftp.config.FtpClientConfig;
import com.yu.wrench.ftp.config.FtpClientConfigProperties;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class FTPPoolService implements IFTPPoolService {

    private GenericObjectPool<FTPClient> pool;

    private final Logger log = LoggerFactory.getLogger(FTPPoolService.class);
    /**
     * ftp 客户端配置文件
     */
    private final FtpClientConfigProperties config;

    /**
     * ftp 客户端工厂
     */
    private final FtpClientConfig factory;

    public FTPPoolService(FtpClientConfigProperties config, FtpClientConfig factory) {
        this.config = config;
        this.factory = factory;
    }

    /**
     * 初始化pool
     */
    @PostConstruct
    private void initPool() {
        this.pool = new GenericObjectPool<FTPClient>(this.factory, this.config);
    }

    /**
     * 获取ftpClient
     */
    @Override
    public FTPClient borrowObject() {
        if (this.pool != null) {
            try {
                FTPClient client = this.pool.borrowObject();
                client.setControlEncoding("UTF-8");
                client.enterLocalPassiveMode(); // 启用被动模式
                return client;
            } catch (Exception e) {
                log.error("获取 FTPClient 失败 ", e);
            }
        }
        return null;
    }

    /**
     * 归还 ftpClient
     */
    @Override
    public void returnObject(FTPClient ftpClient) {
        if (this.pool != null && ftpClient != null) {
            this.pool.returnObject(ftpClient);
        }
    }

    @Override
    public FtpClientConfigProperties getFtpPoolConfig() {
        return config;
    }
}
