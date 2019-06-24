package com.seewo.spider.util;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.AbstractDownloader;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.WMCollections;

/**
 * 使用HtmlUnit动态加载页面，运行JavaScript
 *
 * @author qiuwuhui
 * @date 2019/6/4
 */
public class HtmlUnitDownloader extends AbstractDownloader implements Downloader {

    private static WebClientFactory webClientFactory = WebClientFactory.getInstance();
    private static Logger logger = LoggerFactory.getLogger(HtmlUnitDownloader.class);

    @Override
    public Page download(Request request, Task task) {
        // 设置接收的响应码
        Site site = null;
        if (task != null) {
            site = task.getSite();
        }
        Set<Integer> acceptStatCode;
        if (site != null) {
            acceptStatCode = site.getAcceptStatCode();
        } else {
            acceptStatCode = WMCollections.newHashSet(200);
        }

        int statusCode;
        int failure = 0;
        Document doc;
        WebClient webClient = null;
        // 通过url获取页面，成功则返回，失败则重试3次
        while (true) {
            try {
                webClient = webClientFactory.getWebClient();
                HtmlPage rootPage = webClient.getPage(request.getUrl());
                doc = Jsoup.parse(rootPage.asXml());
                statusCode = rootPage.getWebResponse().getStatusCode();
                if (statusAccept(acceptStatCode, statusCode)) {
                    logger.info("downloading page success {}", request.getUrl());
                    Page page = new Page();
                    page.setRawText(doc.html());
                    page.setUrl(new PlainText(request.getUrl()));
                    page.setRequest(request);
                    page.setStatusCode(statusCode);
                    onSuccess(request);
                    return page;
                } else {
                    logger.warn("get page {} error, status code {} ", request.getUrl(), statusCode);
                    return null;
                }
            } catch (Exception e) {
                onError(request);
                failure++;
                if (failure == 3) {
                    logger.warn("get page {} error, 3 times, exception {} ", request.getUrl(), e.getMessage());
                    return null;
                }
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } catch (InterruptedException e1) {
                }
                logger.warn("download page {} error", request.getUrl(), e);
                continue;
            } finally {
                if (webClient != null) {
                    webClient.getCurrentWindow().getJobManager().removeAllJobs();
                    webClient.close();
                }
            }
        }
    }

    protected boolean statusAccept(Set<Integer> acceptStatCode, int statusCode) {
        return acceptStatCode.contains(statusCode);
    }

    @Override
    public void setThread(int arg0) {
        // TODO Auto-generated method stub

    }
}