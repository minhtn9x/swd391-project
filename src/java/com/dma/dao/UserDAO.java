/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.dao;

import com.dma.dto.UserDTO;
import com.dma.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private static final String LOGIN = "SELECT userID, fullName, password, roleID"
            + ", address, birthday, phoneNumber, email, isDeleted"
            + " FROM tblUsers "
            + "WHERE userID = ? "
            + "AND password = ? "
            + "AND isDeleted = 0";

    private static final String LOGIN_GOOGLE = "SELECT userID, fullName, password, roleID"
            + ", address, birthday, phoneNumber, email, isDeleted"
            + " FROM tblUsers "
            + "WHERE email = ? "
            + "AND isDeleted = 0";

    private static final String IS_DELETED = "SELECT isDeleted FROM tblUsers "
            + "WHERE userID = ? "
            + "OR email = ?";

    private static final String REGISTER = "INSERT INTO tblUsers(userID,fullName,roleID,password,address,"
            + "birthday,phoneNumber,email,isDeleted) "
            + "VALUES(?,?,?,?,?,?,?,?,?)";

    private static final String CHECK_DUPLICATE = "SELECT userID FROM tblUsers "
            + "WHERE userID=?";

    private static final String GET_USER_INFO = "SELECT fullName, password, roleID, address, birthday, "
            + "phoneNumber, email, isDeleted "
            + "FROM tblUsers "
            + "WHERE userID=?";

    private static final String GET_ALL = "SELECT userID, fullName, password, roleID, address, birthday, "
            + "phoneNumber, email, isDeleted "
            + "FROM tblUsers";

    private static final String GET_ALL_EXCEPT_ADMIN = "SELECT userID, fullName, password, roleID, address, birthday, "
            + "phoneNumber, email, isDeleted "
            + "FROM tblUsers "
            + "WHERE roleID <> 'AD' "
            + "AND userID NOT IN"
            + "(SELECT userId\n"
            + "FROM [Permissions]\n"
            + "WHERE [role]='Owner' "
            + "AND fileId=?)";

    private static final String GET_ALL_EXCEPT_LOGGED_USER = "SELECT userID, fullName, password, roleID, address, birthday, "
            + "phoneNumber, email, isDeleted "
            + "FROM tblUsers "
            + "WHERE userID <> ?";

    private static final String GET_BYNAME = "SELECT userID, fullName, password, roleID, address, birthday,\n"
            + "            phoneNumber, email, isDeleted \n"
            + "            FROM tblUsers\n"
            + " Where userID LIKE ?"
            + " AND roleID <> 'AD'"
            +"AND userID<> ?";
           

    private static final String UPDATE = "UPDATE tblUsers SET fullName=?,roleID=?,password=?,address=?"
            + ",birthday=?,phoneNumber=?,email=? "
            + "WHERE userID=?";

    private static final String DELETE = "UPDATE tblUsers SET isDeleted=1 "
            + "WHERE userID=?";

    private static final String RESET = "UPDATE tblUsers SET password=? "
            + "WHERE email=?";

    private static final String CHECK_EMAIL = "SELECT email FROM tblUsers "
            + "WHERE email=?";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LOGIN);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    user = new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNumber, email, isDeleted);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public UserDTO checkLoginGoogle(String emailGoogle) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(LOGIN_GOOGLE);
                stm.setString(1, emailGoogle);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    user = new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNumber, email, isDeleted);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean isDeleted(String emailOrID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(IS_DELETED);
                stm.setString(1, emailOrID);
                stm.setString(2, emailOrID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    boolean isActive = rs.getBoolean("isDeleted");
                    if (isActive) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public boolean checkUserExisted(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_DUPLICATE);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean checkEmailExisted(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(CHECK_EMAIL);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean createUser(UserDTO user) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REGISTER);
                stm.setString(1, user.getUserID());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getPassword());
                stm.setString(5, user.getAddress());
                stm.setDate(6, user.getBirthday());
                stm.setString(7, user.getPhoneNumber());
                stm.setString(8, user.getEmail());
                stm.setBoolean(9, user.getIsDeleted());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public UserDTO getUserInfo(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_USER_INFO);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    user = new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNumber, email, isDeleted);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean updateUser(UserDTO user) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(UPDATE);
                stm.setString(1, user.getFullName());
                stm.setString(2, user.getRoleID());
                stm.setString(3, user.getPassword());
                stm.setString(4, user.getAddress());
                stm.setDate(5, user.getBirthday());
                stm.setString(6, user.getPhoneNumber());
                stm.setString(7, user.getEmail());
                stm.setString(8, user.getUserID());

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean deleteUser(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE);
                stm.setString(1, userID);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public List<UserDTO> getUsers() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    users.add(new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNumber, email, isDeleted));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }

    public List<UserDTO> getUsersExceptAdmin(String fileID) throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_EXCEPT_ADMIN);
                stm.setString(1, fileID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    users.add(new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNumber, email, isDeleted));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }

    public List<UserDTO> getUsersExceptOne(String userIDAdmin) throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_ALL_EXCEPT_LOGGED_USER);
                stm.setString(1, userIDAdmin);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    users.add(new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNumber, email, isDeleted));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }

    public boolean resetPassword(String password, String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(RESET);
                stm.setString(1, password);
                stm.setString(2, email);

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public List<UserDTO> getUsersByName(String UserID,String Loginname) throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_BYNAME);
                stm.setString(1, "%" + UserID + "%");
                stm.setString(2, Loginname);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    Date birthday = rs.getDate("birthday");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    boolean isDeleted = rs.getBoolean("isDeleted");
                    users.add(new UserDTO(userID, fullName, roleID, password, address, birthday, phoneNumber, email, isDeleted));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return users;
    }
}
