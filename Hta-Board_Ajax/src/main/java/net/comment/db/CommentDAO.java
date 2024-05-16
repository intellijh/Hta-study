package net.comment.db;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDAO {
    private DataSource ds;

    public CommentDAO() {
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            System.out.println("DB 연결 실패 : " + e);
        }
    }

    public int commentInsert(Comment c) {
        int result = 0;
        String sql =
                "INSERT INTO comm\n" +
                "VALUES(com_seq.nextval, ?, ?, SYSDATE, ?, ?, ?, com_seq.nextval)";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, c.getId());
            pstmt.setString(2, c.getContent());
            pstmt.setInt(3, c.getComment_board_num());
            pstmt.setInt(4, c.getComment_re_lev());
            pstmt.setInt(5, c.getComment_re_seq());
            result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("데이터 삽인 완료");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getListCount(int comment_board_num) {
        int count = 0;
        String sql =
                "SELECT COUNT(*)\n" +
                "FROM comm\n" +
                "WHERE comment_board_num = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, comment_board_num);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getListCount() 에러 : " + e);
        }
        return count;
    }

    public JsonArray getCommentList(int comment_board_num, int state) {
        String sort = "ASC";
        if (state == 2) {
            sort = "DESC";
        }

        String sql =
                "SELECT c.*, memberfile\n" +
                "FROM comm c, member m\n" +
                "WHERE c.id = m.id\n" +
                "AND comment_board_num = ?\n" +
                "ORDER BY comment_re_ref " + sort + ", comment_re_seq";
        JsonArray array = new JsonArray();
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, comment_board_num);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    JsonObject object = new JsonObject();
                    object.addProperty("num", rs.getInt(1));
                    object.addProperty("id", rs.getString(2));
                    object.addProperty("content", rs.getString(3));
                    object.addProperty("reg_date", rs.getString(4));
//                    object.addProperty("comment_board_num", rs.getInt(5));
                    object.addProperty("comment_re_lev", rs.getInt(6));
                    object.addProperty("comment_re_seq", rs.getInt(7));
                    object.addProperty("comment_re_ref", rs.getInt(8));
                    object.addProperty("memberfile", rs.getString(9));
                    array.add(object);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getCommentList() 에러 : " + e);
        }
        return array;
    }

    public int commentUpdate(Comment c) {

        int result = 0;
        String sql =
                "UPDATE comm\n" +
                "SET content = ?\n" +
                "WHERE num = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, c.getContent());
            pstmt.setInt(2, c.getNum());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("commentUpdate 에러 : " + e);
        }
        return result;
    }

    public int commentReply(Comment c) {

        int result = 0;

        try (Connection conn = ds.getConnection()) {
            conn.setAutoCommit(false);

            try {
                reply_update(conn, c.getComment_re_ref(), c.getComment_re_seq());
                result = reply_insert(conn, c);
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException exception) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private void reply_update(Connection conn, int re_ref, int re_seq) throws SQLException {

        String sql =
                "UPDATE comm\n" +
                "SET comment_re_seq = comment_re_seq + 1\n" +
                "WHERE comment_re_ref = ?\n" +
                "AND comment_re_seq > ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, re_ref);
            pstmt.setInt(2, re_seq);
            pstmt.executeUpdate();
        }
    }

    private int reply_insert(Connection conn, Comment c) throws SQLException{
        int result = 0;

        String sql =
                "INSERT INTO comm\n" +
                "VALUES(com_seq.nextval, ?, ?, SYSDATE, ?, ? ,? ,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getId());
            pstmt.setString(2, c.getContent());
            pstmt.setInt(3, c.getComment_board_num());
            pstmt.setInt(4, c.getComment_re_lev() + 1);
            pstmt.setInt(5, c.getComment_re_seq() + 1);
            pstmt.setInt(6, c.getComment_re_ref());
            result = pstmt.executeUpdate();
        }
        return result;
    }

    public int commentDelete(int num) {

        int result = 0;
        String sql =
                "DELETE comm\n" +
                "WHERE num = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, num);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("commentDelete() 에러 : " + e);
        }
        return result;
    }
}
