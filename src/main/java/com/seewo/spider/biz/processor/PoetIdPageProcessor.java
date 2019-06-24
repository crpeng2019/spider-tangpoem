package com.seewo.spider.biz.processor;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seewo.spider.common.Constant;
import com.seewo.spider.util.SiteFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过http://poem.studentsystem.org/web/sc/zuozhe/all?xm={poetName}
 * 爬取诗人姓名对应的id
 *
 * @author qiuwuhui
 * @date 2019/6/3
 */
public class PoetIdPageProcessor implements PageProcessor {

    private Site site = SiteFactory.getInstance().getSite();
    private List<String> targetIdList = new ArrayList<>();

    @Override
    public void process(Page page) {
        JSONArray jsonArray = JSONArray.parseArray(page.getJson().get());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String id = jsonObject.getString("id");
            targetIdList.add(id);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public List getTargetIdList() {
        return targetIdList;
    }

    public static void main(String[] args){
        Spider.create(new PoetIdPageProcessor())
                .addUrl("http://poem.studentsystem.org/web/sc/zuozhe/all?xm=李白")
                .run();
    }
}
