/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers;

import com.dma.dao.ProjectDAO;
import com.dma.dto.MembershipDTO;

import com.dma.dto.ProjectDTO;
import com.dma.listener.ContextListener;
import com.dma.utils.AppUtils;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author giahu
 */
@WebServlet(name = "ProjectEditController", urlPatterns = {"/ProjectEditController"})
public class ProjectEditController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ContextListener.class);

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

        /* TODO output your page here. You may use following sample code. */
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
        String id = request.getParameter("pid");
        ProjectDAO pd = new ProjectDAO();
        ProjectDTO p = pd.getProjectByID(id);
        List<MembershipDTO> list = pd.getProjectUserList(id);
        HttpSession session = request.getSession();
        request.setAttribute("list", list);
        session.setAttribute("pr", p);
        request.getRequestDispatcher(AppUtils.pagePrefix + "updateProject.jsp").forward(request, response);
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
        String url = "";
        try {
            String uid = request.getParameter("uid");
            String pid = request.getParameter("id");
            String pname = request.getParameter("name");
            String pdesc = request.getParameter("des");
            String inputstartPDate = request.getParameter("startpdate");
            String inputendPDate = request.getParameter("endpdate");
            Date startPDate = Date.valueOf(inputstartPDate);
            Date endPDate = Date.valueOf(inputendPDate);
            ProjectDAO pd = new ProjectDAO();
            boolean check1 = pd.updateProject(pid, pname, pdesc, startPDate, endPDate);
            if (check1) {
                request.setAttribute("STATUS1", "This project has been updated");
                url="MainController?action=projectDetail&pid=" + pid;

            } else {
                request.setAttribute("STATUS1", "Something wrong please try again");   
                url="MainController?action=projectDetail&pid=" + pid;
            }
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
