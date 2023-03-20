package com.dma.controllers.user;

import com.dma.dao.UserDAO;
import com.dma.dto.RegisterUserErrorDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.AppUtils;
import com.dma.validation.Validation;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "AddAccountController", urlPatterns = {"/AddAccountController"})
public class AddAccountController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(AddAccountController.class);

    private static final String ERROR = AppUtils.pagePrefix +"newAccount.jsp";
    private static final String SUCCESS = "MainController?action=Accounts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        RegisterUserErrorDTO userError = new RegisterUserErrorDTO();
        boolean foundError = false;
        try {
            UserDAO dao = new UserDAO();
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String confirmPassword = request.getParameter("confirmPassword");
            RegisterUserErrorDTO userInput = new RegisterUserErrorDTO(userID, fullName,
                    address, birthday, phoneNumber, email);

            
        } catch (Exception e) {
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
