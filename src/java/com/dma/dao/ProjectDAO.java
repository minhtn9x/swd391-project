/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dma.dao;

import com.dma.dto.MembershipDTO;
import com.dma.dto.ProjectDTO;
import com.dma.dto.UserDTO;
import com.dma.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author giahu
 */
public class ProjectDAO {

    private static final String GET_PROJECT_BY_MEMBER_ID = "SELECT project_id,manager_id,projectName,projectDesc,createdDate FROM tblProjects "
            + "WHERE project_id IN "
            + "(SELECT project_id FROM tblMembership WHERE user_id=?)";

    public static boolean insertProject(String projectName, String projectDesc, String manager_id, Date startProjectDate, Date endProjectDate, int isDeleted) {

        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert dbo.tblProjects(projectName,projectDesc,manager_id,createdDate,startProjectDate,endProjectDate,isDeleted)\n"
                        + "values(?,?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, projectName);
                pst.setString(2, projectDesc);
                pst.setString(3, manager_id);
                pst.setTimestamp(4, new java.sql.Timestamp(new java.util.Date().getTime()));
                pst.setDate(5, startProjectDate);
                pst.setDate(6, endProjectDate);
                pst.setInt(7, isDeleted);
                result = pst.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    public static ArrayList<ProjectDTO> getProjectList() {
        ArrayList<ProjectDTO> list = new ArrayList<ProjectDTO>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select project_id,projectName,projectDesc,createdDate from tblProjects ";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    ProjectDTO project = new ProjectDTO();
                    project.setProjectID(rs.getInt("project_id"));
                    project.setProjectName(rs.getString("projectName"));
                    project.setProjectDesc(rs.getString("projectDesc"));
                    project.setCreatedDate(rs.getDate("createdDate"));
                    list.add(project);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return list;
    }

    public ProjectDTO getProjectByID(String id) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "select *from tblProjects \n"
                        + "where project_id=?";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ProjectDTO project = new ProjectDTO();
                    project.setProjectID(rs.getInt("project_id"));
                    project.setManagerID(rs.getString("manager_id"));
                    project.setProjectName(rs.getString("projectName"));
                    project.setProjectDesc(rs.getString("projectDesc"));
                    project.setCreatedDate(rs.getDate("createdDate"));
                    project.setStartDate(rs.getDate("startProjectDate"));
                    project.setEndDate(rs.getDate("endProjectDate"));
                    return project;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public ProjectDTO getLatestProject() {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "SELECT TOP 1 project_id,projectName,projectDesc FROM tblProjects  \n"
                        + "ORDER BY project_id DESC; ";
                PreparedStatement ps = cn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ProjectDTO project = new ProjectDTO();
                    project.setProjectID(rs.getInt("project_id"));
                    project.setProjectName(rs.getString("projectName"));
                    project.setProjectDesc(rs.getString("projectDesc"));
                    return project;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public boolean updateProject(String id, String name, String desc, Date startDate, Date endDate) throws SQLException, ClassNotFoundException {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblProjects\n"
                        + "set projectName=?,\n"
                        + "projectDesc=?,\n"
                        + "startProjectDate=?,\n"
                        + "endProjectDate=?\n"
                        + "where project_id=?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, desc);
                ps.setDate(3, startDate);
                ps.setDate(4, endDate);
                ps.setString(5, id);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return false;
    }

    public ProjectDTO getProjectDetailByID(String id) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "select *from tblMembership \n"
                        + "where project_id=?";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ProjectDTO project = new ProjectDTO();
                    project.setProjectID(rs.getInt("project_id"));
                    project.setProjectName(rs.getString("UserID"));
                    project.setProjectDesc(rs.getString("startDate"));
                    project.setProjectDesc(rs.getString("endDate"));
                    return project;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public MembershipDTO getMembershipByPID(String pid,String uid ) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "select *from tblMembership \n"
                        + "where project_id=? and user_id=?";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, pid);
                ps.setString(2, uid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    MembershipDTO member = new MembershipDTO();
                    member.setprojectID(rs.getInt("project_id"));
                    member.setUserID("user_id");
                    member.setStartDate(rs.getDate("startDate"));
                    member.setEndDate(rs.getDate("endDate"));
                    return member;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    public boolean addMembers(int projectID, String userID, Date startDate, Date endDate) {

        boolean result = false;
        PreparedStatement pst = null;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();

            if (cn != null) {
                String preQueryStatement
                        = "SELECT * FROM tblMembership WHERE user_id = ? and project_id=?;";
                pst = cn.prepareStatement(preQueryStatement);
                pst.setString(1, userID);
                pst.setInt(2, projectID);
                ResultSet rs = pst.executeQuery();
                if (!rs.next()) {
                    String sql = "insert dbo.tblMembership(project_id,user_id,startDate,endDate) values(?,?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, projectID);
                    pst.setString(2, userID);
                    pst.setDate(3, startDate);
                    pst.setDate(4, endDate);
                    result = pst.executeUpdate() > 0;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }

    public MembershipDTO getMembershipDetailByPID(String pid) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "select *from tblMembership \n"
                        + "where project_id=?";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, pid);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    MembershipDTO m = new MembershipDTO();
                    m.setprojectID(rs.getInt("project_id"));
                    m.setUserID(rs.getString("user_id"));
                    m.setStartDate(rs.getDate("startDate"));
                    m.setEndDate(rs.getDate("endDate"));
                    return m;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    public ArrayList<MembershipDTO> getProjectUserList(String id) {
        ArrayList<MembershipDTO> list = new ArrayList<MembershipDTO>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select user_id,startDate,endDate from tblMembership where project_id=?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    MembershipDTO m = new MembershipDTO();

                    m.setUserID(rs.getString("user_id"));
                    m.setStartDate(rs.getDate("startDate"));
                    m.setEndDate(rs.getDate("endDate"));
                    list.add(m);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return list;
    }

    public boolean updateMembership(String pid, String uid, Date startDate, Date endDate) {
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                    String sql = "update tblMembership\n"
                            + "set startDate=?,\n"
                            + "endDate=?\n"
                            + "WHERE user_id = ? and project_id=?";
                    stm = cn.prepareStatement(sql);
                    stm.setDate(1, startDate);
                    stm.setDate(2, endDate);
                    stm.setString(3, uid);
                    stm.setString(4, pid);
                    int row = stm.executeUpdate();
                    if (row > 0) {
                        return true;
                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public ArrayList<ProjectDTO> getProjectsByMemberID(String userID) {
        ArrayList<ProjectDTO> projects = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(GET_PROJECT_BY_MEMBER_ID);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ProjectDTO project = new ProjectDTO();
                    project.setProjectID(rs.getInt("project_id"));
                    project.setProjectName(rs.getString("projectName"));
                    project.setManagerID(rs.getString("manager_id"));
                    project.setProjectDesc(rs.getString("projectDesc"));
                    project.setCreatedDate(rs.getDate("createdDate"));
                    projects.add(project);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        return projects;
    }

    public boolean deleteMembership(String id, Date endDate) {
        Connection cn = null;
        PreparedStatement stm = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "update tblMembership\n"
                        + "set endDate=?\n"
                        + "where user_id=?";
                stm = cn.prepareStatement(sql);
                stm.setDate(1, endDate);
                stm.setString(2, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

}
