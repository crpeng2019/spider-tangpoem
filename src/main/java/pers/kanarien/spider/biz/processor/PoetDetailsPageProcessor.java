package com.seewo.spider.biz.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seewo.spider.entity.PoetDetails;
import com.seewo.spider.common.Constant;
import com.seewo.spider.util.SiteFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过http://poem.studentsystem.org/web/sc/zuozhe/get?id={id}
 * 爬取诗人详细信息
 *
 * @author qiuwuhui
 * @date 2019/6/3
 */
public class PoetDetailsPageProcessor implements PageProcessor {

    private Site site = SiteFactory.getInstance().getSite();

    @Override
    public void process(Page page) {
        Json jsonResult = page.getJson();
        PoetDetails poetDetails = getPoetDetails(jsonResult);
        poetDetails.setShici(getShiciList(jsonResult));
        poetDetails.setFasong(getFasongLIst(jsonResult));
        poetDetails.setJieshou(getJieshoutList(jsonResult));
        poetDetails.setDidian(getDidianList(jsonResult));
        page.putField(Constant.POET_DETAILS_KEY, poetDetails);
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 解析Json获取诗人详细信息
     * @param jsonResult
     * @return
     */
    private PoetDetails getPoetDetails(Json jsonResult) {
        String id = jsonResult.jsonPath("$.id").get();
        String aid = jsonResult.jsonPath("$.aid").get();
        String xingming = jsonResult.jsonPath("$.xingming").get();
        String xingmingpy = jsonResult.jsonPath("$.xingmingpy").get();
        String touxiang = jsonResult.jsonPath("$.touxiang").get();
        String chusheng = jsonResult.jsonPath("$.chusheng").get();
        String shishi = jsonResult.jsonPath("$.shishi").get();
        String zihaobieming = jsonResult.jsonPath("$.zihaobieming").get();
        String zi = jsonResult.jsonPath("$.zi").get();
        String hao = jsonResult.jsonPath("$.hao").get();
        String chaodai = jsonResult.jsonPath("$.chaodai").get();
        String zichaodai = jsonResult.jsonPath("$.zichaodai").get();
        String waihao = jsonResult.jsonPath("$.waihao").get();
        String jianjie = jsonResult.jsonPath("$.jianjie").get();
        String bz = jsonResult.jsonPath("$.bz").get();
        String lianjie = jsonResult.jsonPath("$.lianjie").get();
        Double paixu = Double.parseDouble(jsonResult.jsonPath("$.paixu").get());
        String insertedtime = jsonResult.jsonPath("$.insertedtime").get();
        String updatedtime = jsonResult.jsonPath("$.updatedtime").get();
        String zhbm = jsonResult.jsonPath("$.zhbm").get();
        PoetDetails poetDetails = new PoetDetails(id, aid, xingming, xingmingpy, touxiang, chusheng, shishi, zihaobieming,
                zi, hao, chaodai, zichaodai, waihao, jianjie, bz, lianjie, paixu, insertedtime, updatedtime, zhbm);
        return poetDetails;
    }

    /**
     * 解析Json获得诗词数据
     * @param jsonResult
     * @return
     */
    private List<PoetDetails.Shici> getShiciList(Json jsonResult) {
        List<Selectable> shiciNodes = jsonResult.jsonPath("$.shici").nodes();
        List<PoetDetails.Shici> shiciList = new ArrayList<>();
        for (Selectable shiciNode : shiciNodes) {
            JSONObject shiciJson = JSONObject.parseObject(shiciNode.get());
            String sid = shiciJson.getString("id");
            String biaoti = shiciJson.getString("biaoti");
            String neirong = shiciJson.getString("neirong");
            PoetDetails.Shici shici = new PoetDetails.Shici(sid, biaoti, neirong);
            shiciList.add(shici);
        }
        return shiciList;
    }

    /**
     * 解析Json获得好友(fasong)数据
     * @param jsonResult
     * @return
     */
    private List<PoetDetails.Haoyou> getFasongLIst(Json jsonResult) {
        List<Selectable> fasongNodes = jsonResult.jsonPath("$.fasong").nodes();
        List<PoetDetails.Haoyou> fasongList = new ArrayList<>();
        for (Selectable fasongNode : fasongNodes) {
            JSONObject fasongJson = JSONObject.parseObject(fasongNode.get());
            String fid = fasongJson.getString("id");
            String xm = fasongJson.getString("xm");
            Double pc = fasongJson.getDouble("pc");
            PoetDetails.Haoyou haoyou =  new PoetDetails.Haoyou(fid, xm, pc);
            fasongList.add(haoyou);
        }
        return fasongList;
    }

    /**
     * 解析Json获得好友(jieshou)数据
     * @param jsonResult
     * @return
     */
    private List<PoetDetails.Haoyou> getJieshoutList(Json jsonResult) {
        List<Selectable> jieshouNodes = jsonResult.jsonPath("$.jieshou").nodes();
        List<PoetDetails.Haoyou> jieshouList = new ArrayList<>();
        for (Selectable jieshouNode : jieshouNodes) {
            JSONObject jieshouJson = JSON.parseObject(jieshouNode.get());
            String jid = jieshouJson.getString("id");
            String xm = jieshouJson.getString("xm");
            Double pc = jieshouJson.getDouble("pc");
            PoetDetails.Haoyou haoyou = new PoetDetails.Haoyou(jid, xm, pc);
            jieshouList.add(haoyou);
        }
        return jieshouList;
    }

    /**
     * 解析Json获得地点数据
     * @param jsonResult
     * @return
     */
    private List<PoetDetails.Didian> getDidianList(Json jsonResult) {
        List<Selectable> didianNodes = jsonResult.jsonPath("$.didian").nodes();
        List<PoetDetails.Didian> didianList = new ArrayList<>();
        for (Selectable didianNode : didianNodes) {
            JSONObject didianJson = JSON.parseObject(didianNode.get());
            String di = didianJson.getString("didian");
            Integer scs = didianJson.getInteger("scs");
            PoetDetails.Didian didian = new PoetDetails.Didian(di, scs);
        }
        return didianList;
    }

    public static void main(String[] args){
        Spider.create(new PoetDetailsPageProcessor())
                .addUrl("http://poem.studentsystem.org/web/sc/zuozhe/get?id=8d5e30cf-c72c-4ad5-a265-bceb1c092b85")
                .run();
    }
}
