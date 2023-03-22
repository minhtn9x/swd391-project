package com.dma.controllers;

import com.dma.dao.UserDAO;
import com.dma.utils.AppUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "DeleteAccountController", urlPatterns = {"/DeleteAccountController"})
public class DeleteAccountController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(DeleteAccountController.class);
    private static final String ERROR = AppUtils.pagePrefix +"accounts.jsp";
    private static final String SUCCESS = "MainController?action=Accounts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String userID = request.getParameter("userID");
        if (userID != null) {
            try {
                UserDAO dao = new UserDAO();
                boolean checkDelete = dao.deleteUser(userID);
                if (checkDelete) {
                    url = SUCCESS;
                    LOGGER.info("Delete successfully!");
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
