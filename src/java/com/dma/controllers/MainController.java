package com.dma.controllers;

import com.dma.utils.AppUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class MainController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(MainController.class);

    private static final String ERROR = AppUtils.pagePrefix + "error.jsp";
    private static final String HOME = "Home";
    private static final String HOME_PAGE = AppUtils.pagePrefix + "login.jsp";
    private static final String PROFILE = "Profile";
    private static final String PROFILE_PAGE = AppUtils.pagePrefix + "profile.jsp";
    private static final String FORGOT_PW = "ForgotPassword";
    private static final String FORGOT_PW_PAGE = AppUtils.pagePrefix + "forgotPassword.jsp";
    private static final String CREATE_ACCOUNT = "CreateAccount";
    private static final String CREATE_ACCOUNT_PAGE = AppUtils.pagePrefix + "registration.jsp";
    private static final String LOGIN = "Sign in";
    private static final String LOGIN1 = "login";
    private static final String LOGIN_PAGE = AppUtils.pagePrefix + "login.jsp";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String HOME_INDEX = "home";
    private static final String HOME_PAGE_INDEX = AppUtils.pagePrefix + "index.jsp";
    private static final String CREATEDPROJECT = "Create Project";
    private static final String CREATEPROJECTD_CONTROLLER = "CreateProjectController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String REGISTER = "Register";
    private static final String REGISTER_CONTROLLER = "AddUserController";
    private static final String FORGOT_PASSWORD = "forgotPassword";
    private static final String FORGOT_PASSWORD_CONTROLLER = "ForgotPasswordController";
    private static final String VALIDATE_OTP = "Reset Password";
    private static final String VALIDATE_OTP_CONTROLLER = "ValidateOTPController";
    private static final String RESET_PASSWORD = "Reset";
    private static final String RESET_PASSWORD_CONTROLLER = "ResetPasswordController";
    private static final String PROFILE_EDIT = "Save Changes";
    private static final String PROFILE_EDIT_CONTROLLER = "UpdateProfileController";
    private static final String ACCOUNTS = "Accounts";
    private static final String ACCOUNTS_CONTROLLER = "AccountsController";
    private static final String ACCOUNT_DETAILS = "AccountDetails";
    private static final String ACCOUNT_DETAILS_CONTROLLER = "AccountDetailsController";
    private static final String ACCOUNT_DETAILS_EDIT = "Save";
    private static final String ACCOUNT_DETAILS_EDIT_CONTROLLER = "UpdateAccountDetailsController";
    private static final String ACCOUNT_NEW = "Add new";
    private static final String ACCOUNT_NEW_CONTROLLER = "AddAccountController";
    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteAccountController";
    private static final String VIEWPROJECT = "viewProject";
    private static final String VIEWPROJECT_CONTROLLER = "ViewProjectController";
    private static final String CREATEPROJECT = "createProject";
    private static final String CREATEPROJECTPAGE = AppUtils.pagePrefix + "createProject.jsp";
    private static final String SEARCHUSER = "searchUser";
    private static final String SEARCHUSER_CONTROLLER = "SearchUserController";
    private static final String ADDTOPROJECT = "addToProject";
    private static final String ADDTOPROJECT_CONTROLLER = "AddToProjectController";
    private static final String USER_HOME = "UserHome";
    private static final String USER_HOME_CONTROLLER = "UserHomeController";
    private static final String UPLOAD_FOLDER = "UploadFolder";
    private static final String UPLOAD_FOLDER_CONTROLLER = "UploadFolderController";
    private static final String UPLOAD_FILE = "UploadFile";
    private static final String UPLOAD_FILE_CONTROLLER = "UploadFileController";
    private static final String UPLOAD_FILE_TO_PROJECT = "UploadFileToProject";
    private static final String UPLOAD_FILE_TO_PROJECT_CONTROLLER = "UploadFileToProjectController";
    private static final String FEEDBACK = "feedBack";
    private static final String FEEDBACK_PAGE = AppUtils.pagePrefix + "feedBack.jsp";
    private static final String PROJECTDETAIL = "projectDetail";
    private static final String PROJECTDETAIL_CONTROLLER = "ProjectDetailController";
    private static final String PROJECTEDIT = "projectEdit";
    private static final String PROJECTEDIT_PAGE = "ProjectEditController";
    private static final String PROJECTEDITUSERCHANGE = " Save User Date";
    private static final String PROJECTEDITUSERCHANGE_CONTROLLER = "ProjectEditController";
    private static final String PROJECTEDITCHANGE = "Save Project Profile";
    private static final String PROJECTEDITCHANGE_CONTROLLER = "ProjectEditController";
    private static final String ADDMEMBER = "addMember";
    private static final String ADDMEMBER_PAGE = AppUtils.pagePrefix + "searchUsers.jsp";
    private static final String SENDFEEDBACK = "Send FeedBack";
    private static final String FEEDBACK_CONTROLLER = "FeedbackController";
    private static final String DELETEFROMPROJECT = "deleteMember";
    private static final String DELETEFROMPROJECT_CONTROLLER = "DeleteMemberController";
    private static final String MY_PROJECTS = "MyProjects";
    private static final String MY_PROJECTS_CONTROLLER = "MyProjectsController";
    private static final String GROUP_SHARE = "GroupShare";
    private static final String GROUP_SHARE_CONTROLLER = "GroupShareController";
    private static final String BIN = "Bin";
    private static final String BIN_CONTROLLER = "BinController";
    private static final String BIN_USER = "BinUser";
    private static final String BIN_USER_CONTROLLER = "BinUserController";
    private static final String RENAME_FOLDER = "Rename Folder";
    private static final String RENAME_FOLDER_CONTROLLER = "RenameFolderController";
    private static final String CREATE_FOLDER = "Create Folder";
    private static final String CREATE_FOLDER_CONTROLLER = "CreateFolderController";
    private static final String CREATE_FOLDER_USER = "Create Folder User";
    private static final String CREATE_FOLDER_USER_CONTROLLER = "CreateFolderUserController";
    private static final String REMOVE_FOLDER = "RemoveFolder";
    private static final String REMOVE_FOLDER_CONTROLLER = "RemoveFolderController";
    private static final String RECENT = "Recent";
    private static final String RECENT_CONTROLLER = "RecentController";
    private static final String RECENT_USER = "RecentUser";
    private static final String RECENT_USER_CONTROLLER = "RecentUserController";
    private static final String COPY_FILE = "CopyFile";
    private static final String COPY_FILE_CONTROLLER = "CopyFileController";
    private static final String COPY_FILE_RECENT = "CopyFileInRecent";
    private static final String COPY_FILE_RECENT_CONTROLLER = "CopyFileInRecentController";
    private static final String COPY_FILE_PROJECT = "CopyFileInProject";
    private static final String COPY_FILE_PROJECT_CONTROLLER = "CopyFileInProjectController";
    private static final String COPY_FILE_STARRED = "CopyFileInStarred";
    private static final String COPY_FILE_STARRED_CONTROLLER = "CopyFileInStarredController";
    private static final String COPY_FILE_STARRED_USER = "CopyFileInStarredUser";
    private static final String COPY_FILE_STARRED_USER_CONTROLLER = "CopyFileInStarredUserController";
    private static final String RENAME_FILE_RECENT = "RenameFileInRecent";
    private static final String RENAME_FILE_RECENT_CONTROLLER = "RenameFileInRecentController";
    private static final String RENAME_FILE_RECENT_USER = "RenameFileInRecentUser";
    private static final String RENAME_FILE_RECENT_USER_CONTROLLER = "RenameFileInRecentUserController";
    private static final String RENAME_FILE_PROJECT = "RenameFileInProject";
    private static final String RENAME_FILE_PROJECT_CONTROLLER = "RenameFileInProjectController";
    private static final String RENAME_FILE_STARRED = "RenameFileInStarred";
    private static final String RENAME_FILE_STARRED_CONTROLLER = "RenameFileInStarredController";
    private static final String RENAME_FILE_STARRED_USER = "RenameFileInStarredUser";
    private static final String RENAME_FILE_STARRED_USER_CONTROLLER = "RenameFileInStarredUserController";
    private static final String REMOVE_FILE = "RemoveFile";
    private static final String REMOVE_FILE_CONTROLLER = "RemoveFileController";
    private static final String REMOVE_FILE_PROJECT = "RemoveFileInProject";
    private static final String REMOVE_FILE_PROJECT_CONTROLLER = "RemoveFileInProjectController";
    private static final String REMOVE_FILE_RECENT = "RemoveFileInRecent";
    private static final String REMOVE_FILE_RECENT_CONTROLLER = "RemoveFileInRecentController";
    private static final String REMOVE_FILE_RECENT_USER = "RemoveFileInRecentUser";
    private static final String REMOVE_FILE_RECENT_USER_CONTROLLER = "RemoveFileInRecentUserController";
    private static final String REMOVE_FILE_STARRED = "RemoveFileInStarred";
    private static final String REMOVE_FILE_STARRED_CONTROLLER = "RemoveFileInStarredController";
    private static final String REMOVE_FILE_STARRED_USER = "RemoveFileInStarredUser";
    private static final String REMOVE_FILE_STARRED_USER_CONTROLLER = "RemoveFileInStarredUserController";
    private static final String STAR_FILE = "StarFile";
    private static final String STAR_FILE_CONTROLLER = "StarFileController";
    private static final String STAR_FILE_PROJECT = "StarFileInProject";
    private static final String STAR_FILE_PROJECT_CONTROLLER = "StarFileInProjectController";
    private static final String STAR_FILE_RECENT = "StarFileInRecent";
    private static final String STAR_FILE_RECENT_CONTROLLER = "StarFileInRecentController";
    private static final String STAR_FILE_RECENT_USER = "StarFileInRecentUser";
    private static final String STAR_FILE_RECENT_USER_CONTROLLER = "StarFileInRecentUserController";
    private static final String REPORT_FILE = "ReportFile";
    private static final String REPORT_FILE_PAGE_LOAD_CONTROLLER = "ReportAbusePgLoadController";
    private static final String SUBMIT_REPORT = "SubmitReport";
    private static final String SUBMIT_REPORT_CONTROLLER = "SubmitReportController";
    private static final String STAR_FOLDER = "StarFolder";
    private static final String STAR_FOLDER_CONTROLLER = "StarFolderController";
    private static final String STAR_FOLDER_PROJECT = "StarFolderInProject";
    private static final String STAR_FOLDER_PROJECT_CONTROLLER = "StarFolderInProjectController";
    private static final String RENAME_FOLDER_PROJECT = "RenameFolderInProject";
    private static final String RENAME_FOLDER_PROJECT_CONTROLLER = "RenameFolderInProjectController";
    private static final String RENAME_FOLDER_STARRED = "RenameFolderInStarred";
    private static final String RENAME_FOLDER_STARRED_CONTROLLER = "RenameFolderInStarredController";
    private static final String RENAME_FOLDER_STARRED_USER = "RenameFolderInStarredUser";
    private static final String RENAME_FOLDER_STARRED_USER_CONTROLLER = "RenameFolderInStarredUserController";
    private static final String REMOVE_FOLDER_PROJECT = "RemoveFolderInProject";
    private static final String REMOVE_FOLDER_PROJECT_CONTROLLER = "RemoveFolderInProjectController";
    private static final String REMOVE_FOLDER_STARRED = "RemoveFolderInStarred";
    private static final String REMOVE_FOLDER_STARRED_CONTROLLER = "RemoveFolderInStarredController";
    private static final String REMOVE_FOLDER_STARRED_USER = "RemoveFolderInStarredUser";
    private static final String REMOVE_FOLDER_STARRED_USER_CONTROLLER = "RemoveFolderInStarredUserController";
    private static final String REMOVE_STAR_FOLDER = "RemoveStarFolder";
    private static final String REMOVE_STAR_FOLDER_CONTROLLER = "RemoveStarFolderController";
    private static final String REMOVE_STAR_FOLDER_USER = "RemoveStarFolderUser";
    private static final String REMOVE_STAR_FOLDER_USER_CONTROLLER = "RemoveStarFolderUserController";
    private static final String REMOVE_STAR_FILE = "RemoveStarFile";
    private static final String REMOVE_STAR_FILE_CONTROLLER = "RemoveStarFileController";
    private static final String REMOVE_STAR_FILE_USER = "RemoveStarFileUser";
    private static final String REMOVE_STAR_FILE_USER_CONTROLLER = "RemoveStarFileUserController";
    private static final String RESTORE_FOLDER = "RestoreFolder";
    private static final String RESTORE_FOLDER_CONTROLLER = "RestoreFolderController";
    private static final String RESTORE_FOLDER_USER = "RestoreFolderUser";
    private static final String RESTORE_FOLDER_USER_CONTROLLER = "RestoreFolderUserController";
    private static final String RESTORE_FILE = "RestoreFile";
    private static final String RESTORE_FILE_CONTROLLER = "RestoreFileController";
    private static final String RESTORE_FILE_USER = "RestoreFileUser";
    private static final String RESTORE_FILE_USER_CONTROLLER = "RestoreFileUserController";
    private static final String STARRED = "Starred";
    private static final String STARRED_CONTROLLER = "StarredController";
    private static final String STARRED_USER = "StarredUser";
    private static final String STARRED_USER_CONTROLLER = "StarredUserController";
    private static final String DOWNLOAD_FILE = "DownloadFile";
    private static final String DOWNLOAD_FILE_CONTROLLER = "DownloadFileController";
    private static final String DELETE_FILE = "Delete File";
    private static final String DELETE_FILE_CONTROLLER = "DeleteFileController";
    private static final String DELETE_FILE_USER = "Delete FileUser";
    private static final String DELETE_FILE_USER_CONTROLLER = "DeleteFileUserController";
    private static final String SHARED_WITH_ME = "SharedWithMe";
    private static final String SHARED_WITH_ME_CONTROLLER = "SharedWithMeController";
    private static final String LOAD_SHARE_FILE = "GoToShareFile";
    private static final String LOAD_SHARE_FILE_CONTROLLER = "LoadShareFileController";
    private static final String ADD_PEOPLE_SHARE_FILE = "AddPeopleShareFile";
    private static final String ADD_PEOPLE_SHARE_FILE_CONTROLLER = "AddPeopleShareFileController";
    private static final String DELETE_FOLDER = "Delete Folder";
    private static final String DELETE_FOLDER_CONTROLLER = "DeleteFolderController";
    private static final String DELETE_FOLDER_USER = "Delete FolderUser";
    private static final String DELETE_FOLDER_USER_CONTROLLER = "DeleteFolderUserController";
    private static final String VIEWFEEDBACK = "viewFeedback";
    private static final String VIEWFEEDBACK_CONTROLLER = "ViewFeedbackController";
    private static final String FEEDBACKDETAIL = "feedbackDetail";
    private static final String FEEDBACKDETAIL_CONTROLLER = "FeedbackDetailController";
    private static final String FEEDBACKDELETE = "feedbackDelete";
    private static final String FEEDBACKDELETE_CONTROLLER = "FeedbackDeleteController";
    private static final String ERROREDIT = "errorEdit";
    private static final String ERROREDIT_CONTROLLER = "EditErrorController";
    private static final String REPLY = "reply";
    private static final String REPLY_PAGE = AppUtils.pagePrefix + "reply.jsp";
    private static final String EDITUSER = "Edit User";
    private static final String EDITUSER_PAGE = "EditUserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            Map<String, InputStream> formItemsMap = new HashMap<>();
            if (ServletFileUpload.isMultipartContent(request)) {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
                for (FileItem formItem : formItems) {
                    switch (formItem.getFieldName()) {
                        case "action":
                            action = formItem.getString();
                            break;
                        case "files":
                            formItemsMap.put(new File(formItem.getName()).getName(), formItem.getInputStream());
                            break;
                        case "currentFolderId":
                            request.setAttribute("CURRENT_FOLDER_ID", formItem.getString());
                            break;
                        case "currentProjectId":
                            request.setAttribute("CURRENT_PROJECT_ID", formItem.getString());
                            break;
                        default:
                            break;
                    }
                }
                if (!formItemsMap.isEmpty()) {
                    request.setAttribute("FILES_IS", formItemsMap);
                }
            }
            if (((String) request.getAttribute("ERROR")) != null) {
                request.setAttribute("ERROR", request.getAttribute("ERROR"));
            }

            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (EDITUSER.equals(action)) {
                url = EDITUSER_PAGE;
            } else if (PROJECTEDITUSERCHANGE.equals(action)) {
                url = PROJECTEDITUSERCHANGE_CONTROLLER;
            } else if (REPLY.equals(action)) {
                url = REPLY_PAGE;
            } else if (FEEDBACKDELETE.equals(action)) {
                url = FEEDBACKDELETE_CONTROLLER;
            } else if (ERROREDIT.equals(action)) {
                url = ERROREDIT_CONTROLLER;
            } else if (VIEWFEEDBACK.equals(action)) {
                url = VIEWFEEDBACK_CONTROLLER;
            } else if (FEEDBACKDETAIL.equals(action)) {
                url = FEEDBACKDETAIL_CONTROLLER;
            } else if (FEEDBACK.equals(action)) {
                url = FEEDBACK_PAGE;
            } else if (SENDFEEDBACK.equals(action)) {
                url = FEEDBACK_CONTROLLER;
            } else if (CREATEDPROJECT.equals(action)) {
                url = CREATEPROJECTD_CONTROLLER;
            } else if (ADDMEMBER.equals(action)) {
                url = ADDMEMBER_PAGE;
            } else if (DELETEFROMPROJECT.equals(action)) {
                url = DELETEFROMPROJECT_CONTROLLER;
            } else if (LOGIN1.equals(action)) {
                url = LOGIN_PAGE;
            } else if (CREATE_ACCOUNT.equals(action)) {
                url = CREATE_ACCOUNT_PAGE;
            } else if (HOME.equals(action)) {
                url = HOME_PAGE;
            } else if (PROFILE.equals(action)) {
                url = PROFILE_PAGE;
            } else if (FORGOT_PW.equals(action)) {
                url = FORGOT_PW_PAGE;
            } else if (HOME_INDEX.equals(action)) {
                url = HOME_PAGE_INDEX;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTROLLER;
            } else if (PROFILE_EDIT.equals(action)) {
                url = PROFILE_EDIT_CONTROLLER;
            } else if (ACCOUNTS.equals(action)) {
                url = ACCOUNTS_CONTROLLER;
            } else if (ACCOUNT_DETAILS.equals(action)) {
                url = ACCOUNT_DETAILS_CONTROLLER;
            } else if (ACCOUNT_DETAILS_EDIT.equals(action)) {
                url = ACCOUNT_DETAILS_EDIT_CONTROLLER;
            } else if (ACCOUNT_NEW.equals(action)) {
                url = ACCOUNT_NEW_CONTROLLER;
            } else if (FORGOT_PASSWORD.equals(action)) {
                url = FORGOT_PASSWORD_CONTROLLER;
            } else if (VALIDATE_OTP.equals(action)) {
                url = VALIDATE_OTP_CONTROLLER;
            } else if (RESET_PASSWORD.equals(action)) {
                url = RESET_PASSWORD_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (UPLOAD_FOLDER.equals(action)) {
                url = UPLOAD_FOLDER_CONTROLLER;
            } else if (UPLOAD_FILE.equals(action)) {
                url = UPLOAD_FILE_CONTROLLER;
            } else if (UPLOAD_FILE_TO_PROJECT.equals(action)) {
                url = UPLOAD_FILE_TO_PROJECT_CONTROLLER;
            } else if (USER_HOME.equals(action)) {
                url = USER_HOME_CONTROLLER;
            } else if (CREATEPROJECT.equals(action)) {
                url = CREATEPROJECTPAGE;
            } else if (VIEWPROJECT.equals(action)) {
                url = VIEWPROJECT_CONTROLLER;

            } else if (SEARCHUSER.equals(action)) {
                url = SEARCHUSER_CONTROLLER;
            } else if (ADDTOPROJECT.equals(action)) {
                url = ADDTOPROJECT_CONTROLLER;

            } else if (PROJECTDETAIL.equals(action)) {
                url = PROJECTDETAIL_CONTROLLER;
            } else if (PROJECTEDIT.equals(action)) {
                url = PROJECTEDIT_PAGE;
            } else if (PROJECTEDITCHANGE.equals(action)) {
                url = PROJECTEDITCHANGE_CONTROLLER;

            } else if (MY_PROJECTS.equals(action)) {
                url = MY_PROJECTS_CONTROLLER;
            } else if (GROUP_SHARE.equals(action)) {
                url = GROUP_SHARE_CONTROLLER;
            } else if (RECENT.equals(action)) {
                url = RECENT_CONTROLLER;
            } else if (RECENT_USER.equals(action)) {
                url = RECENT_USER_CONTROLLER;
            } else if (BIN.equals(action)) {
                url = BIN_CONTROLLER;
            } else if (BIN_USER.equals(action)) {
                url = BIN_USER_CONTROLLER;
            } else if (RENAME_FOLDER.equals(action)) {
                url = RENAME_FOLDER_CONTROLLER;
            } else if (RENAME_FILE_STARRED.equals(action)) {
                url = RENAME_FILE_STARRED_CONTROLLER;
            } else if (RENAME_FILE_STARRED_USER.equals(action)) {
                url = RENAME_FILE_STARRED_USER_CONTROLLER;
            } else if (RENAME_FOLDER_PROJECT.equals(action)) {
                url = RENAME_FOLDER_PROJECT_CONTROLLER;
            } else if (RENAME_FOLDER_STARRED.equals(action)) {
                url = RENAME_FOLDER_STARRED_CONTROLLER;
            } else if (RENAME_FOLDER_STARRED_USER.equals(action)) {
                url = RENAME_FOLDER_STARRED_USER_CONTROLLER;
            } else if (RENAME_FILE_RECENT.equals(action)) {
                url = RENAME_FILE_RECENT_CONTROLLER;
            } else if (RENAME_FILE_RECENT_USER.equals(action)) {
                url = RENAME_FILE_RECENT_USER_CONTROLLER;
            } else if (RENAME_FILE_PROJECT.equals(action)) {
                url = RENAME_FILE_PROJECT_CONTROLLER;
            } else if (COPY_FILE_RECENT.equals(action)) {
                url = COPY_FILE_RECENT_CONTROLLER;
            } else if (COPY_FILE.equals(action)) {
                url = COPY_FILE_CONTROLLER;
            } else if (COPY_FILE_PROJECT.equals(action)) {
                url = COPY_FILE_PROJECT_CONTROLLER;
            } else if (COPY_FILE_STARRED.equals(action)) {
                url = COPY_FILE_STARRED_CONTROLLER;
            } else if (COPY_FILE_STARRED_USER.equals(action)) {
                url = COPY_FILE_STARRED_USER_CONTROLLER;
            } else if (CREATE_FOLDER.equals(action)) {
                url = CREATE_FOLDER_CONTROLLER;
            } else if (CREATE_FOLDER_USER.equals(action)) {
                url = CREATE_FOLDER_USER_CONTROLLER;
            } else if (REMOVE_FOLDER.equals(action)) {
                url = REMOVE_FOLDER_CONTROLLER;
            } else if (REMOVE_FOLDER_PROJECT.equals(action)) {
                url = REMOVE_FOLDER_PROJECT_CONTROLLER;
            } else if (REMOVE_FOLDER_STARRED.equals(action)) {
                url = REMOVE_FOLDER_STARRED_CONTROLLER;
            } else if (REMOVE_FOLDER_STARRED_USER.equals(action)) {
                url = REMOVE_FOLDER_STARRED_USER_CONTROLLER;
            } else if (REMOVE_FILE_STARRED.equals(action)) {
                url = REMOVE_FILE_STARRED_CONTROLLER;
            } else if (REMOVE_FILE_STARRED_USER.equals(action)) {
                url = REMOVE_FILE_STARRED_USER_CONTROLLER;
            } else if (RESTORE_FOLDER.equals(action)) {
                url = RESTORE_FOLDER_CONTROLLER;
            } else if (RESTORE_FOLDER_USER.equals(action)) {
                url = RESTORE_FOLDER_USER_CONTROLLER;
            } else if (RESTORE_FILE.equals(action)) {
                url = RESTORE_FILE_CONTROLLER;
            } else if (RESTORE_FILE_USER.equals(action)) {
                url = RESTORE_FILE_USER_CONTROLLER;
            } else if (DOWNLOAD_FILE.equals(action)) {
                url = DOWNLOAD_FILE_CONTROLLER;
            } else if (STAR_FILE.equals(action)) {
                url = STAR_FILE_CONTROLLER;
            } else if (STAR_FILE_PROJECT.equals(action)) {
                url = STAR_FILE_PROJECT_CONTROLLER;
            } else if (STAR_FILE_RECENT.equals(action)) {
                url = STAR_FILE_RECENT_CONTROLLER;
            } else if (STAR_FILE_RECENT_USER.equals(action)) {
                url = STAR_FILE_RECENT_USER_CONTROLLER;
            } else if (REPORT_FILE.equals(action)) {
                url = REPORT_FILE_PAGE_LOAD_CONTROLLER;
            } else if (SUBMIT_REPORT.equals(action)) {
                url = SUBMIT_REPORT_CONTROLLER;
            } else if (STAR_FOLDER.equals(action)) {
                url = STAR_FOLDER_CONTROLLER;
            } else if (STAR_FOLDER_PROJECT.equals(action)) {
                url = STAR_FOLDER_PROJECT_CONTROLLER;
            } else if (REMOVE_FILE_PROJECT.equals(action)) {
                url = REMOVE_FILE_PROJECT_CONTROLLER;
            } else if (REMOVE_FILE.equals(action)) {
                url = REMOVE_FILE_CONTROLLER;
            } else if (REMOVE_FILE_RECENT.equals(action)) {
                url = REMOVE_FILE_RECENT_CONTROLLER;
            } else if (REMOVE_FILE_RECENT_USER.equals(action)) {
                url = REMOVE_FILE_RECENT_USER_CONTROLLER;
            } else if (STARRED.equals(action)) {
                url = STARRED_CONTROLLER;
            } else if (STARRED_USER.equals(action)) {
                url = STARRED_USER_CONTROLLER;
            } else if (REMOVE_STAR_FOLDER.equals(action)) {
                url = REMOVE_STAR_FOLDER_CONTROLLER;
            } else if (REMOVE_STAR_FOLDER_USER.equals(action)) {
                url = REMOVE_STAR_FOLDER_USER_CONTROLLER;
            } else if (REMOVE_STAR_FILE.equals(action)) {
                url = REMOVE_STAR_FILE_CONTROLLER;
            } else if (REMOVE_STAR_FILE_USER.equals(action)) {
                url = REMOVE_STAR_FILE_USER_CONTROLLER;
            } else if (DELETE_FILE.equals(action)) {
                url = DELETE_FILE_CONTROLLER;
            } else if (DELETE_FILE_USER.equals(action)) {
                url = DELETE_FILE_USER_CONTROLLER;
            } else if (SHARED_WITH_ME.equals(action)) {
                url = SHARED_WITH_ME_CONTROLLER;
            } else if (LOAD_SHARE_FILE.equals(action)) {
                url = LOAD_SHARE_FILE_CONTROLLER;
            } else if (ADD_PEOPLE_SHARE_FILE.equals(action)) {
                url = ADD_PEOPLE_SHARE_FILE_CONTROLLER;
            } else if (DELETE_FOLDER.equals(action)) {
                url = DELETE_FOLDER_CONTROLLER;
            } else if (DELETE_FOLDER_USER.equals(action)) {
                url = DELETE_FOLDER_USER_CONTROLLER;
            } else {
                request.setAttribute("ERROR_MESSAGE", "Function is not available!");
                LOGGER.info("Function is not available");
                LOGGER.info("Action: \"" + action + "\"");
            }
        } catch (IOException e) {
            LOGGER.error(e);
        } catch (FileUploadException ex) {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
