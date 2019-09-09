package per.ippool.get;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * 西祠代理
 * https://www.xicidaili.com/nn/1
 */
public class xici_ipCrawler implements PageProcessor {

    private static Spider spider =null;
    private static int pageCount=1;
    private Site site = Site
            .me()
            .setTimeOut(10000)
            .setSleepTime(33)
            .setUserAgent(UserAgent.getRandomUserAgent())
//            .set
            .setRetrySleepTime(3000);

    public Site getSite(){
        return site;
    }

    public void process(Page page){
        //爬取十页
        if (!(pageCount==2)){
            System.out.println("获取page"+pageCount+".....");
            page.putField("ip",page.getHtml().xpath("//*[@id='ip_list']/tbody//tr/td[2]/text()").all());
            page.putField("port",page.getHtml().xpath("//*[@id='ip_list']/tbody//tr/td[3]/text()").all());

            pageCount++;
            page.addTargetRequest("https://www.xicidaili.com/nn/"+String.valueOf(pageCount));
        }
    }

    public static void main(String[] args) {


        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("114.221.42.112",61234)));

        spider = Spider.create(new xici_ipCrawler())
//                .setDownloader(httpClientDownloader)
//                .addPipeline(new FilePipeline("d://crawler//result"))
                .addUrl("https://www.xicidaili.com/nn/1")
                .addPipeline(new LocalPipleLine())
//                .addPipeline(new ConsolePipeline())
                .thread(2);
        spider.run();
    }
}
