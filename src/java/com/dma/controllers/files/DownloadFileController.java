package com.dma.controllers.files;

import static com.dma.utils.DownloadFile.downloadFile;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "DownloadFileController", urlPatterns = {"/DownloadFileController"})
public class DownloadFileController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(DownloadFileController.class);

    private static final String ERROR = "MainController?action=UserHome";
    private static final String SUCCESS = "MainController?action=UserHome";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        String fileId = request.getParameter("fileId");
        String fileName = request.getParameter("fileName");
        ByteArrayOutputStream baos = downloadFile(fileId);
        byte[] byteArr = baos.toByteArray();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setContentLength(byteArr.length);

        ServletOutputStream os = response.getOutputStream();

        try {
            os.write(byteArr, 0, byteArr.length);
        } catch (IOException e) {
        } finally {
            os.close();
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
