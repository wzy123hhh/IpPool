package team.SensitiveWord.crawler;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import team.SensitiveWord.entity.UrlInfo;
import team.SensitiveWord.util.SensitivewordFilter;
import team.SensitiveWord.util.urlTool;
import java.util.ArrayList;
import java.util.function.Consumer;


public class WebsiteProcessor extends BreadthCrawler {
    private  static  ArrayList<UrlInfo> infolists = new ArrayList<>();
    //爬取链接
    private static String crawerurl ="";
    private static SensitivewordFilter sensitivewordFilter = new SensitivewordFilter();
    //最大爬取链接数
    private static int MAXUrl=100;
    //爬取规则
    private static String crawerRegex="";
    //要匹配的敏感词库
    private static int[] sentype;

    public static ArrayList getInfoList(){
        return infolists;
    }

    public WebsiteProcessor(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        //爬取链接
        this.addSeed(crawerurl);
        this.addRegex(crawerRegex);
        this.addRegex("-.*\\.(jpg|png|gif|mp3|mp4|doc|docx|xls|pdf|rar|zip|7z).*");
        setRequester(new RandomRequest());
        //设置线程数
        setThreads(5);
        //爬取最大url数量
        getConf().setTopN(MAXUrl);
    }

    public static void main(String[] args) throws Exception {
       StartCrawler("http://www.chzu.edu.cn/");

       infolists.forEach(new Consumer<UrlInfo>() {
           @Override
           public void accept(UrlInfo info) {
               if (info.getHits()!="无敏感词")
               System.out.println(info.getUrl()+"--->"+info.getHits());
           }
       });
    }

    /**
     * 启动爬虫
     * @param url 要爬取的链接
     * @param stype 匹配的敏感词库类型
     * @return
     */
    public static ArrayList<UrlInfo> StartCrawler(String url,int... stype){
        //爬虫启动失败，url不符合规范
        if (!urlTool.CheckUrl(url)){
            return null;
        }
        //获取要匹配的敏感词库
        sentype = new int[stype.length];
        for (int i = 0; i < stype.length; i++) {
            sentype[i]=stype[i];
        }
        //获取要匹配的链接
        crawerurl=urlTool.getSupplementUrl(url);
        crawerRegex=urlTool.getUrlRegex(crawerurl);

        WebsiteProcessor crawler = new WebsiteProcessor("website", true);
        try {
            crawler.start(5);
        } catch (Exception e) {
            //服务器原因，启动失败
            return null;
        }
        return infolists;//启动成功
    }

    /**
     *
     * @param max 最大爬取链接数量
     * @param url
     * @param stype 匹配的敏感词库类型
     * @return
     */
    public static int StartCrawler(int max,String url,int... stype){
        //爬虫启动失败，url不符合规范
        if (!urlTool.CheckUrl(url)){
            return 0;
        }

        //最大爬取链接数量
        MAXUrl=max;

        //获取要匹配的敏感词库
        sentype = new int[stype.length];
        for (int i = 0; i < stype.length; i++) {
            sentype[i]=stype[i];
        }
        //获取要匹配的链接
        crawerurl=urlTool.getSupplementUrl(url);
        crawerRegex=urlTool.getUrlRegex(crawerurl);

        WebsiteProcessor crawler = new WebsiteProcessor("website", true);
        try {
            crawler.start(5);
        } catch (Exception e) {
            //服务器原因，启动失败
            return -1;
        }
        return 0;//启动成功
    }


    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        String url = page.url();
        if (page.matchUrl(crawerRegex)){
            try {
                String cont = ContentExtractor.getContentByUrl(page.url());

                if (cont.trim()!=""&&cont!=null){

                    UrlInfo info = new UrlInfo(page.url(),cont);
                    info = sensitivewordFilter.getSensitiveWord(info, 1,sentype);
                    infolists.add(info);
                }

            } catch (Exception e) {
               System.err.println("url连接失败--->"+page.url());
            }
        }
    }
}
