package per.crawler.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;

public class JobProcessor implements PageProcessor {

    private Site site = Site.me()
            .setCharset("utf-8") //设置编码
            .setTimeOut(10000) //设置超时时间
            .setRetrySleepTime(3000) //设置重试时间
            .setSleepTime(3) //设置重试次数

            ;

    public void process(Page page) {
        page.putField("div",page.getHtml().css("span.Column_Anchor"));

        //模糊查询
//        page.putField("content",page.getHtml().xpath("//li[contains(@class,'column-news-item')]/span/a/text()").all());
        page.putField("content",page.getHtml().xpath("//span[@class=column-news-title]/a/@title").all());

//        page.putField("content",page.getHtml().regex(""));

        //获取链接
        page.addTargetRequests(page.getHtml().xpath("//span[@class=column-news-title]/a/@href").all());
        page.putField("head",page.getHtml().css("span.arti-update").get());

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                .addUrl("http://www.chzu.edu.cn/105/list.htm")
                .addPipeline(new FilePipeline("C:\\Users\\王智源\\Desktop\\res"))
                .thread(5)
                .run();
    }
}
