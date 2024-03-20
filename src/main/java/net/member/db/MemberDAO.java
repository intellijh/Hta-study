package net.member.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {
    private DataSource ds;

    public MemberDAO() {
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            System.out.println("DB 연결 실패 : " + e);
        }
    }

    public int isId(String id) {
        int result = -1; //아이디 미존재
        String sql =
                "SELECT id\n" +
                "FROM member\n" +
                "WHERE id = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    result = 0; //아이디 존재
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int isId(String id, String pass) {
        int result = -1; //아이디 미존재
        String sql =
                "SELECT id, password\n" +
                "FROM member\n" +
                "WHERE id = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    if (rs.getString(2).equals(pass)) {
                        result = 1;
                    } else {
                        result = 0; //아이디 존재
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int insert(Member m) {
        int result = 0;
        String sql =
                "INSERT INTO member\n" +
                "VALUES(?, ?, ?, ?, ?, ?, NULL)";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, m.getId());
            pstmt.setString(2, m.getPassword());
            pstmt.setString(3, m.getName());
            pstmt.setInt(4, m.getAge());
            pstmt.setString(5, m.getGender());
            pstmt.setString(6, m.getEmail());

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Member member_info(String id) {
        Member m = null;
        String sql =
                "SELECT *\n" +
                "FROM member\n" +
                "WHERE id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    m = new Member();
                    m.setId(rs.getString(1));
                    m.setPassword(rs.getString(2));
                    m.setName(rs.getString(3));
                    m.setAge(rs.getInt(4));
                    m.setGender(rs.getString(5));
                    m.setEmail(rs.getString(6));
                    m.setMemberfile(rs.getString(7));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    public boolean updateMember(Member m) {
        boolean isUpdateSuccess = false;
        String sql =
                "UPDATE member\n" +
                "SET name = ?, age = ?, gender = ?,email = ?, memberfile = ?\n" +
                "WHERE id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getName());
            pstmt.setInt(2, m.getAge());
            pstmt.setString(3, m.getGender());
            pstmt.setString(4, m.getEmail());
            pstmt.setString(5, m.getMemberfile());
            pstmt.setString(6, m.getId());

            int result = pstmt.executeUpdate();

            if (result >= 1) {
                isUpdateSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdateSuccess;
    }
}
