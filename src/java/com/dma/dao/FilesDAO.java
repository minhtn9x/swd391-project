package com.dma.dao;

import com.dma.dto.PermissionDTO;
import com.dma.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesDAO {
    private static final String CREATE_FOLDER_TO_PROJECT = "INSERT INTO FolderInFolder(projectId, parentFolderId, childFolderId, childFolderName, parentsFolderName, parentsFolderId) "
                                                         + "VALUES(?,?,?,?,?,?) "
                                                         + "INSERT INTO Folders(folderId) VALUES (?)";
    private static final String CREATE_FOLDER_TO_PERSONAL = "INSERT INTO FolderInFolderUser(userId, parentFolderId, childFolderId, childFolderName, parentsFolderName, parentsFolderId) "
                                                          + "VALUES(?,?,?,?,?,?) "
                                                          + "INSERT INTO Folders(folderId) VALUES (?)";
    private static final String GET_PARENT_FOLDER_NAMES_PROJECT = "SELECT parentsFolderName "
                                                        + "FROM FolderInFolder "
                                                        + "WHERE childFolderId=?";
    private static final String GET_PARENT_FOLDER_IDS_PROJECT = "SELECT parentsFolderId "
                                                        + "FROM FolderInFolder "
                                                        + "WHERE childFolderId=?";
    private static final String GET_PARENT_FOLDER_NAMES = "SELECT parentsFolderName "
                                                        + "FROM FolderInFolderUser "
                                                        + "WHERE childFolderId=?";
    private static final String GET_PARENT_FOLDER_IDS = "SELECT parentsFolderId "
                                                        + "FROM FolderInFolderUser "
                                                        + "WHERE childFolderId=?";
    private static final String ADD_FILES_TO_PROJECT = "INSERT INTO FileInFolder(projectId, parentFolderId, childFileId, childFileName, uploadUserID) "
                                                     + "VALUES(?,?,?,?,?) "
                                                     + "INSERT INTO Files(fileId) VALUES (?)";
    private static final String ADD_FILES_TO_PERSONAL = "INSERT INTO FileInFolderUser(userId, parentFolderId, childFileId, childFileName) "
                                                      + "VALUES(?,?,?,?) "
                                                      + "INSERT INTO Files(fileId) VALUES (?)";
    private static final String ADD_FILES_TO_STARRED = "INSERT INTO FileInFolder(projectId, parentFolderId, childFileId, childFileName) "
                                                     + "VALUES(?,?,?,?) "
                                                     + "INSERT INTO Files(fileId,starred) VALUES (?,?)";
    private static final String ADD_FILES_TO_STARRED_USER = "INSERT INTO FileInFolderUser(userId, parentFolderId, childFileId, childFileName) "
                                                          + "VALUES(?,?,?,?) "
                                                          + "INSERT INTO Files(fileId,starred) VALUES (?,?)";
    private static final String GET_ROOT_FOLDERS_BY_PROJECT_ID = "SELECT childFolderId, childFolderName "
                                                               + "FROM FolderInFolder "
                                                               + "WHERE projectId=? AND parentFolderId=? "
                                                                   + "AND childFolderId IN ("
                                                                       + "SELECT folderId "
                                                                       + "FROM Folders "
                                                                       + "WHERE trashed=? OR trashed IS NULL"
                                                                   + ")";
    private static final String GET_ROOT_FOLDERS_BY_USER_ID = "SELECT childFolderId, childFolderName "
                                                               + "FROM FolderInFolderUser "
                                                               + "WHERE userId=? AND parentFolderId=? "
                                                                   + "AND childFolderId IN ("
                                                                       + "SELECT folderId "
                                                                       + "FROM Folders "
                                                                       + "WHERE trashed=? OR trashed IS NULL"
                                                                   + ")";
    private static final String GET_ROOT_FOLDERS_TRASHED_BY_PROJECT_ID = "SELECT childFolderId, childFolderName "
                                                                       + "FROM FolderInFolder "
                                                                       + "WHERE projectId=? "
                                                                           + " AND parentFolderId=? "
                                                                           + " AND childFolderId IN ("
                                                                               + "SELECT folderId "
                                                                               + "FROM Folders "
                                                                               + "WHERE trashed=?"
                                                                           + ")";
    private static final String GET_ROOT_FOLDERS_TRASHED_BY_USER_ID = "SELECT childFolderId, childFolderName "
                                                                       + "FROM FolderInFolderUser "
                                                                       + "WHERE userId=? "
                                                                           + " AND parentFolderId=? "
                                                                           + " AND childFolderId IN ("
                                                                               + "SELECT folderId "
                                                                               + "FROM Folders "
                                                                               + "WHERE trashed=?"
                                                                           + ")";
    private static final String GET_ROOT_FOLDERS_STARRED = "SELECT childFolderId, childFolderName "
                                                         + "FROM FolderInFolder "
                                                         + "WHERE projectId=? "
                                                            + "AND parentFolderId=? "
                                                            + "AND childFolderId IN ("
                                                                + "SELECT folderId "
                                                                + "FROM Folders "
                                                                + "WHERE starred=?"
                                                            + ")";
    
    private static final String GET_ROOT_FOLDERS_STARRED_USER = "SELECT childFolderId, childFolderName "
                                                         + "FROM FolderInFolderUser "
                                                         + "WHERE userId=? "
                                                            + "AND parentFolderId=? "
                                                            + "AND childFolderId IN ("
                                                                + "SELECT folderId "
                                                                + "FROM Folders "
                                                                + "WHERE starred=?"
                                                            + ")";
    
    private static final String GET_OWNER = "SELECT uploadUserID "
                                          + "FROM FileInFolder "
                                          + "WHERE childFileId=?";
    private static final String GET_ROOT_FILES_BY_PROJECT_ID = "SELECT childFileId "
                                                             + "FROM FileInFolder "
                                                             + "WHERE projectId=? AND parentFolderId=? "
                                                                + "AND childFileId IN ("
                                                                    + "SELECT fileId "
                                                                    + "FROM Files "
                                                                    + "WHERE trashed=? OR trashed IS NULL"
                                                                + ")";
    private static final String GET_ROOT_FILES_BY_USER_ID = "SELECT childFileId "
                                                             + "FROM FileInFolderUser "
                                                             + "WHERE userId=? AND parentFolderId=? "
                                                                + "AND childFileId IN ("
                                                                    + "SELECT fileId "
                                                                    + "FROM Files "
                                                                    + "WHERE trashed=? OR trashed IS NULL"
                                                                + ")";
    private static final String GET_FILES_BY_PROJECT_ID = "SELECT childFileId "
                                                        + "FROM FileInFolder "
                                                        + "WHERE projectId=? "
                                                        + "AND childFileId IN ("
                                                            + "SELECT fileId "
                                                            + "FROM Files "
                                                            + "WHERE trashed=? OR trashed IS NULL"
                                                        + ")";
    private static final String GET_FILES_BY_USER_ID = "SELECT childFileId "
                                                        + "FROM FileInFolderUser "
                                                        + "WHERE userId=? "
                                                        + "AND childFileId IN ("
                                                            + "SELECT fileId "
                                                            + "FROM Files "
                                                            + "WHERE trashed=? OR trashed IS NULL"
                                                        + ")";
    private static final String GET_FILES_TRASHED_BY_PROJECT_ID = "SELECT childFileId "
                                                                + "FROM FileInFolder "
                                                                + "WHERE projectId=? "
                                                                    + "AND childFileId IN ("
                                                                        + "SELECT fileId "
                                                                        + "FROM Files "
                                                                        + "WHERE trashed=?"
                                                                    + ")";
    private static final String GET_FILES_TRASHED_BY_USER_ID = "SELECT childFileId "
                                                                + "FROM FileInFolderUser "
                                                                + "WHERE userId=? "
                                                                    + "AND childFileId IN ("
                                                                        + "SELECT fileId "
                                                                        + "FROM Files "
                                                                        + "WHERE trashed=?"
                                                                    + ")";
    private static final String GET_FILES_STARRED = "SELECT childFileId "
                                                  + "FROM FileInFolder "
                                                  + "WHERE projectId=? "
                                                  + "AND childFileId IN ("
                                                      + "SELECT fileId "
                                                      + "FROM Files "
                                                      + "WHERE starred=?"
                                                  + ")";
    private static final String GET_FILES_STARRED_USER = "SELECT childFileId "
                                                  + "FROM FileInFolderUser "
                                                  + "WHERE userId=? "
                                                  + "AND childFileId IN ("
                                                      + "SELECT fileId "
                                                      + "FROM Files "
                                                      + "WHERE starred=?"
                                                  + ")";
    private static final String REMOVE_FILE= "UPDATE Files SET trashed=? "
                                           + "WHERE fileId=?";
    private static final String STAR_FILE= "UPDATE Files SET starred=? "
                                         + "WHERE fileId=?";
    private static final String REMOVE_FOLDER= "UPDATE Folders SET trashed=? "
                                           + "WHERE folderId=?";
    private static final String STAR_FOLDER= "UPDATE Folders SET starred=? "
                                           + "WHERE folderId=?";
    private static final String DELETE_FOLDER= "DELETE FROM FolderInFolder "
                                             + "WHERE childFolderId=? "
                                             + "DELETE FROM Folders "
                                             + "WHERE folderId=?";
    private static final String DELETE_FILE= "DELETE FROM FileInFolder "
                                           + "WHERE childFileId=? "
                                           + "DELETE FROM Files "
                                           + "WHERE fileId=?";
    private static final String GET_PEOPLE_WITH_ACCESS = "SELECT b.userId, fullName, role FROM "
                                                       + "(SELECT userId,fullName " +
                                                         "FROM [tblUsers]) a JOIN "
                                                       + "(SELECT userId, role "
                                                       + "FROM [Permissions] "
                                                       + "WHERE fileId=?) b ON a.userId = b.userId";
    private static final String GET_FILE_OWNER = "SELECT a.fileId, [owner] FROM\n" +
                                                            "(SELECT fileId\n" +
                                                            "FROM [Permissions]\n" +
                                                            "WHERE userId=?) a JOIN\n" +
                                                            "(SELECT fileId, userId AS [owner]\n" +
                                                            "FROM [Permissions]\n" +
                                                            "WHERE [role]=?) b ON a.fileId = b.fileId";
    private static final String ADD_PEOPLE_WITH_ACCESS = "INSERT INTO [Permissions](fileId,userId,role) "
                                                       + "VALUES (?,?,?)";
    private static final String GET_FOLDER_OWNER = "SELECT a.folderId, [owner] FROM\n" +
                                                            "(SELECT folderId\n" +
                                                            "FROM [PermissionsFolder]\n" +
                                                            "WHERE userId=?) a JOIN\n" +
                                                            "(SELECT folderId, userId AS [owner]\n" +
                                                            "FROM [PermissionsFolder]\n" +
                                                            "WHERE [role]=?) b ON a.folderId = b.folderId";
    private static final String ADD_PEOPLE_WITH_ACCESS_FOLDER = "INSERT INTO [PermissionsFolder](folderId,userId,role) "
                                                              + "VALUES (?,?,?)";

    public List<String> getRootFoldersByProjectID(String projectId) throws SQLException, ClassNotFoundException {
        List<String> folders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FOLDERS_BY_PROJECT_ID);
                stm.setString(1, projectId);
                stm.setString(2, "null");
                stm.setBoolean(3, false);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childFolderId = rs.getString("childFolderId");
                    folders.add(childFolderId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return folders;
    }
    
    public List<String> getRootFoldersByUserID(String userId) throws SQLException, ClassNotFoundException {
        List<String> folders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FOLDERS_BY_USER_ID);
                stm.setString(1, userId);
                stm.setString(2, "null");
                stm.setBoolean(3, false);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childFolderId = rs.getString("childFolderId");
                    folders.add(childFolderId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return folders;
    }
    
    public List<String> getRootFoldersTrashed(String projectId) throws SQLException, ClassNotFoundException {
        List<String> folders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FOLDERS_TRASHED_BY_PROJECT_ID);
                stm.setString(1, projectId);
                stm.setString(2, "null");
                stm.setBoolean(3, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childFolderId = rs.getString("childFolderId");
                    folders.add(childFolderId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return folders;
    }
    
    public List<String> getRootFoldersTrashedByUserID(String userId) throws SQLException, ClassNotFoundException {
        List<String> folders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FOLDERS_TRASHED_BY_USER_ID);
                stm.setString(1, userId);
                stm.setString(2, "null");
                stm.setBoolean(3, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childFolderId = rs.getString("childFolderId");
                    folders.add(childFolderId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return folders;
    }
    
    public List<String> getRootFoldersStarred(String projectId) throws SQLException, ClassNotFoundException {
        List<String> folders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FOLDERS_STARRED);
                stm.setString(1, projectId);
                stm.setString(2, "null");
                stm.setBoolean(3, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childFolderId = rs.getString("childFolderId");
                    folders.add(childFolderId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return folders;
    }
    
    public List<String> getRootFoldersStarredByUserID(String userId) throws SQLException, ClassNotFoundException {
        List<String> folders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FOLDERS_STARRED_USER);
                stm.setString(1, userId);
                stm.setString(2, "null");
                stm.setBoolean(3, true);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String childFolderId = rs.getString("childFolderId");
                    folders.add(childFolderId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return folders;
    }
    
    public String getOwner(String fileID) throws SQLException, ClassNotFoundException {
        String userId = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_OWNER);
                stm.setString(1, fileID);
                rs = stm.executeQuery();
                if(rs.next()) {
                    userId= rs.getString("uploadUserID");
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return userId;
    }
    public List<String> getRootFilesByProjectID(String projectId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FILES_BY_PROJECT_ID);
                stm.setString(1, projectId);
                stm.setString(2, "null");
                stm.setBoolean(3, false);
                rs = stm.executeQuery();
                while (rs.next()) {
                    // fileId, childFolderName, webViewLink, modifiedTime, size 
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    
    public List<String> getRootFilesByUserID(String userId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ROOT_FILES_BY_USER_ID);
                stm.setString(1, userId);
                stm.setString(2, "null");
                stm.setBoolean(3, false);
                rs = stm.executeQuery();
                while (rs.next()) {
                    // fileId, childFolderName, webViewLink, modifiedTime, size 
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    
    public List<String> getFilesByProjectID(String projectId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FILES_BY_PROJECT_ID);
                stm.setString(1, projectId);
                stm.setBoolean(2, false);
                rs = stm.executeQuery();
                while (rs.next()) {
                    // fileId, childFolderName, webViewLink, modifiedTime, size 
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    
    public List<String> getFilesByUserID(String userId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FILES_BY_USER_ID);
                stm.setString(1, userId);
                stm.setBoolean(2, false);
                rs = stm.executeQuery();
                while (rs.next()) {
                    // fileId, childFolderName, webViewLink, modifiedTime, size 
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    
    public List<String> getFilesTrashed(String projectId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FILES_TRASHED_BY_PROJECT_ID);
                stm.setString(1, projectId);
                stm.setBoolean(2, Boolean.TRUE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    // fileId, childFolderName, webViewLink, modifiedTime, size 
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    public List<String> getFilesTrashedByUserID(String userId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FILES_TRASHED_BY_USER_ID);
                stm.setString(1, userId);
                stm.setBoolean(2, Boolean.TRUE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    
    public List<String> getFilesStarred(String projectId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FILES_STARRED);
                stm.setString(1, projectId);
                stm.setBoolean(2, Boolean.TRUE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    // fileId, childFolderName, webViewLink, modifiedTime, size 
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    
    public List<String> getFilesStarredByUserID(String userId) throws SQLException, ClassNotFoundException {
        List<String> files = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FILES_STARRED_USER);
                stm.setString(1, userId);
                stm.setBoolean(2, Boolean.TRUE);
                rs = stm.executeQuery();
                while (rs.next()) {
                    // fileId, childFolderName, webViewLink, modifiedTime, size 
                    String fileId= rs.getString("childFileId");
                    files.add(fileId);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return files;
    }
    
    public boolean addFileToProject(String projectId, String parentFolderId, String childFileId, String childFileName, String userID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_FILES_TO_PROJECT);
                stm.setString(1, projectId);
                stm.setString(2, parentFolderId);
                stm.setString(3, childFileId);
                stm.setString(4, childFileName);
                stm.setString(5, userID);
                stm.setString(6, childFileId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean addFileToPersonal(String userId, String parentFolderId, String childFileId, String childFileName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_FILES_TO_PERSONAL);
                stm.setString(1, userId);
                stm.setString(2, parentFolderId);
                stm.setString(3, childFileId);
                stm.setString(4, childFileName);
                stm.setString(5, childFileId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean addFileToStarred(String projectId, String parentFolderId, String childFileId, String childFileName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_FILES_TO_STARRED);
                stm.setString(1, projectId);
                stm.setString(2, parentFolderId);
                stm.setString(3, childFileId);
                stm.setString(4, childFileName);
                stm.setString(5, childFileId);
                stm.setBoolean(6, true);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean addFileToStarredUser(String userId, String parentFolderId, String childFileId, String childFileName) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_FILES_TO_STARRED_USER);
                stm.setString(1, userId);
                stm.setString(2, parentFolderId);
                stm.setString(3, childFileId);
                stm.setString(4, childFileName);
                stm.setString(5, childFileId);
                stm.setBoolean(6, true);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }

    public boolean createFolderToProject(String projectId, String parentFolderId, String childFolderId, String childFolderName, String parentsFolderName, String parentsFolderId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_FOLDER_TO_PROJECT);
                stm.setString(1, projectId);
                stm.setString(2, parentFolderId);
                stm.setString(3, childFolderId);
                stm.setString(4, childFolderName);
                stm.setString(5, parentsFolderName);
                stm.setString(6, parentsFolderId);
                stm.setString(7, childFolderId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean createFolderToPersonal(String userId, String parentFolderId, String childFolderId, String childFolderName, String parentsFolderName, String parentsFolderId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CREATE_FOLDER_TO_PERSONAL);
                stm.setString(1, userId);
                stm.setString(2, parentFolderId);
                stm.setString(3, childFolderId);
                stm.setString(4, childFolderName);
                stm.setString(5, parentsFolderName);
                stm.setString(6, parentsFolderId);
                stm.setString(7, childFolderId);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean removeFile(String fileID, int trashed) throws SQLException, ClassNotFoundException {
        // trashed = 0, 1
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REMOVE_FILE);
                stm.setInt(1, trashed);
                stm.setString(2, fileID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean starFile(String fileID, int starred) throws SQLException, ClassNotFoundException {
        // starred = 0, 1
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(STAR_FILE);
                stm.setInt(1, starred);
                stm.setString(2, fileID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean starFolder(String folderID, int starred) throws SQLException, ClassNotFoundException {
        // starred = 0, 1
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(STAR_FOLDER);
                stm.setInt(1, starred);
                stm.setString(2, folderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    public boolean removeFolder(String folderID, int trashed) throws SQLException, ClassNotFoundException {
        // trashed = 0, 1
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REMOVE_FOLDER);
                stm.setInt(1, trashed);
                stm.setString(2, folderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean deleteFolder(String folderID) throws SQLException, ClassNotFoundException {
        // trashed = 0, 1
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE_FOLDER);
                stm.setString(1, folderID);
                stm.setString(2, folderID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public boolean deleteFile(String fileID) throws SQLException, ClassNotFoundException {
        // trashed = 0, 1
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE_FILE);
                stm.setString(1, fileID);
                stm.setString(2, fileID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }

    public String getParentFolderNames(String parentFolderId) throws SQLException, ClassNotFoundException {
        String parentFolderNames = "";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PARENT_FOLDER_NAMES);
                stm.setString(1, parentFolderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    parentFolderNames = rs.getString("parentsFolderName");
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return parentFolderNames;
    }

    public String getParentFolderIds(String parentFolderId) throws ClassNotFoundException, SQLException {
        String parentFolderIds = "";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PARENT_FOLDER_IDS);
                stm.setString(1, parentFolderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    parentFolderIds = rs.getString("parentsFolderId");
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return parentFolderIds;
    }
    
    public String getParentFolderNamesInProject(String parentFolderId) throws SQLException, ClassNotFoundException {
        String parentFolderNames = "";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PARENT_FOLDER_NAMES_PROJECT);
                stm.setString(1, parentFolderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    parentFolderNames = rs.getString("parentsFolderName");
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return parentFolderNames;
    }

    public String getParentFolderIdsInProject(String parentFolderId) throws ClassNotFoundException, SQLException {
        String parentFolderIds = "";
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PARENT_FOLDER_IDS_PROJECT);
                stm.setString(1, parentFolderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    parentFolderIds = rs.getString("parentsFolderId");
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return parentFolderIds;
    }
    
    public List<PermissionDTO> getPeopleWithAccess(String fileId) throws SQLException, ClassNotFoundException {
        List<PermissionDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PEOPLE_WITH_ACCESS);
                stm.setString(1, fileId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userId= rs.getString("userID");
                    String fullName= rs.getString("fullName");
                    String role= rs.getString("role");
                    users.add(new PermissionDTO(userId, fullName, role));
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return users;
    }
    
    public Map<String, String> getFileOwner(String userId) throws SQLException, ClassNotFoundException {
        Map<String, String> ownersMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FILE_OWNER);
                stm.setString(1, userId);
                stm.setString(2, "Owner");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String fileId= rs.getString("fileId");
                    String owner= rs.getString("owner");
                    ownersMap.put(fileId, owner);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return ownersMap;
    }
    
    public boolean addPeopleWithAccess(String fileId, String userId, String role) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_PEOPLE_WITH_ACCESS);
                stm.setString(1, fileId);
                stm.setString(2, userId);
                stm.setString(3, role);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
    
    public Map<String, String> getFolderOwner(String userId) throws SQLException, ClassNotFoundException {
        Map<String, String> ownersMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_FOLDER_OWNER);
                stm.setString(1, userId);
                stm.setString(2, "Owner");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String fileId= rs.getString("folderId");
                    String owner= rs.getString("owner");
                    ownersMap.put(fileId, owner);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        
        return ownersMap;
    }
    
    public boolean addPeopleWithAccessFolder(String folderId, String userId, String role) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(ADD_PEOPLE_WITH_ACCESS_FOLDER);
                stm.setString(1, folderId);
                stm.setString(2, userId);
                stm.setString(3, role);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }
        return false;
    }
}
