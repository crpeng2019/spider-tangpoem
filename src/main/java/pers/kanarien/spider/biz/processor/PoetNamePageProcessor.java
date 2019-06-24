package com.seewo.spider.biz.processor;

import com.seewo.spider.common.Constant;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通过http://www.shicimingju.com/category/tangdaishiren
 * 爬取所有唐代诗人的姓名
 *
 * 后来发现另一个可以直接获得诗人id的接口，该类多余，但代码保留
 *
 * @author qiuwuhui
 * @date 2019/6/3
 */
@Deprecated
public class PoetNamePageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setRetryTimes(3)
            .setTimeOut(60000)
            .setUserAgent(Constant.getAgent());
    private List<String> targetNameList = new ArrayList<>();

    @Override
    public void process(Page page) {
        List<Selectable> nameNodes = page.getHtml().xpath("//div[@class='www-main-container']/h3/a/text()").nodes();
        List<String> nameList = nameNodes.stream()
                .map(Selectable::get)
                .collect(Collectors.toList());
        targetNameList.addAll(nameList);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public List getTargetNameList() {
        return this.targetNameList;
    }

    public static void main(String[] args){
        String[] urls = {"http://www.shicimingju.com/category/tangdaishiren", "http://www.shicimingju.com/category/tangdaishiren__2",
        "http://www.shicimingju.com/category/tangdaishiren__3", "http://www.shicimingju.com/category/tangdaishiren__4",
        "http://www.shicimingju.com/category/tangdaishiren__5"};
        Spider.create(new PoetNamePageProcessor())
                .addUrl(urls)
                .thread(20)
                .run();
        System.out.println("complete!...");
    }
}
