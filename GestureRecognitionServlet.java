package com.program.controller;

import com.program.bean.RecognitionResult;
import com.program.service.ViolationRecordService;
import com.program.service.Impl.ViolationRecordServiceImpl;
import com.program.tools.GsonUtils;
import com.program.tools.SaveImageTool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * 整合图像保存与手势识别的Servlet，调用 ViolationRecordService 处理业务逻辑
 * 流程：接收前端图像数据 -> 保存图像 -> 调用业务层进行手势识别及规则判定 -> 返回结果给前端
 * !!!!!!!修改前端返回后端的地址
 */

@WebServlet("/api/gesture/process")
public class GestureRecognitionServlet extends HttpServlet {

    // 业务层对象，用于处理手势识别相关业务
    private ViolationRecordService violationRecordService;

    @Override
    public void init() throws ServletException {
        // 初始化业务层实现类，这里简单直接实例化
        violationRecordService = new ViolationRecordServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型为 JSON，编码为 UTF-8
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 1. 读取前端请求体数据
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }
            String requestBody = sb.toString();

            // 2. 调用工具类保存图像到指定目录
            String storageDir = "D:/camera-screenshots/";//存储根目录
            String imagePath = SaveImageTool.saveImageFromRequestBody(requestBody, storageDir);

            if (imagePath == null) {
                out.write("{\"error\":\"图像保存失败，请重试\"}");
                return;
            }

            // 3. 调用业务层方法处理手势识别及相关逻辑
            RecognitionResult result = violationRecordService.processGestureRecognition(imagePath);

            // 4. 将识别结果转换为 JSON 并返回给前端
            out.write(GsonUtils.toJson(result));

        } catch (Exception e) {
            // 捕获异常，返回错误信息给前端
            System.err.println("手势识别处理异常: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(500);
            out.write("{\"error\":\"" + e.getMessage() + "\"}");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}