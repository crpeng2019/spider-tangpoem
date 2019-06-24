package com.seewo.spider.vo;

import com.seewo.spider.vo.common.PropertiesPair;

/**
 * MongoDB配置类
 * 映射mongodb.properties
 *
 * @author qiuwuhui
 * @date 2019/6/11
 */
public class MongoDBConfig {

    @PropertiesPair(key = "mongodb.host")
    private String host;
    @PropertiesPair(key = "mongodb.port")
    private Integer port;
    @PropertiesPair(key = "mongodb.dbName")
    private String dbName;
    @PropertiesPair(key = "mongodb.authDb")
    private String authDb;
    @PropertiesPair(key = "mongodb.username")
    private String username;
    @PropertiesPair(key = "mongodb.password")
    private String password;
    @PropertiesPair(key = "mongodb.maxWaitTime")
    private Integer maxWaitTime;
    @PropertiesPair(key = "mongodb.connectTimeout")
    private Integer connectTimeout;
    @PropertiesPair(key = "mongodb.connectionsPerHost")
    private Integer connectionsPerHost;
    @PropertiesPair(key = "mongodb.socketTimeout")
    private Integer socketTimeout;
    @PropertiesPair(key = "mongodb.threadsAllowedToBlockForConnectionMultiplier")
    private Integer threadsAllowedToBlockForConnectionMultiplier;

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

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getAuthDb() {
        return authDb;
    }

    public void setAuthDb(String authDb) {
        this.authDb = authDb;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(Integer maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(Integer connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(Integer threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MongoDBConfig{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", dbName='").append(dbName).append('\'');
        sb.append(", authDb='").append(authDb).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", maxWaitTime=").append(maxWaitTime);
        sb.append(", connectTimeout=").append(connectTimeout);
        sb.append(", connectionsPerHost=").append(connectionsPerHost);
        sb.append(", socketTimeout=").append(socketTimeout);
        sb.append(", threadsAllowedToBlockForConnectionMultiplier=").append(threadsAllowedToBlockForConnectionMultiplier);
        sb.append('}');
        return sb.toString();
    }
}
