package com.seewo.spider.util;

import com.mongodb.*;
import com.mongodb.MongoClientOptions.Builder;
import com.seewo.spider.common.Constant;
import com.seewo.spider.vo.MongoDBConfig;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.*;

/**
 * MongoDB连接获取工具类
 *
 * @author qiuwuhui
 * @date 2019/6/4
 */
public class MongoDBUtils {

    private static Datastore datastore;

    static {
        MongoDBConfig mongoDBConfig = PropertiesUtils.propertiesToBean(Constant.MONGODB_CONFIG_PATH, MongoDBConfig.class);
        ServerAddress sa = new ServerAddress(mongoDBConfig.getHost(), mongoDBConfig.getPort());
        List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
        mongoCredentialList.add(MongoCredential.createScramSha1Credential(
                mongoDBConfig.getUsername(), mongoDBConfig.getAuthDb(), mongoDBConfig.getPassword().toCharArray()));
        Builder options = new MongoClientOptions.Builder();
        options.connectionsPerHost(mongoDBConfig.getConnectionsPerHost())
                .connectTimeout(mongoDBConfig.getConnectTimeout())
                .maxWaitTime(mongoDBConfig.getMaxWaitTime())
                .socketTimeout(mongoDBConfig.getSocketTimeout())
                .threadsAllowedToBlockForConnectionMultiplier(mongoDBConfig.getThreadsAllowedToBlockForConnectionMultiplier());
        MongoClient mongoClient = new MongoClient(sa, mongoCredentialList, options.build());
        Morphia morphia = new Morphia();
        datastore = morphia.createDatastore(mongoClient, mongoDBConfig.getDbName());
    }

    public static Datastore getDatastore() {
        return datastore;
    }

}
