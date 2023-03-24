package com.dma.controllers.files;

import com.dma.dao.FilesDAO;
import com.dma.dao.UserDAO;
import com.dma.dto.PermissionDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "LoadShareFileController", urlPatterns = {"/LoadShareFileController"})
public class LoadShareFileController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(LoadShareFileController.class);

    private static final String ERROR = AppUtils.pagePrefix +"share.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix +"share.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String fileId = request.getParameter("fileId");
            String fileName = request.getParameter("fileName");

            FilesDAO dao = new FilesDAO();
            List<PermissionDTO> peopleWithAccess = dao.getPeopleWithAccess(fileId);

            UserDAO userDAO = new UserDAO();
            List<UserDTO> users = userDAO.getUsersExceptAdmin(fileId);

            HttpSession session = request.getSession();

            session.setAttribute("FILE_USERS", peopleWithAccess);
            session.setAttribute("USERS", users);
            session.setAttribute("FILE_ID", fileId);
            session.setAttribute("FILE_NAME", fileName);
        } catch (SQLException | ClassNotFoundException e) {
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
