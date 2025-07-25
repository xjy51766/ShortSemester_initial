package com.program.controller;

import com.program.dao.ViolationRecordDao;
import com.program.dao.Impl.ViolationRecordDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/deleteRecord")
public class DeleteRecordServlet extends HttpServlet {
    private ViolationRecordDao violationRecordDao = new ViolationRecordDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int affectedRows = violationRecordDao.deleteById(id);

        if (affectedRows > 0) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }
}