/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers;

import com.dma.dao.ProjectDAO;
import com.dma.dto.ProjectDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author giahu
 */
@WebServlet(name = "CreateProjectController", urlPatterns = {"/CreateProjectController"})
public class CreateProjectController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("projectname");
            String desc = request.getParameter("projectdesc");
            String inputStartDate = request.getParameter("startdate");
            String inputEndDate = request.getParameter("enddate");
            HttpSession session = request.getSession();
            UserDTO userdto = AppUtils.getLoginedUser(session);
            int isDeleted = 0;
            Date startDate = Date.valueOf(inputStartDate);
            Date endDate = Date.valueOf(inputEndDate);
            if (startDate.compareTo(endDate) > 0) {
                request.setAttribute("ERROR", "End Date cannot before start date");
                RequestDispatcher rd = request.getRequestDispatcher("MainController?action=createProject");
                rd.forward(request, response);
            } else if (ProjectDAO.insertProject(name, desc, userdto.getUserID(), startDate, endDate, isDeleted)) {
                request.setAttribute("STATUS", "This project has been created");
                ProjectDAO pd = new ProjectDAO();
                ProjectDTO p = pd.getLatestProject();
                ProjectDAO pd2 = new ProjectDAO();
                request.setAttribute("pr", p);
                pd2.addMembers(p.getProjectID(), userdto.getUserID(), startDate, endDate);
                RequestDispatcher rd = request.getRequestDispatcher("MainController?action=viewProject");
                rd.include(request, response);
            } else {
                request.setAttribute("STATUS","Something wrong please try again");
                ProjectDAO pd = new ProjectDAO();
                ProjectDTO p = pd.getLatestProject();
                request.setAttribute("pr", p);
                request.getRequestDispatcher("MainController?action=viewProject").forward(request, response);
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
