package team.AI.IMG;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DealUrl {
    public boolean isNet(String url){
        String str = "(http?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(url);
        return m.matches();
    }

    public String getNetwork(String network){
        Pattern pattern = Pattern.compile("https?://[0-9a-z-]*\\.?[0-9a-z-]*\\.?[0-9a-z-]*\\.?[0-9a-z-]*");
        Matcher matcher = pattern.matcher(network);
        if(matcher.find()){
            String group = matcher.group();
            return group;
        }else{
            return network;
        }
    }

    public String getUrl(String url){
        String substring = url.substring(0, 1);
        if(substring.equals("/")){
            return url;
        }else{
            return "/"+url;
        }
    }

}
