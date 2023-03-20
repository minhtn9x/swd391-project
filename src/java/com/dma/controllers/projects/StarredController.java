package com.dma.controllers.projects;

import com.dma.dao.FilesDAO;
import com.dma.dto.GoogleFileDTO;
import com.dma.utils.AppUtils;
import static com.dma.utils.GetFiles.getGoogleFiles;
import static com.dma.utils.GetFiles.getGoogleFolders;
import static com.dma.utils.GetSubFolders.getGoogleSubFolders;
import static com.dma.utils.GetSubFolders.getGoogleSubFiles;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "StarredController", urlPatterns = {"/StarredController"})
public class StarredController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(StarredController.class);

    private static final String ERROR = AppUtils.pagePrefix +"groupShareStar.jsp";
    private static final String ERROR_PROJECT_NOT_SELECTED = "MainController?action=MyProjects";
    private static final String SUCCESS = AppUtils.pagePrefix +"groupShareStar.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String projectId= request.getParameter("projectId");
            FilesDAO dao = new FilesDAO();
            String googleFolderIdParent= request.getParameter("folderId");
            
            if (googleFolderIdParent == null) {
                List<String> rootFolderIds = dao.getRootFoldersStarred(projectId);
                List<String> fileIds = dao.getFilesStarred(projectId);
                if (!rootFolderIds.isEmpty()) {
                    List<GoogleFileDTO> folders = getGoogleFolders(rootFolderIds);
                    request.setAttribute("FOLDERS_DB", folders);
                } else {
                    request.setAttribute("ERROR_FOLDERS_DB", "rootFolders NOT FOUND");
                }
                if (!fileIds.isEmpty()) {
                    List<GoogleFileDTO> files = getGoogleFiles(fileIds);
                    request.setAttribute("FILES", files);
                } else {
                    request.setAttribute("ERROR_FILES", "rootFiles NOT FOUND");
                }
            } else {
                List<File> googleFolders = getGoogleSubFolders(googleFolderIdParent);
                List<GoogleFileDTO> googleFiles = getGoogleSubFiles(googleFolderIdParent);
                if (!googleFolders.isEmpty()) {
                    request.setAttribute("FOLDERS", googleFolders);
                } else {
                    request.setAttribute("ERROR_FOLDERS", "Folders NOT FOUND");
                }
                request.setAttribute("FILES", googleFiles);
            }

            url = SUCCESS;

        } catch (IOException | SQLException | ClassNotFoundException e) {
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
