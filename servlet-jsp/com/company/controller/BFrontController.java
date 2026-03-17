/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.company.controller;

import com.company.controller.BCommand;
import com.company.controller.BDeleteCommand;
import com.company.controller.BDetailCommand;
import com.company.controller.BModifyCommand;
import com.company.controller.BNextCommand;
import com.company.controller.BPrevCommand;
import com.company.controller.BUpdateCommand;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"*.basic"})
public class BFrontController
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.actionDo(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            this.actionDo(request, response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(":::::::ActionBasic<br><br>");
        String uri = request.getRequestURI();
        StringBuffer url = request.getRequestURL();
        String conPath = request.getContextPath();
        String path = uri.substring(conPath.length());
        out.println("1. " + uri + "<br/>");
        out.println("2. " + url.toString() + "<br/>");
        out.println("3. " + conPath + "<br/>");
        out.println("4. " + path + "<br/>");
        BCommand comm = null;
        if (path.equals("/detail.basic")) {
            comm = new BDetailCommand();
            comm.execute(request, response);
        } else if (path.equals("/modify.basic")) {
            comm = new BModifyCommand();
            comm.execute(request, response);
        } else if (path.equals("/update.basic")) {
            comm = new BUpdateCommand();
            comm.execute(request, response);
        } else if (path.equals("/delete.basic")) {
            comm = new BDeleteCommand();
            comm.execute(request, response);
        } else if (path.equals("/prev.basic")) {
            comm = new BPrevCommand();
            comm.execute(request, response);
        } else if (path.equals("/next.basic")) {
            comm = new BNextCommand();
            comm.execute(request, response);
        }
    }
}
