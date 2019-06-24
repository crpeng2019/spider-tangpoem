import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author qiuwuhui
 * @date 2019/6/1
 */
public class HtmlUnitTest {

    public void testHtmlUnit() throws IOException{
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(5000);
        HtmlPage rootPage = webClient.getPage("http://poem.studentsystem.org/index?s=%E6%9D%8E%E7%99%BD%E7%9A%84%E4%BD%9C%E5%93%81");
        // 设置一个运行JavaScript的时间
        webClient.waitForBackgroundJavaScript(500);
        String html = rootPage.asXml();
        Document document = Jsoup.parse(html);
        System.out.println(document.toString());
    }

    public static void main(String[] args) throws IOException{
        new HtmlUnitTest().testHtmlUnit();
    }
}
