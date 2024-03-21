package net.member.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public int getListCount() {
        String sql =
                "SELECT COUNT(id)\n" +
                "FROM member\n" +
                "WHERE id <> 'admin'";
        int count = 0;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getListCount() 에러 : " + e);
        }
        return count;
    }

    public int getListCount(String search_field, String search_word) {
        String sql =
                "SELECT COUNT(id)\n" +
                "FROM member\n" +
                "WHERE id <> 'admin'\n" +
                "AND " + search_field + " LIKE ?";
        int count = 0;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + search_word + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getListCount() 에러 : " + e);
        }
        return count;
    }

    public List<Member> getList(int page, int limit) {
        String sql =
                "SELECT *\n" +
                "FROM (SELECT b.*, ROWNUM AS rnum\n" +
                "      FROM (SELECT *\n" +
                "            FROM member\n" +
                "            WHERE id <> 'admin'\n" +
                "            ORDER BY id) b\n" +
                "      WHERE ROWNUM <= ?)\n" +
                "WHERE rnum >= ?\n" +
                "  AND rnum <= ?";
        List<Member> list = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int startrow = (page -1) * limit + 1;
            int endrow = startrow + limit - 1;
            pstmt.setInt(1, endrow);
            pstmt.setInt(2, startrow);
            pstmt.setInt(3, endrow);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Member m = new Member();
                    m.setId(rs.getString("id"));
                    m.setName(rs.getString("name"));
                    m.setAge(rs.getInt("age"));
                    m.setGender(rs.getString("gender"));
                    m.setEmail(rs.getString("email"));
                    list.add(m);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Member> getList(String search_field, String search_word, int page, int limit) {
        String sql =
                "SELECT *\n" +
                        "FROM (SELECT b.*, ROWNUM AS rnum\n" +
                        "      FROM (SELECT *\n" +
                        "            FROM member\n" +
                        "            WHERE id <> 'admin'\n" +
                        "            AND " + search_field + " LIKE ?\n" +
                        "            ORDER BY id) b\n" +
                        "      WHERE ROWNUM <= ?)\n" +
                        "WHERE rnum >= ?\n" +
                        "  AND rnum <= ?";
        List<Member> list = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + search_word + "%");

            int startrow = (page -1) * limit + 1;
            int endrow = startrow + limit - 1;
            pstmt.setInt(2, endrow);
            pstmt.setInt(3, startrow);
            pstmt.setInt(4, endrow);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Member m = new Member();
                    m.setId(rs.getString("id"));
                    m.setName(rs.getString("name"));
                    m.setAge(rs.getInt("age"));
                    m.setGender(rs.getString("gender"));
                    m.setEmail(rs.getString("email"));
                    list.add(m);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
