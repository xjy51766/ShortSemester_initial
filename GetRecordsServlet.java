package com.program.controller;

import com.program.bean.ViolationRecord;
import com.program.dao.ViolationRecordDao;
import com.program.dao.Impl.ViolationRecordDaoImpl;
import com.program.tools.GsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/getRecords")
public class GetRecordsServlet extends HttpServlet {
    private ViolationRecordDao violationRecordDao = new ViolationRecordDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        List<ViolationRecord> records = violationRecordDao.selectAllRecord();
        if (records == null || records.isEmpty()) {
            System.out.println("未查询到记录");
        }
        String json = GsonUtils.toJson(records);

        response.getWriter().write(json);
    }
}