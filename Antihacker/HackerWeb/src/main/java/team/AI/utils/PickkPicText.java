package team.AI.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PickkPicText {

    public static final String APP_ID = "17040487";
    public static final String API_KEY = "ohXVFvzv7CSz1qXxiXk9gNzm";
    public static final String SECRET_KEY = "WNo06jdkzYMo7axZZ93FH4RnSLEvM6Tk";
    AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

    public  ArrayList isDanger(String url) {
        ArrayList arrayList=new ArrayList();
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        JSONObject res = client.basicGeneralUrl(url, options);
        if(res.has("words_result")){
            JSONArray words_result = res.getJSONArray("words_result");
            for(int i=0;i<words_result.length();i++){
                JSONObject jsonObject = words_result.getJSONObject(i);
                String words =(String) jsonObject.get("words");
                arrayList.add(words);
            }
        }

        return arrayList;
    }

    public static void main(String[] args) {
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
        //isDanger("http://www.chzu.edu.cn/_upload/article/images/e2/ad/ca3eb6c44a709145bfeca0bf7dc3/f443a1b7-5f38-4312-9b55-d68aff4ab3d8.jpg");
    }
}
