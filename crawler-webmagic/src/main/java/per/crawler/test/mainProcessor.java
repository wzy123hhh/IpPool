package per.crawler.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class mainProcessor implements PageProcessor {

    private static Spider spider = null;
    private Site site = Site
            .me()
            .setTimeOut(10000)
            .setSleepTime(33)
            .setRetrySleepTime(3000);

    public void process(Page page) {
        if(!page.getUrl().regex("http://www.fangongheike.com/search\\??").match()){
            getHeadPage(page);
        }else{
            getOlderPage(page);
        }

    }

    public void getOlderPage(Page page){


        page.putField("title",page.getHtml().xpath("//div[@class='blog-posts hfeed']/div[@class=date-outer]//h3/a/text()").all());
        page.putField("url",page.getHtml().regex("</a>(https?://[0-9a-z-]*\\.?[0-9a-z-]*\\.?[0-9a-z-]*\\.?[0-9a-z-]*:?[a-z0-9-]*[/\\.a-z0-9-]*).&?",1).all());
        page.putField("context",page.getHtml().xpath("//div[@class='post-body entry-content']/text()").all());
        page.putField("time",page.getHtml().xpath("//abbr[@class=published]/text()").all());

        //获取较旧的博客地址
        List<String> olds = page.getHtml().xpath("//span[@id=blog-pager-older-link]/a/@href").all();
        if(olds==null){
            spider.stop();
            return;
        }
        page.addTargetRequests(olds);

    }

    public Site getSite() {
        return site;
    }

    public void getHeadPage(Page page){

        //标题
        List<String> titles = page.getHtml().xpath("//div[@class='blog-posts hfeed']/div[@class=date-outer]//h3/a/text()").all();
        System.out.println("移除头条前tititle0 : "+titles.get(0));
        titles.remove(0);
        System.out.println("==========================================================");
        System.out.println("移除头条后tititle0 : "+titles.get(0));
        page.putField("title",titles);
        //链接
        page.putField("url",page.getHtml().regex("</a>(https?://[0-9a-z-]*\\.?[0-9a-z-]*\\.?[0-9a-z-]*\\.?[0-9a-z-]*:?[a-z0-9-]*[/\\.a-z0-9-]*).&?",1).all());
        //内容
        List<String> contexts = page.getHtml().xpath("//div[@class='post-body entry-content']/text()").all();
        contexts.remove(0);
        page.putField("context",contexts);
        //时间
        List<String> times = page.getHtml().xpath("//abbr[@class=published]/text()").all();
        times.remove(0);
        page.putField("time",times);

        //获取较旧的博客地址
        page.addTargetRequests(page.getHtml().xpath("//span[@id=blog-pager-older-link]/a/@href").all());
    }

    public static void main(String[] args) {

//        System.setProperty("webdriver.chrome.driver","E:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        spider = Spider.create(new mainProcessor())
                .setDownloader(new SeleniumDownloader())
                .addPipeline(new FilePipeline("d://result"))
                .addUrl("http://www.fangongheike.com/")
//                .addPipeline(new MySqlPipeline())
                .addPipeline(new SQLPipeline())
                .thread(4);
        spider.run();
   }


}
