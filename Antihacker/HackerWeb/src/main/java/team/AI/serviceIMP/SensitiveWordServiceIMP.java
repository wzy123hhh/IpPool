package team.AI.serviceIMP;

import com.alibaba.fastjson.JSONObject;
import team.AI.service.SenesitiveWordService;
import team.SensitiveWord.crawler.WebsiteProcessor;
import team.SensitiveWord.entity.UrlInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SensitiveWordServiceIMP implements SenesitiveWordService {

    @Override
    public int startCrawler(String url, int... type) {
        ArrayList<UrlInfo> urlInfos = WebsiteProcessor.StartCrawler(url, type);
        System.out.println(urlInfos);
        getResult(urlInfos);
        return 0;
    }

    @Override
    public String getResult(ArrayList<UrlInfo> infolist) {
        System.out.println("sss");
        //未爬取到信息
        if (infolist.size()==0){
            Map result= new HashMap();
            result.put("result","fail");
            result.put("info","请检查输入的url是否正确，或该站点禁制爬虫访问");
            System.out.println(JSONObject.toJSONString(result));
            return JSONObject.toJSONString(result);
        }else{
            statistics(infolist);
        }
        return null;
    }

    /**
     *
     * @param infolist
     * @return
     */
    @Override
    public String statistics(ArrayList<UrlInfo> infolist) {
        /**
         * 0：暴恐敏感词
         * 1：反动敏感词
         * 2：色情敏感词
         * 3：其他敏感词
         * 4：民生敏感词
         * 5：自定义敏感词
         */
        for (UrlInfo info : infolist) {
            if (info.getHits()!="无敏感词"){
                System.out.println(info.getHits());

            }
        }
        return null;

    }


}
