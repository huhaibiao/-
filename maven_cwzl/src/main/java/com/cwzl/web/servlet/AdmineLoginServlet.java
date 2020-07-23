package com.cwzl.web.servlet;

import com.cwzl.domain.ResultInfo;
import com.cwzl.domain.Admine;
import com.cwzl.service.AdmineService;
import com.cwzl.service.impl.AdmineServiceImpl;
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

@WebServlet("/admineLoginServlet")
public class AdmineLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();

        Admine admine = new Admine();

        try {
            BeanUtils.populate(admine,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service查询
        AdmineService service = new AdmineServiceImpl();
        Admine a = service.login(admine);
        ResultInfo info = new ResultInfo();

        //判断用户是否存在
        if (a == null){
            //密码或用户名错误
            info.setFlag(false);
            info.setMsg("用户名或密码错误");
        }
        //用户密码正确
        if (a!=null){
            info.setFlag(true);
            info.setMsg("登录成功");
            info.setData(a);
        }

//        响应数据
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
