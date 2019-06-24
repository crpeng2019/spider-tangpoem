package com.seewo.spider.util;

import com.seewo.spider.common.Constant;
import com.seewo.spider.vo.SpiderConfig;
import us.codecraft.webmagic.Site;

/**
 * Site对象工厂
 * 单例模式、工厂模式
 *
 * @author qiuwuhui
 * @date 2019/6/21
 */
public class SiteFactory {

    private static volatile SiteFactory siteFactory;
    private static SpiderConfig spiderConfig;

    private SiteFactory() {
        spiderConfig = PropertiesUtils.propertiesToBean(Constant.SPIDER_CONFIG_PATH, SpiderConfig.class);
    }

    public static SiteFactory getInstance() {
        if (siteFactory == null) {
            synchronized (SiteFactory.class) {
                if (siteFactory == null) {
                    siteFactory = new SiteFactory();
                }
            }
        }
        return siteFactory;
    }

    public Site getSite() {
        return Site.me()
                .setSleepTime(spiderConfig.getSleepTime())
                .setTimeOut(spiderConfig.getSocketTimeOut())
                .setUserAgent(Constant.getAgent());
    }
}
