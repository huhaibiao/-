package com.cwzl.web.servlet;

import com.cwzl.domain.ResultInfo;
import com.cwzl.domain.User;
import com.cwzl.service.UserService;
import com.cwzl.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置request编码为utf-8
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service完成注册
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //响应结果
        if (flag){
            //注册成功
            info.setFlag(true);
            info.setMsg("注册成功！");
        }else {
            //注册失败
            info.setFlag(false);
            info.setMsg("注册失败！");
        }
        //将info对象序列化为json，写回客户端
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        //写回客户端
        //先设置一个content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
