package team.SensitiveWord.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理url
 */
public class urlTool {

    //匹配域名
    private static String domain = "((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+$,\\w]+@)?[A-Za-z0-9.-]+(:[0-9]+)?|(?:www.|[-;:&=\\+$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)";
    //匹配url
    private static String isurl="(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";

    /**
     * 验证url是否规范
     * @param url 用户传入url
     * @return boolean
     */
    public static boolean CheckUrl(String url){
        url = getSupplementUrl(url);
        Pattern res = Pattern.compile(isurl);
        Matcher matcher = res.matcher(url);
        if (matcher.find()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取域名
     * @param url 用户传入url
     * @return  域名
     */
    public static String getDomain(String url){
        url=getSupplementUrl(url);
        Pattern res = Pattern.compile(domain);
        Matcher matcher = res.matcher(url);
        if (matcher.find()){
            return matcher.group(0);
        }
        return null;
    }

    /**
     * 补充url 默认http协议
     * @param url
     * @return
     */
     public static String getSupplementUrl(String url){
         StringBuilder strurl = new StringBuilder("http://");
        if (url.startsWith("http")){
            return url;
        }else {
            strurl = strurl.append(url);
        }
        return strurl.toString();
     }

    /**
     * 生成爬取整站规则
     * @param url
     * @return
     */
     public static String getUrlRegex(String url){

         if (!url.endsWith("/")){
             return url+"/.*";
         } else {
             return url+".*";
         }

     }
}
