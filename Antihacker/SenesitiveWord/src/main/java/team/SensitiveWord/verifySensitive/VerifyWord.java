package team.SensitiveWord.verifySensitive;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.json.JSONObject;
import team.SensitiveWord.entity.UrlInfo;
import team.SensitiveWord.util.VerifyTool;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 验证敏感词
 */
public class VerifyWord {
    //获取token
    private static String token = "24.dfc9422f4f6cdaa7b746aae2a065f5dc.2592000.1571441282.282335-17272419";
//    private static  HttpClient client  =new HttpClient();
//    private static ByteArrayOutputStream out;

    /**
     * 获取请求参数
     * @param info
     * @return
     */
    public static NameValuePair[] getBody(UrlInfo info){

        try {
            //超过20000字节进行分割
            if (info.getContent().getBytes("utf-8").length>20000){
                List<String> lists = VerifyTool.splitBySize(info.getContent(),19999);
                NameValuePair[] np = new NameValuePair[lists.size()];

                for (int i = 0; i < lists.size(); i++) {
                    np[i]=new NameValuePair("content",lists.get(i));
                }

                return np;
            }else{
                NameValuePair[] np = new NameValuePair[]{new NameValuePair("content",info.getContent())};
                return np;
            }

        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    public static String Verify(UrlInfo info){

        NameValuePair[] body = getBody(info);

        //无内容
        if (body==null){
            return null;
        }

        //发送多条请求
        if (body.length>1){
            for (int i = 0; i < body.length; i++) {
                JSONObject resultjson = sendRequest(body[i]);
                VerifyTool.ResolveJson(resultjson);
            }
        }else{
            //发送一条请求
            JSONObject resultjson = sendRequest(body[0]);
            VerifyTool.ResolveJson(resultjson);
        }

        return null;

    }

    public static JSONObject sendRequest(NameValuePair np){

        HttpClient client  =new HttpClient();
        ByteArrayOutputStream out;

        PostMethod postMethod = new PostMethod("https://aip.baidubce.com/rest/2.0/antispam/v2/spam?access_token="+token);
        postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        postMethod.setRequestBody(new NameValuePair[]{np});

        try {
            client.executeMethod(postMethod);
            out = new ByteArrayOutputStream();
            String in = postMethod.getResponseBodyAsString();
            JSONObject json=new JSONObject(in);
            out.close();
            return json;


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error ---> "+VerifyWord.class.getName()+"----->"+"sendRequest");
            return null;
        }finally {
            postMethod.releaseConnection();
        }
    }

    public static void main(String[] args) {
        try {
            sendRequest(new NameValuePair("content","枪支贩卖"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
