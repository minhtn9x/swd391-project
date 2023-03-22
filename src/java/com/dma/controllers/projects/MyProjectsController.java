package com.dma.controllers.projects;

import com.dma.dao.ProjectDAO;
import com.dma.dto.ProjectDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "MyProjectsController", urlPatterns = {"/MyProjectsController"})
public class MyProjectsController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(MyProjectsController.class);

    private static final String ERROR = AppUtils.pagePrefix +"groupShare.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix +"groupShare.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loggedUser = AppUtils.getLoginedUser(session);
            ProjectDAO dao = new ProjectDAO();
            ArrayList<ProjectDTO> projects= dao.getProjectsByMemberID(loggedUser.getUserID());
            if (!projects.isEmpty()) {
                request.setAttribute("PROJECTS", projects);
            } else {
                request.setAttribute("ERROR_MY_PROJECTS", "Projects NOT FOUND");
            }

            if (((String) request.getAttribute("ERROR")) != null) {
                request.setAttribute("ERROR", "Bin - Project not selected");
            }
            
            url = SUCCESS;

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
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
