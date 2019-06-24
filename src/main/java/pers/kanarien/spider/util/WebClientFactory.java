package com.seewo.spider.util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * 线程安全的WebClient工厂类
 * 单例模式和工厂模式
 *
 * @author qiuwuhui
 * @date 2019/6/6
 */
@SuppressWarnings("AlibabaThreadLocalShouldRemove")
public class WebClientFactory {

    private volatile static WebClientFactory webClientfactory;

    private WebClientFactory() {
    }

    public static WebClientFactory getInstance() {
        if (webClientfactory == null) {
            synchronized (WebClientFactory.class) {
                if (webClientfactory == null) {
                    webClientfactory = new WebClientFactory();
                }
            }
        }
        return webClientfactory;
    }

    public WebClient getWebClient() {
        WebClient webClient;
        webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setDoNotTrackEnabled(true);
        webClient.getOptions().setDownloadImages(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setTimeout(5000);
        // 设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(1000);
        return webClient;
    }

}
