package com.seewo.spider.biz.pipeline;

import com.seewo.spider.common.Constant;
import com.seewo.spider.dao.PoemDetailsDao;
import com.seewo.spider.dao.impl.PoemDetailsDaoImpl;
import com.seewo.spider.entity.PoemDetails;
import org.apache.log4j.Logger;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


/**
 * @author qiuwuhui
 * @date 2019/6/10
 */
public class PoemDetailsPipeline implements Pipeline {

    private PoemDetailsDao poemDetailsDao = PoemDetailsDaoImpl.getInstance();
    private static Logger logger = Logger.getLogger(PoemDetailsPipeline.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        PoemDetails poemDetails = resultItems.get(Constant.POEM_DETAILS_KEY);
        poemDetailsDao.save(poemDetails);
        logger.info(String.format("数据保存成功：id = %s, title = %s", poemDetails.getId(), poemDetails.getTitle()));
    }
}
