package com.seewo.spider.dao.impl;

import com.seewo.spider.util.MongoDBUtils;
import com.seewo.spider.dao.PoetDetailsDao;
import com.seewo.spider.entity.PoetDetails;

import java.util.List;

/**
 * @author qiuwuhui
 * @date 2019/6/5
 */
public class PoetDetailsDaoImpl implements PoetDetailsDao {
    /** 单例模式 */
    private volatile static PoetDetailsDao poetDetailsDao;
    private PoetDetailsDaoImpl() {}
    public static PoetDetailsDao getInstance() {
        if (poetDetailsDao == null) {
            synchronized (PoetDetailsDaoImpl.class) {
                if (poetDetailsDao == null) {
                    poetDetailsDao = new PoetDetailsDaoImpl();
                }
            }
        }
        return poetDetailsDao;
    }

    @Override
    public void save(PoetDetails poetDetails) {
        MongoDBUtils.getDatastore().save(poetDetails);
    }

    @Override
    public PoetDetails get(String id) {
        return MongoDBUtils.getDatastore().get(PoetDetails.class, id);
    }

    @Override
    public List<PoetDetails> list() {
        return MongoDBUtils.getDatastore().createQuery(PoetDetails.class).asList();
    }
}
