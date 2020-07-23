package com.cwzl.web.servlet;

import com.cwzl.domain.User;
import com.cwzl.domain.Userinfo;
import com.cwzl.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/houTaiServlet1")
public class HouTaiServlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");//获取浏览器传递的参数
        String limit = request.getParameter("limit");

        //调用service方法查询
        UserServiceImpl service = new UserServiceImpl();
        Userinfo<User> pl = service.findUser(page,limit);
        System.out.println(pl);
        //写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pl);
        //写回客户端
        //先设置一个content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
