package com.seewo.spider.dao.impl;

import com.seewo.spider.util.MongoDBUtils;
import com.seewo.spider.entity.PoemDetails;
import com.seewo.spider.dao.PoemDetailsDao;

import java.util.List;

/**
 * @author qiuwuhui
 * @date 2019/6/5
 */
public class PoemDetailsDaoImpl implements PoemDetailsDao {
    /** 单例模式 */
    private volatile static PoemDetailsDao poemDetailsDao;
    private PoemDetailsDaoImpl() {}
    public static PoemDetailsDao getInstance() {
        if (poemDetailsDao == null) {
            synchronized (PoemDetailsDaoImpl.class) {
                if (poemDetailsDao == null) {
                    poemDetailsDao = new PoemDetailsDaoImpl();
                }
            }
        }
        return poemDetailsDao;
    }

    @Override
    public void save(PoemDetails poemDetails) {
        MongoDBUtils.getDatastore().save(poemDetails);
    }

    @Override
    public PoemDetails get(String id) {
        return MongoDBUtils.getDatastore().get(PoemDetails.class, id);
    }

    @Override
    public List<PoemDetails> list() {
        return MongoDBUtils.getDatastore().createQuery(PoemDetails.class).asList();
    }

    @Override
    public List<PoemDetails> listByTitle(String title) {
        return MongoDBUtils.getDatastore().createQuery(PoemDetails.class).filter("title = ", title).asList();
    }
    
}
