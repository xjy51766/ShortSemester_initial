package com.program.service;

import com.program.bean.RecognitionResult;
import com.program.bean.ViolationRecord;

import java.util.Date;
import java.util.List;

public interface ViolationRecordService {
    public int addRecord (ViolationRecord record);
    public int deleteRecord(int id);
    public List<ViolationRecord> getAllViolationRecords();
    public int updateRecordType(int id,String judgeType);
    public List<ViolationRecord> selectRecordsByPage(
            int page,
            int pageSize,
            String search,
            String sortField,
            String sortDirection
    );
    /**
     * 处理手势识别结果
     * @param imagePath 图片路径
     * @return 识别结果封装对象（符合前端交互格式）
     */
    RecognitionResult processGestureRecognition(String imagePath);
    public int selectIdBycreateTime(Date createTime);
}
