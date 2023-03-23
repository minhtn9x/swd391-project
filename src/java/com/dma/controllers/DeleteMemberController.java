/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers;

import com.dma.dao.ProjectDAO;
import com.dma.dto.MembershipDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import java.util.List;

import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giahu
 */


@WebServlet(name = "DeleteMemberController", urlPatterns = {"/DeleteMemberController"})
public class DeleteMemberController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = "";
            String msg = "";
            try {
                String uid = request.getParameter("uid");
                String pid = request.getParameter("pid");
                ProjectDAO pd = new ProjectDAO();
                MembershipDTO m = pd.getMembershipDetailByPID(pid);
                Date enddate = new Date(System.currentTimeMillis());
                if (m.getStartDate().compareTo(enddate) > 0) {
                    request.setAttribute("ERROR", "End Date cannot be before start date");
                    url = "MainController?action=projectDetail&pid=" + pid;
                } else {
                    pd.deleteMembership(uid, enddate);
                    request.setAttribute("STATUS2", "This member is no longer in the project");
                    url = "MainController?action=projectDetail&pid=" + pid;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
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
