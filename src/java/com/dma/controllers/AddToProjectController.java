/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers;

import com.dma.dao.ProjectDAO;
import com.dma.dto.ProjectDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giahu
 */
@WebServlet(name = "AddToProjectController", urlPatterns = {"/AddToProjectController"})
public class AddToProjectController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String uid = request.getParameter("uid");
            int pid = Integer.parseInt(request.getParameter("pid"));
            Date startDate = null;
            Date endDate = null;
            ProjectDAO p = new ProjectDAO();
            if (p.addMembers(pid, uid, startDate, endDate)) {
                response.setContentType("text/html");
                PrintWriter pw = response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('This member  has been added to project');");
                pw.println("</script>");
                String name = request.getParameter("PROJECT_NAME");
                request.setAttribute("PN", name);
                request.getRequestDispatcher(AppUtils.pagePrefix + "searchUsers.jsp").include(request, response);
            } else if (!p.addMembers(pid, uid, startDate, endDate)) {
                PrintWriter pw = response.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('This member has been already added to project');");
                pw.println("</script>");
                request.getRequestDispatcher(AppUtils.pagePrefix + "searchUsers.jsp").include(request, response);
            } else {
                request.setAttribute("error", "There something wrong please try again");
                request.getRequestDispatcher(AppUtils.pagePrefix + "searchUsers.jsp").forward(request, response);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
