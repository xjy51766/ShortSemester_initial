package com.program.controller;

import com.program.bean.RecognitionResult;
import com.program.bean.ResultItem;
import com.program.service.Impl.ViolationRecordServiceImpl;
import com.program.service.ViolationRecordService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet("/api/gesture/feedback")
public class GestureFeedbackServlet extends HttpServlet {
    private ViolationRecordService violationRecordService = new ViolationRecordServiceImpl();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 读取前端JSON（格式：{ "result": [{"id":13,"url":"yes"}, ...] }）
        BufferedReader reader = request.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        String json = jsonBuilder.toString();

        System.out.println("收到反馈请求: " + json); // 添加日志记录

        // 2. 复用RecognitionResult解析（忽略result_num，仅用result数组）
        Type type = new TypeToken<RecognitionResult>() {}.getType();
        RecognitionResult feedback = gson.fromJson(json, type);

        // 3. 处理反馈结果
        List<ResultItem> results = feedback.getResult();
        if (results == null || results.isEmpty()) {
            response.getWriter().println("未收到有效反馈");
            return;
        }

        PrintWriter out = response.getWriter();
        for (ResultItem item : results) {
            int recordId = item.getId();
            String feedbackFlag = item.getUrl();  // yes/no 反馈标记

            try {
                if ("yes".equalsIgnoreCase(feedbackFlag)) {
                    // ✅ 直接调用updateRecordType修改judgeType
                    violationRecordService.updateRecordType(recordId, "人工");
                    out.println("ID=" + recordId + " 已修改为人工审核");
                    System.out.println("ID=" + recordId + " 已修改为人工审核");
                } else if ("no".equalsIgnoreCase(feedbackFlag)) {
                    // ✅ 直接删除记录（返回值是int，0表示失败，非0表示成功）
                    int affectedRows = violationRecordService.deleteRecord(recordId);
                    if (affectedRows > 0) {
                        out.println("ID=" + recordId + " 已删除");
                        System.out.println("ID=" + recordId + " 已删除");
                    } else {
                        out.println("ID=" + recordId + " 删除失败（无对应记录或数据库异常）");
                        System.err.println("ID=" + recordId + " 删除失败");
                    }
                } else {
                    out.println("ID=" + recordId + " 无效标记：" + feedbackFlag);
                    System.err.println("ID=" + recordId + " 无效标记：" + feedbackFlag);
                }
            } catch (Exception e) {
                out.println("ID=" + recordId + " 处理异常：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}