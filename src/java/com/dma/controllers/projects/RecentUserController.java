package com.dma.controllers.projects;

import com.dma.dao.FilesDAO;
import com.dma.dto.GoogleFileDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import static com.dma.utils.GetFiles.getGoogleFiles;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@WebServlet(name = "RecentUserController", urlPatterns = {"/RecentUserController"})
public class RecentUserController extends HttpServlet {
    static final Logger LOGGER = Logger.getLogger(RecentUserController.class);

    private static final String ERROR = AppUtils.pagePrefix + "recentUser.jsp";
    private static final String SUCCESS = AppUtils.pagePrefix + "recentUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            FilesDAO dao = new FilesDAO();

            HttpSession session = request.getSession();
            UserDTO loggedUser= AppUtils.getLoginedUser(session);
            List<String> rootFileIds = dao.getFilesByUserID(loggedUser.getUserID());
            if (!rootFileIds.isEmpty()) {
                List<GoogleFileDTO> files = getGoogleFiles(rootFileIds);
                List<GoogleFileDTO> filesThisWeek = new ArrayList<>();
                List<GoogleFileDTO> filesThisMonth = new ArrayList<>();
                List<GoogleFileDTO> filesThisYear = new ArrayList<>();
                List<GoogleFileDTO> filesNever = new ArrayList<>();
                
                Calendar calendar= Calendar.getInstance();
                Date nowDate= new Date();
                calendar.setTime(nowDate);
                int thisWeek= calendar.get(Calendar.WEEK_OF_YEAR);
                int thisMonth= calendar.get(Calendar.MONTH);
                int thisYear= calendar.get(Calendar.YEAR);
                for (GoogleFileDTO file : files) {
                    Date viewedByMeTime= file.getViewedByMeTime();
                    if (viewedByMeTime != null) {
                        calendar.setTime(viewedByMeTime);
                        if (thisWeek == calendar.get(Calendar.WEEK_OF_YEAR)) {
                            filesThisWeek.add(file);
                        } else if (thisMonth == calendar.get(Calendar.MONTH)) {
                            filesThisMonth.add(file);
                        } else if (thisYear == calendar.get(Calendar.YEAR)) {
                            filesThisYear.add(file);
                        }
                    } else {
                        filesNever.add(file);
                    }
                }
                request.setAttribute("FILES_THIS_WEEK", filesThisWeek);
                request.setAttribute("FILES_THIS_MONTH", filesThisMonth);
                request.setAttribute("FILES_THIS_YEAR", filesThisYear);
                request.setAttribute("FILES_NEVER", filesNever);
            } else {
                request.setAttribute("ERROR_FILES", "rootFiles NOT FOUND");
            }
            
            url = SUCCESS;

        } catch (IOException | ClassNotFoundException | SQLException e) {
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
