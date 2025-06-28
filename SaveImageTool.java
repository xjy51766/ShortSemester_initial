package com.program.tools;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
public class SaveImageTool {
    /**
     * 完整封装Servlet中的图片保存逻辑
     * @param requestBody 前端请求体JSON数据
     * @param storageDir 存储根目录 例：D:/camera-screenshots/
     * @return 保存成功的完整路径，失败返回null
     */
    /*！！！！注意！！！！要配合
            @WebServlet("/save-image")

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = req.getReader().readLine()) != null) {
                sb.append(line);
            }
            String requestBody = sb.toString();使用*/
    public static String saveImageFromRequestBody(String requestBody, String storageDir) {
        try {
            /*// 1. 提取Base64图片数据
            String imageData = requestBody.substring(requestBody.indexOf("\"imageData\":\"") + 13);
            imageData = imageData.substring(0, imageData.indexOf("\"}"));
            imageData = imageData.replaceAll("\\\\/", "/");*/
            // 使用Gson解析JSON，自动处理格式问题
            Gson gson = new Gson();
            JsonObject jsonObj = gson.fromJson(requestBody, JsonObject.class);
            if (!jsonObj.has("imageData")) {
                System.err.println("请求体缺少imageData字段");
                return null;
            }
            String imageData = jsonObj.get("imageData").getAsString();
            imageData = imageData.replaceAll("\\\\/", "/");

            // 2. 生成存储路径
            String dateDir = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String fileName = "screenshot_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss")) +
                    "_" + UUID.randomUUID().toString().substring(0, 8) + ".png";
            String savePath = storageDir + dateDir + "/" + fileName;

            // 3. 确保目录存在
            Path dirPath = Paths.get(storageDir + dateDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // 4. 保存图片
            byte[] imageBytes = java.util.Base64.getDecoder().decode(imageData);
            Files.write(Paths.get(savePath), imageBytes, StandardOpenOption.CREATE_NEW);

            return savePath;
        } catch (Exception e) {
            System.err.println("图片保存失败：" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
