package com.seewo.spider.biz.processor;

import com.alibaba.fastjson.JSONObject;
import com.seewo.spider.common.Constant;
import com.seewo.spider.entity.PoemDetails;
import com.seewo.spider.util.JSEngineFactory;
import com.seewo.spider.util.SiteFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * 通过http://poem.studentsystem.org/shici?id={shiciId}
 * 爬取诗歌的详细信息，该类直接处理静态页面，使用Java的JS引擎执行JS代码
 *
 * @author qiuwuhui
 * @date 2019/6/14
 */
public class PoemDetailsStaticPageProcessor implements PageProcessor {

    private Site site = SiteFactory.getInstance().getSite();
    private JSEngineFactory jsEngineFactory = JSEngineFactory.getInstance();

    @Override
    public void process(Page page) {
        String encodedJsonStr = page.getHtml().xpath("//input[@id='vue-model']").css("input", "value").get();
        String decodedJsonStr = decode(encodedJsonStr);
        PoemDetails poemDetails = parseBean(decodedJsonStr);
        page.putField(Constant.POEM_DETAILS_KEY, poemDetails);
    }

    @Override
    public Site getSite() {
        return site;
    }

    private PoemDetails parseBean(String jsonStr) {
        JSONObject json = JSONObject.parseObject(jsonStr);
        String id = json.getString("id");
        String title = json.getString("biaoti");
        String author = json.getString("zuozhe");
        String content = json.getString("neirong");
        String annotations = json.getString("zhujie");
        String analysis = json.getString("shangxi");
        String translation = json.getString("yiwen");
        String style = json.getString("fengge");
        String volumeId = json.getString("juanid");
        String annals = json.getString("biannian");
        String theme = json.getString("ticai");
        String form = json.getString("tizhi");
        String function = json.getString("gongneng");
        String intonation = json.getString("yuyan");
        String emotion = json.getString("qinggan");
        String label = json.getString("biaoqian");
        String learningPhase = json.getString("jieduan");
        String bookName = json.getString("jiaocai");
        PoemDetails.InfoBox infoBox = new PoemDetails.InfoBox(style, bookName, volumeId,
                annals, theme, form, function, intonation, emotion, learningPhase, label);
        PoemDetails poemDetails = new PoemDetails(id, title, author, content, annotations, analysis, translation, infoBox);
        return poemDetails;
    }

    private String decode(String encodedJsonStr) {
        ScriptEngine scriptEngine = jsEngineFactory.getScriptEngine();
        Invocable invocable = (Invocable) scriptEngine;
        String result = null;
        try {
            result = (String) invocable.invokeFunction("resolveData", encodedJsonStr);
        } catch (ScriptException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args){
        Spider.create(new PoemDetailsStaticPageProcessor())
                .addUrl("http://poem.studentsystem.org/shici?id=d02b631a-b2fb-4393-88af-bd461f07ddfb")
                .run();
    }
}
