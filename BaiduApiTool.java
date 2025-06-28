package com.program.tools;

import com.program.tools.Base64Util;
import com.program.tools.FileUtil;
import com.program.tools.HttpUtil;

import java.net.URLEncoder;
public class BaiduApiTool {
    // 手势识别API URL（固定值）
    private static final String GESTURE_API_URL = "https://aip.baidubce.com/rest/2.0/image-classify/v1/gesture";

    // 百度AI平台获取的access_token（需手动替换为真实值）
    private static final String ACCESS_TOKEN = "24.edcd1cb91ea6030652fea8403ea6926f.2592000.1753196316.282335-119270675";

    public static String recognizeGesture(String imagePath) {
        try {
            // 1. 读取图片文件为字节数组
            byte[] imgData = FileUtil.readFileByBytes(imagePath);

            // 2. 字节数组转Base64字符串
            String imgStr = Base64Util.encode(imgData);

            // 3. URL编码Base64字符串（符合API参数要求）
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            // 4. 构建请求参数（固定格式：image=Base64编码后数据）
            String param = "image=" + imgParam;

            // 5. 发送HTTP请求并获取结果
            String result = HttpUtil.post(GESTURE_API_URL, ACCESS_TOKEN, param);

            return result;
        } catch (Exception e) {
            System.err.println("百度API调用失败：" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
