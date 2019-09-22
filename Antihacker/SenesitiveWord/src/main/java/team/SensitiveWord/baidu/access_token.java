package team.SensitiveWord.baidu;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取百度云access token
 */
public class access_token {

    public static String getAuth(){
        String clientID = "UhbwmiRzGAeOyEULxrTZDlyh";
        String clientSecret = "c7c6QFKjMZS3YfgDkfSh38qbLBZ4clT8";
        return getAuth(clientID,clientSecret);
    }

    public static String getAuth(String clientID,String clientSecret){
        String authhost = "https://aip.baidubce.com/oauth/2.0/token?";
        //生成请求url
        String getAccessTokenUrl = authhost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + clientID
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + clientSecret;

        //发送获取请求
        try {
            URL realurl = new URL(getAccessTokenUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) realurl.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Map<String, List<String>> map = urlConnection.getHeaderFields();

            for(String key:map.keySet()){
                System.err.println(key + "--->" + map.get(key));
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            System.out.println(access_token);
            return access_token;

        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("获取失败");
            return null;
        }
    }

    public static void main(String[] args) {
        getAuth();
    }
}
