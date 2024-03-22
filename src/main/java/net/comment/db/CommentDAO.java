package net.comment.db;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
