package team.AI.servlet;
import team.AI.serviceIMP.SensitiveWordServiceIMP;
import team.SensitiveWord.crawler.WebsiteProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SensitiveWord")
public class SensitiveWordServlet extends HttpServlet {

    SensitiveWordServiceIMP serviceIMP = new SensitiveWordServiceIMP();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String choose = req.getParameter("choose");
        String url = req.getParameter("url");
        String content = req.getParameter("content");
        System.out.println(choose);
        int type[];
        /**
         * 4,0
         * ffv
         * 法轮功，中共下台，中共伪政权
         */

        if (choose.trim()!=""&&choose!=null){
            String[] split = choose.split(",");

            if (content!=null){
                type = new int[split.length+1];
            }else{
                type = new int[split.length];
            }

            for (int i = 0; i < split.length; i++) {
                type[i]=Integer.parseInt(split[i]);
            }
            //启动爬虫
            serviceIMP.startCrawler(url,type);

        }else {
            serviceIMP.startCrawler(url,0,1,2,3,4);
            WebsiteProcessor.getInfoList();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
