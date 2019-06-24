package com.seewo.spider.dao;

import com.seewo.spider.entity.PoemDetails;

import java.util.List;

/**
 * PoemDetails数据获取对象
 *
 * @author qiuwuhui
 * @date 2019/6/5
 */
public interface PoemDetailsDao {
    /**
     * 保存实体
     * @param poemDetails
     */
    void save(PoemDetails poemDetails);

    /**
     * 根据主键获取实体
     * @param id
     * @return
     */
    PoemDetails get(String id);

    /**
     * 列出所有实体
     * @return
     */
    List<PoemDetails> list();

    /**
     * 根据属性title的值获取实体
     * @param title
     * @return
     */
    List<PoemDetails> listByTitle(String title);
}
