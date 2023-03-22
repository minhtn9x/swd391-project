/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.controllers;

import static com.dma.controllers.ProjectEditController.LOGGER;
import com.dma.dao.ProjectDAO;
import com.dma.dto.MembershipDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author giahu
 */
@WebServlet(name = "EditUserController", urlPatterns = {"/EditUserController"})
public class EditUserController extends HttpServlet {

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
        String url="";
        String msg="";
        try{
            String pid = request.getParameter("pid");
            String uid = request.getParameter("uid");
            String inputstartDate = request.getParameter("startdate");
            String inputendDate = request.getParameter("enddate");
            Date startDate = Date.valueOf(inputstartDate);
            String inputendPDate = request.getParameter("endpdate");
            Date endPDate = Date.valueOf(inputendPDate);
            Date endDate = Date.valueOf(inputendDate);
            ProjectDAO pd = new ProjectDAO();
            String inputstartPDate = request.getParameter("startpdate");
            Date startPDate = Date.valueOf(inputstartPDate);
            System.out.println("updateMembership:");
            System.out.println("pid= "+ pid);
            System.out.println("uid= "+ uid);
            System.out.println("startDate= "+ startDate);
            System.out.println("endDate= "+ endDate);
            System.out.println("endPDate= "+ endPDate);
            System.out.println("startPDate= "+ startPDate);
            if(endDate.compareTo(endPDate)>0||startPDate.compareTo(startDate)>0){
            url="MainController?action=errorEdit&pid="+pid;
            }
            else if(pd.updateMembership(pid, uid, startDate, endDate)) {
                MembershipDTO m= pd.getMembershipByPID(pid, uid);
                request.setAttribute("M", m);
                request.setAttribute("STATUS","User info has been updated");
                url="MainController?action=projectDetail&pid=" + pid;
            }else{
                   request.setAttribute("STATUS","Something wrong please try again");  
                   url="MainController?action=projectDetail&pid=" + pid;;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher(url).forward(request, response);   
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
