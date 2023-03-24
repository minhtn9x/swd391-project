package com.dma.dao;
import java.sql.SQLException;
import com.dma.dto.FeedbackDTO;
import com.dma.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;

public class FeedbackDAO {
    private static final String REPORT_FILE = "INSERT INTO Feedback(detail,subject,createdDate,userID) "
                                            + "VALUES(?,?,?,?)";
                                            
    public static boolean insertFeedback(String Userid, String Subject, String Detail, String Option) {
        boolean result = false;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "insert into dbo.tblFeedbacks(user_id,subject,details,[option],created_date)\n"
                        + "values(?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, Userid);
                pst.setString(2, Subject);
                pst.setString(3, Detail);
                pst.setString(4, Option);
                pst.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));
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

    public static ArrayList<FeedbackDTO> getFeedbackList() {
        ArrayList<FeedbackDTO> list = new ArrayList<FeedbackDTO>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select feedback_id,user_id,subject,details,[option],created_date from tblFeedbacks ";
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    FeedbackDTO feedback = new FeedbackDTO();
                    feedback.setFeedbackID(rs.getString("feedback_id"));
                    feedback.setUserID(rs.getString("user_id"));
                    feedback.setSubject(rs.getString("subject"));
                    feedback.setDetail(rs.getString("details"));
                    feedback.setOption(rs.getString("option"));
                    feedback.setCreatedDate(rs.getDate("created_date"));
                    list.add(feedback);
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

    public FeedbackDTO getFeedbackDetailByID(String id) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "select *from tblFeedbacks \n"
                        + "where feedback_id=?";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    FeedbackDTO feedback = new FeedbackDTO();
                    feedback.setFeedbackID(rs.getString("feedback_id"));
                    feedback.setUserID(rs.getString("user_id"));
                    feedback.setSubject(rs.getString("subject"));
                    feedback.setDetail(rs.getString("details"));
                    feedback.setOption(rs.getString("option"));
                    feedback.setCreatedDate(rs.getDate("created_date"));
                    return feedback;
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

    public boolean  deleteFeedback(String id) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String query = "delete from tblFeedbacks\n"
                        + "where feedback_id=?";
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, id);
                result=ps.executeUpdate()>0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean reportFile(String details, String subject, Date createdDate, String userID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(REPORT_FILE);
                stm.setString(1, details);
                stm.setString(2, subject);
                stm.setDate(3, createdDate);
                stm.setString(4, userID);

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
