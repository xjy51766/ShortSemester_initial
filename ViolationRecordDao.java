package com.program.dao;

import com.program.bean.ViolationRecord;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ViolationRecordDao {
    public int insertRecord(ViolationRecord record);
    public int deleteById(int id);
    public List<ViolationRecord> selectAllRecord();
    public int updateRecordType(int id,String judgeType);
    public int selectIdBycreateTime(Date createTime);
}
