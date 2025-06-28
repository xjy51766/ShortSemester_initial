package com.program.tests;

import com.program.bean.ViolationRecord;
import com.program.dao.ViolationRecordDao;
import com.program.service.Impl.ViolationRecordServiceImpl;
import com.program.service.ViolationRecordService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestService {
    private static ViolationRecordService service;
    @BeforeAll
    public static void initService(){
         service = new ViolationRecordServiceImpl();
    }
    @Test
    public void testInsertViolationRecord(){
        /*int id = 9;*/
        String path = "D:/学习资料/图片/dsbx.png";
        String gestureclass = "one";
        Date time = new Date();
        String judgeType = "自动";
        ViolationRecord violationRecord = new ViolationRecord(path,gestureclass,time,judgeType);
        service.addRecord(violationRecord);
    }
    @Test
    public void testUpdateViolationRecord(){
        String judgeType = "自动";
        service.updateRecordType(1,judgeType);
    }
    @Test
    public void testDeleteViolationRecord(){
        service.deleteRecord(1);
    }
    @Test
    public void testSelectIdByCreateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date createTime = null;
        try {
            createTime = sdf.parse("2025-06-27 14:13:43");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (createTime != null) {
            int id = service.selectIdBycreateTime(createTime);
            System.out.println(id);
        }
    }
}
