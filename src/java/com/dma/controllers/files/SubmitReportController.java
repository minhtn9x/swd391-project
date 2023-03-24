package com.dma.controllers.files;

import com.dma.dao.FeedbackDAO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "SubmitReportController", urlPatterns = {"/SubmitReportController"})
public class SubmitReportController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(SubmitReportController.class);

    private static final String ERROR = AppUtils.pagePrefix +"reportAbuse.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix +"groupShare.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String url = ERROR;
            
            HttpSession session= request.getSession();
            UserDTO loggedUser= AppUtils.getLoginedUser(session);
            
            String selector= request.getParameter("selector");
            String projectId= request.getParameter("projectId");
            String fileId= request.getParameter("fileId");
            System.out.println("selector= "+ selector);
            System.out.println("projectId= "+ projectId);
            System.out.println("fileId= "+ fileId);
            
            FeedbackDAO dao= new FeedbackDAO();
            String msg= "";
            dao.reportFile(msg, "Report File", new java.sql.Date(new Date().getTime()), loggedUser.getUserID());
            
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.error(ex);
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
