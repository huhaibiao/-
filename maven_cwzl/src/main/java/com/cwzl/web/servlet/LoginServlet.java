package com.cwzl.web.servlet;

import com.cwzl.domain.ResultInfo;
import com.cwzl.domain.User;
import com.cwzl.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info = new ResultInfo();
        //验证码判断
        String checkCode = request.getParameter("checkCode");//获取客户端浏览器输入的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("CHECKCODE_SERVER");//获取服务器生成的验证码
        session.removeAttribute("CHECKCODE_SERVER");
        System.out.println(checkCode_session);
        if (checkCode ==null||!(checkCode.equalsIgnoreCase(checkCode_session))){
            info.setFlag(false);
            info.setMsg("验证码不正确");
            //响应数据
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //写回客户端
            //先设置一个content-type
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        }else {

            //获取用户信息
            Map<String, String[]> map = request.getParameterMap();
            //封装user对象
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            //调用service查询user
            UserServiceImpl service = new UserServiceImpl();
            User u = service.login(user);
            //判断用户是否存在
            if (u == null) {
                //密码或用户名错误
                info.setFlag(false);
                info.setMsg("用户名或密码错误");
            }
            //用户密码正确
            if (u != null) {
                info.setFlag(true);
                info.setMsg("登录成功");
                info.setData(u);
                request.getSession().setAttribute("user",info);//登录成功标记
            }

//        响应数据
//            ObjectMapper mapper = new ObjectMapper();
//            String json = mapper.writeValueAsString(info);
//            //写回客户端
//            //先设置一个content-type
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(json);

            //响应数据
            ObjectMapper mapper = new ObjectMapper();

            response.setContentType("application/json;charset=utf-8");
            mapper.writeValue(response.getOutputStream(),info);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
