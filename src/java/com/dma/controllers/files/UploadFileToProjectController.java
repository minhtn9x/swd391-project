package com.dma.controllers.files;

import com.dma.dao.FilesDAO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import static com.dma.utils.GetFiles.getGoogleFiles;
import com.google.api.services.drive.model.File;
import com.dma.utils.UploadFileHelper;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "UploadFileToProjectController", urlPatterns = {"/UploadFileToProjectController"})
public class UploadFileToProjectController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(UploadFileToProjectController.class);

    private static final String ERROR = "MainController?action=GroupShare";
    private static final String SUCCESS = "MainController?action=GroupShare";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String currentProjectId = (String) request.getAttribute("CURRENT_PROJECT_ID");
            if ("".equals(currentProjectId)) {
                request.setAttribute("ERROR", "ProjectID NOT FOUND");
                url = "MainController?action=MyProjects";
            } else {
                url = ERROR + "&projectId=" + currentProjectId;
                String currentFolderId = (String) request.getAttribute("CURRENT_FOLDER_ID");
                if ("".equals(currentFolderId)) {
                    currentFolderId = null;
                } else {
                    url+= "&folderId=" + currentFolderId;
                }
                Map<String, InputStream> formItemsMap = (Map<String, InputStream>) request.getAttribute("FILES_IS");
                List<File> files = UploadFileHelper.uploadFile(currentFolderId, formItemsMap);
                
                HttpSession session= request.getSession();
                UserDTO loggedUser= AppUtils.getLoginedUser(session);
                
                FilesDAO dao= new FilesDAO();
                for (File file : files) {
                    dao.addFileToProject(currentProjectId, currentFolderId==null?"null":currentFolderId, file.getId(), file.getName(), loggedUser.getUserID());
                }
            }
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
