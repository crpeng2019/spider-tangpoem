package com.seewo.spider.dao;

import com.seewo.spider.entity.PoetDetails;

import java.util.List;

/**
 * PoetDetails数据获取对象
 *
 * @author qiuwuhui
 * @date 2019/6/5
 */
public interface PoetDetailsDao {
    /**
     * 保存实体
     * @param poetDetails
     */
    void save(PoetDetails poetDetails);

    /**
     * 根据id获取实体
     * @param id
     * @return
     */
    PoetDetails get(String id);

    /**
     * 列出所有实体
     * @return
     */
    List<PoetDetails> list();
}
