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
import com.dma.utils.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ViewProjectController", urlPatterns = {"/ViewProjectController"})
public class ViewProjectController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProjectDAO pd = new ProjectDAO();
        HttpSession session = request.getSession();
        UserDTO userdto = AppUtils.getLoginedUser(session);
        ArrayList<ProjectDTO> list = pd.getProjectsByMemberID(userdto.getUserID());
        request.setAttribute("listS", list);
        request.getRequestDispatcher(AppUtils.pagePrefix +"projectlist.jsp").forward(request, response);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          processRequest(request, response);

//            doGet(request, response);
//        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
