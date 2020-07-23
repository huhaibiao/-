package com.cwzl.web.servlet;

import com.cwzl.domain.ResultInfo;
import com.cwzl.domain.Userinfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.awt.SystemColor.info;

@WebServlet("/fileUpLoadServlet")
public class fileUpLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("jjkkkk");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart == true) {
            // 创建文件项目工厂
            FileItemFactory factory = new DiskFileItemFactory();
            // 创建文件上传控柄
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求
            List<FileItem> items = null;
            try {
                items = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            // 处理上传项目
            Iterator<FileItem> iter = ((List) items).iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    // 处理表单字段
                    String fieldName = item.getFieldName();
                    String fieldValue = new String(item.getString().getBytes("iso-8859-1"), "utf-8");
                    response.getWriter().write(fieldName + ": " + fieldValue + "<br/>");
                } else {
                    // 获得上传文件名，包括路径
                    String filePath = item.getName();
                    // 获得上传文件的字节数
                    long sizeInBytes = item.getSize();
                    // 设置上传文件大小的条件
                    if (sizeInBytes > 10 * 1024 * 1024) {
//                        response.getWriter().write("<script type='text/javascript'>" +
//                                "    alert('文件大小不能超过10M！');" +
//                                "    window.location = 'upload.html';" +
//                                "</script>");
                    }

                    // 获取原文件名
                    String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
                    String extName = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
                    //创建允许文件类型列表
                    List<String> allowedList = new ArrayList<String>();
                    allowedList.add("txt");
                    allowedList.add("jpg");
                    allowedList.add("doc");
                    allowedList.add("rar");
                    boolean flag = false;
                    for (String ext : allowedList) {
                        if (ext.equals(extName)) {
                            flag = true;
                        }
                    }
                    if (!flag) {
//                        response.getWriter().write("<script type='text/javascript'>" +
//                                "    alert('上传文件类型只能为txt、jpg、doc、rar！');" +
//                                "    window.location = 'upload.html';" +
//                                "</script>");
                        return;
                    }
                    // 获取上传文件完整路径
                    String uploadFilePath = "E:\\ideaprogram\\maven_cwzl\\src\\main\\webapp\\images\\"+fileName;
//                String uploadFilePath = application.getRealPath("") + "upload\\" + fileName;
                    // 保存上传文件到指定位置
                    try {
                        item.write(new File(uploadFilePath));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    response.getWriter().write("文件上传成功！<br/>保存位置：" + uploadFilePath);
                }
            }
            //        响应数据
            Userinfo<Object> info = new Userinfo<>();

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            //写回客户端
            //先设置一个content-type
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        } else {
//            response.getWriter().write("没有需要上传的文件！");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
