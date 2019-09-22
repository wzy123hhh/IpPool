package team.AI.service;

import team.SensitiveWord.entity.UrlInfo;

import java.util.ArrayList;

public interface SenesitiveWordService {

    public int startCrawler(String url,int... type);

    public String getResult(ArrayList<UrlInfo> infolist);

    public String statistics(ArrayList<UrlInfo> infolist);
}
