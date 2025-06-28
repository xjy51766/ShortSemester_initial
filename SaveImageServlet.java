package com.program.controller;

import com.program.tools.SaveImageTool;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 融合在GestureRecognitionServlet里，后期可删
 */
@WebServlet("/save-image")
public class SaveImageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SaveImageTool tool = new SaveImageTool();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = req.getReader().readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();

        // 修改为你希望的存储路径
        String STORAGE_DIR = "D:/camera-screenshots/";

        String imagePath = tool.saveImageFromRequestBody(requestBody, STORAGE_DIR);
    }
}
