package com.seewo.spider.biz.processor;

import com.seewo.spider.entity.PoemDetails;
import com.seewo.spider.common.Constant;
import com.seewo.spider.util.HtmlUnitDownloader;
import com.seewo.spider.util.SiteFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * 通过http://poem.studentsystem.org/shici?id={shiciId}
 * 爬取诗歌的详细信息，由于页面是JS动态加载的，所以Downloader预先使用HtmlUnit或PhantomJS处理
 *
 * @author qiuwuhui
 * @date 2019/6/4
 */
public class PoemDetailsDynamicPageProcessor implements PageProcessor {

    private Site site = SiteFactory.getInstance().getSite();

    @Override
    public void process(Page page) {
        String sourceUrl = page.getUrl().get();
        String id = sourceUrl.substring(sourceUrl.indexOf("id=") + 3);
        String title = page.getHtml().xpath("//div[@id='vue-app']/h1/text()").get().trim();
        String author = page.getHtml().xpath("//div[@class='sc-zz']/text()").get().trim();
        String content = page.getHtml().xpath("//div[@class='sc-nr']/text()").get().trim();
        String annotations = page.getHtml().xpath("//div[@class='sc-zhujie']/div/text()").get().trim();
        String analysis = page.getHtml().xpath("//div[@class='sc-shangxi']/div/text()").get().trim();
        String translation = page.getHtml().xpath("//div[@class='sc-yiwen']/div/text()").get().trim();
        List<Selectable> infoBoxNodes = page.getHtml().xpath("//div[@class='infobox']/div/table/tbody/tr").nodes();
        PoemDetails.InfoBox infoBox = getInfoBox(infoBoxNodes);
        PoemDetails poemDetails = new PoemDetails(id, title, author, content, annotations, analysis, translation, infoBox);
        page.putField(Constant.POEM_DETAILS_KEY, poemDetails);
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 根据Html节点解析InfoBox对象
     *
     * @param infoBoxNodes
     * @return
     */
    private PoemDetails.InfoBox getInfoBox(List<Selectable> infoBoxNodes) {
        String style = null;
        String volumeId = null;
        String bookName = null;
        String annals = null;
        String theme = null;
        String form = null;
        String function = null;
        String intonation = null;
        String emotion = null;
        String learningPhase = null;
        String label = null;
        for (Selectable item : infoBoxNodes) {
            String key = item.xpath("/tr/td[1]/text()").get().trim();
            String value = item.xpath("/tr/td[2]/text()").get().trim();
            switch (key) {
                case "风格":
                    style = value;
                    break;
                case "教材":
                    bookName = value;
                    break;
                case "卷ID":
                    volumeId = value;
                    break;
                case "编年":
                    annals = value;
                    break;
                case "题材":
                    theme = value;
                    break;
                case "体制":
                    form = value;
                    break;
                case "功能":
                    function = value;
                    break;
                case "语言":
                    intonation = value;
                    break;
                case "情感":
                    emotion = value;
                    break;
                case "学习阶段":
                    learningPhase = value;
                    break;
                case "标签":
                    label = value;
                    break;
                default:
            }
        }
        return new PoemDetails.InfoBox(style, bookName, volumeId, annals, theme, form,
                function, intonation, emotion, learningPhase, label);
    }

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        Spider.create(new PoemDetailsDynamicPageProcessor())
//                .setDownloader(new PhantomJSDownloader("e:/phantomjs.exe", "e:/phantomjs/crawl.js"))
                .setDownloader(new HtmlUnitDownloader())
                .addUrl("http://poem.studentsystem.org/shici?id=31d97644-0826-46fc-9261-3799e61d451f")
                .run();
        System.out.println("cost: " + (System.currentTimeMillis() - begin) + "ms");
    }
}
