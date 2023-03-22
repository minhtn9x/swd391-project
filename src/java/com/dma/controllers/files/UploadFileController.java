package com.dma.controllers.files;

import com.dma.dao.FilesDAO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import com.google.api.services.drive.model.File;
import com.dma.utils.UploadFileHelper;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "UploadFileController", urlPatterns = {"/UploadFileController"})
public class UploadFileController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(UploadFileController.class);

    private static final String ERROR = "MainController?action=UserHome";
    private static final String SUCCESS = "MainController?action=UserHome";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String currentFolderId= (String) request.getAttribute("CURRENT_FOLDER_ID");
            if ("".equals(currentFolderId)) {
                currentFolderId= null;
            }
            
            HttpSession session= request.getSession();
            UserDTO loggedUser= AppUtils.getLoginedUser(session);
            
            Map<String, InputStream> formItemsMap= (Map<String, InputStream>) request.getAttribute("FILES_IS");
            if (currentFolderId!= null) {
                url= ERROR +"&folderId="+ currentFolderId;
            }
            
            List<File> files = UploadFileHelper.uploadFile(currentFolderId, formItemsMap);
            if (!files.isEmpty()) {
                FilesDAO dao= new FilesDAO();
                for (File file : files) {
                    dao.addFileToPersonal(loggedUser.getUserID(), currentFolderId==null?"null":currentFolderId, file.getId(), file.getName());
                }
                if (currentFolderId!= null) {
                    url= SUCCESS +"&folderId="+ currentFolderId;
                } else {
                    url= SUCCESS;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
