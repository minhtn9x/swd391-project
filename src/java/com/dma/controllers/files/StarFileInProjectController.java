package com.dma.controllers.files;

import com.dma.dao.FilesDAO;
import com.google.api.services.drive.model.File;
import static com.dma.utils.UpdateFolder.starGoogleFile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "StarFileInProjectController", urlPatterns = {"/StarFileInProjectController"})
public class StarFileInProjectController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(StarFileInProjectController.class);

    private static final String ERROR = "MainController?action=GroupShare";
    private static final String SUCCESS = "MainController?action=GroupShare";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String projectId= request.getParameter("projectId");
            url= ERROR +"&projectId="+ projectId;
            String fileId= request.getParameter("fileId");
            
            FilesDAO dao= new FilesDAO();
            
            File file= starGoogleFile(fileId, Boolean.TRUE);
            dao.starFile(fileId, 1);
            
            if (file!= null) {
                url= SUCCESS +"&projectId="+ projectId;
            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            LOGGER.error(e);
        } finally {
            response.sendRedirect(url);
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
