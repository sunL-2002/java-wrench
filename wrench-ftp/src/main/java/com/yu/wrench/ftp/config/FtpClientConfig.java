package com.yu.wrench.ftp.config;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FtpClientConfigProperties.class)
public class FtpClientConfig implements PooledObjectFactory<FTPClient> {

    private final Logger log = LoggerFactory.getLogger(FtpClientConfig.class);

    FtpClientConfigProperties properties;

    /**
     * 创建连接到池中
     *
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(properties.getClientTimeout());
        ftpClient.connect(properties.getHost(), properties.getPort());
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftpClient.disconnect();
            return null;
        }
        boolean success;
        if (StringUtils.isBlank(properties.getUserName())) {
            success = ftpClient.login("anonymous", "anonymous");
        } else {
            success = ftpClient.login(properties.getUserName(), properties.getPassword());
        }
        if (!success) {
            return null;
        }
        ftpClient.setFileType(properties.getTransferFileType());
        ftpClient.setBufferSize(1024);
        ftpClient.setControlEncoding(properties.getEncoding());
        if (properties.isPassiveMode()) {
            ftpClient.enterLocalPassiveMode();
        }
        log.debug("创建ftp连接");
        return new DefaultPooledObject<>(ftpClient);
    }

    /**
     * 链接状态检查
     *
     * @param pool
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pool) {
        FTPClient ftpClient = pool.getObject();
        try {
            return ftpClient != null && ftpClient.sendNoOp();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 销毁连接，当连接池空闲数量达到上限时，调用此方法销毁连接
     *
     * @param pool
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pool) throws Exception {
        FTPClient ftpClient = pool.getObject();
        if (ftpClient != null) {
            try {
                ftpClient.disconnect();
                log.debug("销毁ftp连接");
            } catch (Exception e) {
                log.error("销毁ftpClient异常，error：", e.getMessage());
            }
        }
    }

    /**
     * 钝化连接，是连接变为可用状态
     *
     * @param p
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<FTPClient> p) throws Exception{
        FTPClient ftpClient = p.getObject();
        try {
            ftpClient.changeWorkingDirectory(properties.getWorkingDirectory());
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not disconnect from server.", e);
        }
    }

    /**
     * 初始化连接
     *
     * @param pool
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<FTPClient> pool) throws Exception {
        FTPClient ftpClient = pool.getObject();
        ftpClient.connect(properties.getHost(),properties.getPort());
        ftpClient.login(properties.getUserName(), properties.getPassword());
        ftpClient.setControlEncoding(properties.getEncoding());
        ftpClient.changeWorkingDirectory(properties.getWorkingDirectory());
        //设置上传文件类型为二进制，否则将无法打开文件
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    /**
     * 获取 FTP 连接配置
     * @return
     */
    public FtpClientConfigProperties getConfig(){
        return properties;
    }

}
