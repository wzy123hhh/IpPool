package team.AI.IMG;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;


public class ImgChange {
    public void ImgChan() {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page = null;
        try {
            page = webClient.getPage("url");
            List<DomAttr> byXPath = page.getByXPath("//img/@src");
            for (DomAttr domAttr : byXPath) {
                String value = domAttr.getValue();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
