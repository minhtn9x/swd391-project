package com.dma.controllers.projects;

import com.dma.dao.FilesDAO;
import com.dma.dao.ProjectDAO;
import com.dma.dto.FileSharedDTO;
import com.dma.dto.GoogleFileDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import static com.dma.utils.GetFiles.getGoogleFilesShared;
import static com.dma.utils.GetFiles.getGoogleFoldersShared;
import static com.dma.utils.GetFiles.getGoogleFolders;
import static com.dma.utils.GetSubFolders.getGoogleSubFolders;
import static com.dma.utils.GetSubFolders.getGoogleSubFiles;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "SharedWithMeController", urlPatterns = {"/SharedWithMeController"})
public class SharedWithMeController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(SharedWithMeController.class);

    private static final String ERROR = AppUtils.pagePrefix + "sharedWithMe.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix + "sharedWithMe.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String googleFolderIdParent = request.getParameter("folderId");

            HttpSession session = request.getSession();
            UserDTO loggedUser = AppUtils.getLoginedUser(session);
            FilesDAO fileDao = new FilesDAO();
            if (googleFolderIdParent == null) {
                Map<String, String> folderOwners= fileDao.getFolderOwner(loggedUser.getUserID());
                Map<String, String> fileOwners= fileDao.getFileOwner(loggedUser.getUserID());

                List<String> folderIds= new ArrayList<>(folderOwners.keySet());
                List<String> foOwners= new ArrayList<>(folderOwners.values());
                List<FileSharedDTO> folders = getGoogleFoldersShared(folderIds);
                for (int i = 0; i < foOwners.size(); i++) {
                    folders.get(i).setOwner(foOwners.get(i));
                }
                request.setAttribute("FOLDERS", folders);
                
                List<String> fileIds= new ArrayList<>(fileOwners.keySet());
                List<String> owners= new ArrayList<>(fileOwners.values());
                List<FileSharedDTO> files = getGoogleFilesShared(fileIds);
                for (int i = 0; i < owners.size(); i++) {
                    files.get(i).setOwner(owners.get(i));
                }
                request.setAttribute("FILES", files);
            } else {
                List<File> googleFolders = getGoogleSubFolders(googleFolderIdParent);
                List<GoogleFileDTO> googleFiles = getGoogleSubFiles(googleFolderIdParent);
                request.setAttribute("FOLDERS", googleFolders);
                request.setAttribute("FILES", googleFiles);

                List<String> googleFileIds = new ArrayList<>();
                googleFileIds.add(googleFolderIdParent);
                List<GoogleFileDTO> folders = getGoogleFolders(googleFileIds);
                
                Map<String, String> breadcrumbs= new TreeMap<>();
                String parentFolderIds = fileDao.getParentFolderIds(googleFolderIdParent);
                String parentFolderNames = fileDao.getParentFolderNames(googleFolderIdParent);
                String breadcrumbsUrl= "MainController?action=SharedWithMe";
                if (parentFolderNames.contains("/")) {
                    String[] nameParts = parentFolderNames.split("/");
                    String[] idParts = parentFolderIds.split("/");
                    for (int i = 0; i < nameParts.length; i++) {
                        if ("null".equals(nameParts[i])) {
                            breadcrumbs.put(breadcrumbsUrl, "Shared with me");
                        } else {
                            breadcrumbs.put(breadcrumbsUrl+"&folderId="+idParts[i], nameParts[i]);
                        }
                    }
                    breadcrumbs.put("z", folders.get(0).getName());
                } else {
                    breadcrumbs.put(breadcrumbsUrl, "Shared with me");
                    breadcrumbs.put("z", folders.get(0).getName());
                }
                
                request.setAttribute("BREADCRUMBS", breadcrumbs);
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
