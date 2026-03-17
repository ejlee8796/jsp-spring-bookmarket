/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.company.controller;

import com.company.controller.BCommand;
import com.company.dao.BDao;
import com.company.dto.Dto;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BModifyCommand
implements BCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        BDao dao = new BDao();
        int bno = Integer.parseInt(request.getParameter("bno"));
        Dto dto = dao.fbModify(bno);
        request.setAttribute("dto", (Object)dto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index1view1/modify.jsp");
        dispatcher.forward((ServletRequest)request, (ServletResponse)response);
    }
}
