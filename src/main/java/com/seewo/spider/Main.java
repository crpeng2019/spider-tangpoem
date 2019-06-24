package com.seewo.spider;

import com.seewo.spider.biz.processor.PoemDetailsDynamicPageProcessor;
import com.seewo.spider.biz.processor.PoemDetailsStaticPageProcessor;
import com.seewo.spider.biz.processor.PoetDetailsPageProcessor;
import com.seewo.spider.biz.processor.PoetIdPageProcessor;
import com.seewo.spider.biz.pipeline.PoemDetailsPipeline;
import com.seewo.spider.biz.pipeline.PoetDetailsPipeline;
import com.seewo.spider.dao.PoemDetailsDao;
import com.seewo.spider.dao.PoetDetailsDao;
import com.seewo.spider.dao.impl.PoemDetailsDaoImpl;
import com.seewo.spider.dao.impl.PoetDetailsDaoImpl;
import com.seewo.spider.entity.PoemDetails;
import com.seewo.spider.entity.PoetDetails;
import com.seewo.spider.common.Constant;
import com.seewo.spider.util.HtmlUnitDownloader;
import com.seewo.spider.util.MainProxy;
import com.seewo.spider.util.PropertiesUtils;
import com.seewo.spider.vo.SpiderConfig;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 程序入口类
 *
 * @author qiuwuhui
 * @date 2019/6/5
 */
public class Main {

    private static PoetDetailsDao poetDetailsDao = PoetDetailsDaoImpl.getInstance();
    private static PoemDetailsDao poemDetailsDao = PoemDetailsDaoImpl.getInstance();
    private static MainProxy mainProxy = new MainProxy(new Main());

    public static void main(String[] args) {
        Main main = mainProxy.createProxy();
        // 0.读取爬虫配置
        SpiderConfig spiderConfig = main.getSpiderConfig();
        Integer threadNum = spiderConfig.getThreadNum();
        // 1.爬取所有的诗人id
        List<String> poetIdList = main.crawlPoetId();
        // 2.爬取所有诗人的详细信息
        main.crawlPoetDetails(poetIdList, threadNum);
        // 3.爬取所有诗歌的详细信息
        main.crawlPoemDetails(threadNum);
        // 4.处理JS动态加载不成功的诗歌信息
        main.fixPoemDetails(threadNum);
    }

    /**
     * 爬取所有唐代诗人的id
     *
     * @return
     */
    public List<String> crawlPoetId() {
        // 只需调用一次接口，单线程爬取即可
        PoetIdPageProcessor poetIdPageProcessor = new PoetIdPageProcessor();
        Spider.create(poetIdPageProcessor)
                .addUrl(Constant.POET_ID_API)
                .run();
        return poetIdPageProcessor.getTargetIdList();
    }

    /**
     * 根据id爬取诗人的详细信息，并保存到MongoDB
     * 其中包括诗人的诗歌id，为下一步服务
     *
     * @param poetIdList
     * @param threadNum
     */
    public void crawlPoetDetails(List<String> poetIdList, int threadNum) {
        // 1.拼接目的url
        List<String> sourceUrlList = poetIdList.stream()
                .map(poetId -> Constant.POET_DETAILS_API + poetId)
                .collect(Collectors.toList());
        // 2.使用线程池，多线程爬取
        String[] sourceUrls = new String[sourceUrlList.size()];
        Spider.create(new PoetDetailsPageProcessor())
                .addUrl(sourceUrlList.toArray(sourceUrls))
                .addPipeline(new PoetDetailsPipeline())
                .thread(threadNum)
                .run();
    }

    /**
     * 从数据库中获取诗歌id，爬取诗歌的详细信息
     *
     * @param threadNum
     */
    public void crawlPoemDetails(int threadNum) {
        // 1.获取所有的诗歌id，然后拼成url
        List<PoetDetails> list = poetDetailsDao.list();
        List<String> sourceUrlList = list.stream()
                .map(PoetDetails::getShici)
                .flatMap(List::stream)
                .map(PoetDetails.Shici::getId)
                .map(poemId -> Constant.POEM_DETAILS_API + poemId)
                .collect(Collectors.toList());
        // 2.使用线程池，多线程爬取 静态 页面
        // 静态页面爬取使用框架默认的HttpClient，调用一次接口耗时 5-6s
        String[] sourceUrls = new String[sourceUrlList.size()];
        // Spider.create(new PoemDetailsStaticPageProcessor())
        //         .addUrl(sourceUrlList.toArray(sourceUrls))
        //         .addPipeline(new PoemDetailsPipeline())
        //         .thread(threadNum)
        //         .run();

        // // 另一种方式，多线程爬取 动态 页面
        Spider.create(new PoemDetailsDynamicPageProcessor())
                .addUrl(sourceUrlList.toArray(sourceUrls))
                // 使用HtmlUnit爬取，调用一次接口耗时 10-11s
                // .setDownloader(new HtmlUnitDownloader())
                // 使用PhantomJS爬取，调用一次接口耗时 13-14s
                .setDownloader(new PhantomJSDownloader("e:/phantomjs.exe", "e:/phantomjs/crawl.js"))
                .addPipeline(new PoemDetailsPipeline())
                .thread(threadNum)
                .run();
    }

    /**
     * 由于动态执行JS可会能超时，
     * 因此最后要处理未成功加载信息的诗歌数据，
     * 从数据库中读取这类数据，再次构成url爬取，直到所有不完整数据都被处理
     *
     * @param threadNum
     */
    public void fixPoemDetails(int threadNum) {
        List<PoemDetails> unfixedList = poemDetailsDao.listByTitle(Constant.POEM_DETAILS_UNFIXED_TITLE_VALUE);
        while (!unfixedList.isEmpty()) {
            List<String> sourceUrlList = unfixedList.stream()
                    .map(poemDetails -> Constant.POEM_DETAILS_API + poemDetails.getId())
                    .collect(Collectors.toList());
            String[] sourceUrls = new String[sourceUrlList.size()];
            Spider.create(new PoemDetailsDynamicPageProcessor())
                    .addUrl(sourceUrlList.toArray(sourceUrls))
                    .setDownloader(new HtmlUnitDownloader())
                    .addPipeline(new PoemDetailsPipeline())
                    .thread(threadNum)
                    .run();
            unfixedList = poemDetailsDao.listByTitle(Constant.POEM_DETAILS_UNFIXED_TITLE_VALUE);
        }
    }

    /**
     * 初始化爬虫配置
     *
     * @return
     */
    public SpiderConfig getSpiderConfig() {
        return PropertiesUtils.propertiesToBean(Constant.SPIDER_CONFIG_PATH, SpiderConfig.class);
    }


}
