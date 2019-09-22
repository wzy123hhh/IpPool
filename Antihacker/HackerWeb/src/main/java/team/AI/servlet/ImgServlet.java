package team.AI.servlet;

import team.AI.IMG.DealUrl;
import team.AI.IMG.ImgAddress;
import team.AI.utils.PickkPicText;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getParameter("url");
        if(!url.equals("")){
            ImgAddress imgAddress=new ImgAddress();
            ArrayList address = imgAddress.imgAddress(url);
            DealUrl dealUrl=new DealUrl();
            PickkPicText pickkPicText=new PickkPicText();
            Iterator iterator = address.iterator();
            while(iterator.hasNext()){
                String imgHalfAddr = (String)iterator.next();//图片地址
                if(dealUrl.isNet(imgHalfAddr)){
                    ArrayList danger = pickkPicText.isDanger(url);
                    Iterator iterator1 = danger.iterator();
                    while(iterator1.hasNext()){
                        System.out.println(iterator1.next());
                    }

                }else{
                    String network = dealUrl.getNetwork(url);
                    String addReve = dealUrl.getUrl(imgHalfAddr);
                    String fullUrlAddr=network+addReve;//图片地址
                    ArrayList danger = pickkPicText.isDanger(fullUrlAddr);
                    Iterator iterator1 = danger.iterator();
                    while(iterator1.hasNext()){
                        System.out.println(iterator1.next());
                    }
                }
            }
        }

    }
}
