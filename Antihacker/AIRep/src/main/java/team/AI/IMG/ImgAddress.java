package team.AI.IMG;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImgAddress {
    ArrayList arrayList=new ArrayList();
    public ArrayList imgAddress(String url) {
        //实例化客户端
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        HtmlPage page=null;
        try {
            //获取HTML页面
            page = webClient.getPage(url);
            List<DomAttr> byXPath = page.getByXPath("//img/@src");
            for (DomAttr domAttr : byXPath) {
                arrayList.add(domAttr.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            webClient.getCurrentWindow().getJobManager().removeAllJobs();
            System.gc();
            webClient.close();
        }
        return arrayList;
    }

    public static void main(String[] args) {
        ImgAddress imgAddress=new ImgAddress();
        ArrayList arrayList = imgAddress.imgAddress("http://www.chzu.edu.cn");
        Iterator iterator = arrayList.iterator();
        while(iterator.hasNext()){
            System.out.println((String)iterator.next());
        }
        System.out.println(arrayList.size());
    }
}
