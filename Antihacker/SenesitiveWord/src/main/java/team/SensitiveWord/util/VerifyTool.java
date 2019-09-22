package team.SensitiveWord.util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VerifyTool {

    public static List<String> splitBySize(String value, int length) {
        char[] cs = value.toCharArray();
        StringBuilder result = new StringBuilder();
        List<String> resultList = new ArrayList<String>();
        int index = 0;
        for (char c: cs){
            index += String.valueOf(c).getBytes().length;
            if (index > length){
                resultList.add(result.toString());
                result.delete(0,index-1);
                index = 0;
            }else {
                result.append(c);
            }
        }
        return resultList;
    }


    public static String[] ResolveJson(JSONObject json){
        System.out.println(json.toString());
        Object reject = json.get("reject");
        System.out.println(reject.toString());
        return null;
    }

}
