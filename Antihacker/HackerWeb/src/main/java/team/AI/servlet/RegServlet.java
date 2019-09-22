package team.AI.servlet;

import com.google.gson.Gson;
import team.AI.bean.JsonBean;
import team.AI.bean.UserBean;
import team.AI.serviceIMP.UserServiceIMP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
    用户注册
*/
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        String tel = request.getParameter("tel");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Map<String, String> map = new HashMap<String, String>();
        UserBean userBean = new UserBean();
        userBean.setName(username);
        userBean.setEmail(email);
        userBean.setPassword(password);
        userBean.setPhone(tel);
        UserServiceIMP userServiceIMP = new UserServiceIMP();
        Boolean isExistPhoneAndEmail = userServiceIMP.isExistPhoneAndEmail(userBean);

        JsonBean jsonBean=new JsonBean();
        if (isExistPhoneAndEmail) {
            Boolean reg = userServiceIMP.reg(userBean);
            if (reg) {
                System.out.println("注册成功");
            } else {
                System.out.println("注册失败");
                jsonBean.setFail2("服务器繁忙，请稍后再试");
            }
        } else {
            jsonBean.setFail1("手机号或者邮箱已经存在");
        }
        Gson gson=new Gson();
        String json = gson.toJson(jsonBean);
        response.getWriter().println(json);
    }

}

