package com.yu.wrench.ftp.config;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ftp", ignoreInvalidFields = true)
public class FtpClientConfigProperties extends GenericObjectPoolConfig<FTPClient> {

    /**
     * FTP服务器地址
     */
    private String host;

    /**
     * FTP服务器端口
     */
    private Integer port;

    /**
     * FTP用户名
     */
    private String userName;

    /**
     * FTP密码
     */
    private String password;

    /**
     * FTP服务器根目录
     */
    private String workingDirectory;

    /**
     * 传输编码
     */
    private String encoding;

    /**
     * 被动模式：在这种模式下，数据连接是由客户程序发起的
     */
    private boolean passiveMode;

    /**
     * 连接超时时间
     */
    private int clientTimeout;

    /**
     * 线程数
     */
    private int threaNum;
    /**
     * 0=ASCII_FILE_TYPE(ASCII格式)，1=EBCDIC_FILE_TYPE，2=LOCAL_FILE_TYPE(二进制文件)
     */
    private int transferFileType;

    /**
     * 是否重命名
     */
    private boolean renameUploaded;

    /**
     * 重新连接时间
     */
    private int retryTimes;

    /**
     * 缓存大小
     */
    private int bufferSize;

    /**
     * 最大数
     */
    private int maxTotal;

    /**
     * 最小空闲
     */
    private int minldle;

    /**
     * 最大空闲
     */
    private int maxldle;

    /**
     * 最大等待时间
     */
    private int maxWait;
    /**
     *  池对象耗尽之后是否阻塞，maxWait < 0 时一直等待
     */
    private boolean blockWhenExhausted;
    /**
     * 取对象时验证
     */
    private boolean testOnBorrow;
    /**
     * 回收验证
     */
    private boolean testOnReturn;
    /**
     * 创建时验证
     */
    private boolean testOnCreate;
    /**
     * 空闲验证
     */
    private boolean testWhileldle;
    /**
     * 后进先出
     */
    private boolean lifo;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isPassiveMode() {
        return passiveMode;
    }

    public void setPassiveMode(boolean passiveMode) {
        this.passiveMode = passiveMode;
    }

    public int getClientTimeout() {
        return clientTimeout;
    }

    public void setClientTimeout(int clientTimeout) {
        this.clientTimeout = clientTimeout;
    }

    public int getThreaNum() {
        return threaNum;
    }

    public void setThreaNum(int threaNum) {
        this.threaNum = threaNum;
    }

    public int getTransferFileType() {
        return transferFileType;
    }

    public void setTransferFileType(int transferFileType) {
        this.transferFileType = transferFileType;
    }

    public boolean isRenameUploaded() {
        return renameUploaded;
    }

    public void setRenameUploaded(boolean renameUploaded) {
        this.renameUploaded = renameUploaded;
    }

    public int getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(int retryTimes) {
        this.retryTimes = retryTimes;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public int getMaxTotal() {
        return maxTotal;
    }

    @Override
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMinldle() {
        return minldle;
    }

    public void setMinldle(int minldle) {
        this.minldle = minldle;
    }

    public int getMaxldle() {
        return maxldle;
    }

    public void setMaxldle(int maxldle) {
        this.maxldle = maxldle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public boolean isBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    @Override
    public void setBlockWhenExhausted(boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    @Override
    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    @Override
    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestOnCreate() {
        return testOnCreate;
    }

    @Override
    public void setTestOnCreate(boolean testOnCreate) {
        this.testOnCreate = testOnCreate;
    }

    public boolean isTestWhileldle() {
        return testWhileldle;
    }

    public void setTestWhileldle(boolean testWhileldle) {
        this.testWhileldle = testWhileldle;
    }

    public boolean isLifo() {
        return lifo;
    }

    @Override
    public void setLifo(boolean lifo) {
        this.lifo = lifo;
    }
}
