package com.seewo.spider.biz.pipeline;

import com.seewo.spider.common.Constant;
import com.seewo.spider.dao.PoetDetailsDao;
import com.seewo.spider.dao.impl.PoetDetailsDaoImpl;
import com.seewo.spider.entity.PoetDetails;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author qiuwuhui
 * @date 2019/6/10
 */
public class PoetDetailsPipeline implements Pipeline {

    private PoetDetailsDao poetDetailsDao = PoetDetailsDaoImpl.getInstance();
    private Logger logger = Logger.getLogger(PoetDetailsPipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        PoetDetails poetDetails = resultItems.get(Constant.POET_DETAILS_KEY);
        poetDetailsDao.save(poetDetails);
        logger.info("数据保存成功：id = " + poetDetails.getId() + ", name = " + poetDetails.getXingming());
    }
}
