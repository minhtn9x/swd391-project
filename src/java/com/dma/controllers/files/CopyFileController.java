package com.dma.controllers.files;

import com.dma.dao.FilesDAO;
import com.dma.dto.GoogleFileDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import static com.dma.utils.CreateGoogleFile.copyGoogleFile;
import static com.dma.utils.GetFiles.getGoogleFile;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "CopyFileController", urlPatterns = {"/CopyFileController"})
public class CopyFileController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(CopyFileController.class);

    private static final String ERROR = "MainController?action=UserHome";
    private static final String SUCCESS = "MainController?action=UserHome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loggedUser = AppUtils.getLoginedUser(session);
            
            String currentFolderId = request.getParameter("folderId");
            if ("".equals(currentFolderId)) {
                currentFolderId = null;
            } else {
                url += "&folderId=" + currentFolderId;
            }

            String fileId = request.getParameter("fileId");
            GoogleFileDTO fileDTO = getGoogleFile(fileId);
            String fileName = "Copy of " + fileDTO.getName();
            File file = copyGoogleFile(currentFolderId, fileId, fileName);

            FilesDAO dao = new FilesDAO();
            dao.addFileToPersonal(loggedUser.getUserID(), currentFolderId == null ? "null" : currentFolderId, file.getId(), file.getName());

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
