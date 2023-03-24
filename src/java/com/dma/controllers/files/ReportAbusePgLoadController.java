package com.dma.controllers.files;

import com.dma.dao.FilesDAO;
import com.dma.utils.AppUtils;
import com.google.api.services.drive.model.File;
import static com.dma.utils.UpdateFolder.removeGoogleFile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "ReportAbusePgLoadController", urlPatterns = {"/ReportAbusePgLoadController"})
public class ReportAbusePgLoadController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(ReportAbusePgLoadController.class);

    private static final String ERROR = AppUtils.pagePrefix + "reportAbuse.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix + "reportAbuse.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String url = ERROR;
            String fileId = request.getParameter("fileId");
            String fileName = request.getParameter("fileName");

            FilesDAO dao = new FilesDAO();
            String userId = dao.getOwner(fileId);

            request.setAttribute("OWNER", userId);
            request.setAttribute("FILE_NAME", fileName);

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
