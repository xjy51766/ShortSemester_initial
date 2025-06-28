package com.program.tests;

import com.program.tools.BaiduApiTool;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestBaiduApiTools {
    private static BaiduApiTool baiduTools;

    @BeforeAll
    public static void initJDBCTools() {
        baiduTools = new BaiduApiTool();
    }

    @Test
    public void testGesture(){
        String url = "D:/学习资料/图片/dsbx.png";
        String result = baiduTools.recognizeGesture(url);
        System.out.println(result);
    }
}
