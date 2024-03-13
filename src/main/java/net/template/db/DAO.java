package net.template.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO {
    public int isId(String id) {
        return isId(id, null);
    }

    public int isId(String id, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result = 0; //아이디가 존재하지 않는 경우

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String select_sql =
                            "SELECT id, password " +
                            "FROM template_join " +
                            "WHERE id = ?";

            pstmt = conn.prepareStatement(select_sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if (rs.getString("password").equals(password)) {
                    result = 1; //아이디와 비밀번호가 일치하는 경우
                } else {
                    result = -1; //아이디는 일치하고 비밀번호가 일치하지 않는 경우
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return result;
    }

    public int insert(Template_join join) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String insert_sql =
                            "INSERT INTO template_join (id, password, jumin, email, gender, hobby, post, address, intro)\n" +
                            "VALUES (?, ?, ?, ?, ?,\n" +
                            "        ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(insert_sql);
            pstmt.setString(1, join.getId());
            pstmt.setString(2, join.getPassword());
            pstmt.setString(3, join.getJumin());
            pstmt.setString(4, join.getEmail());
            pstmt.setString(5, join.getGender());
            pstmt.setString(6, join.getHobby());
            pstmt.setString(7, join.getPost());
            pstmt.setString(8, join.getAddress());
            pstmt.setString(9, join.getIntro());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return result;
    }

    public Template_join selectInfo(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String select_sql =
                    "SELECT * " +
                    "FROM template_join " +
                    "WHERE id = ?";

            pstmt = conn.prepareStatement(select_sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Template_join temp = new Template_join();
                temp.setId(rs.getString("id"));
                temp.setPassword(rs.getString("password"));
                temp.setJumin(rs.getString("jumin"));
                temp.setEmail(rs.getString("email"));
                temp.setGender(rs.getString("gender"));
                temp.setHobby(rs.getString("hobby"));
                temp.setPost(rs.getString("post"));
                temp.setAddress(rs.getString("address"));
                temp.setIntro(rs.getString("intro"));
                temp.setRegister_date(rs.getString("register_date").substring(0, 10));

                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return null;
    }

    public int update(Template_join join) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String update_sql =
                            "UPDATE template_join\n" +
                            "SET password = ?, jumin = ?, email = ?, gender = ?,\n" +
                            "    hobby = ?, post = ?, address = ?, intro = ?\n" +
                            "WHERE id = ?";

            pstmt = conn.prepareStatement(update_sql);
            pstmt.setString(1, join.getPassword());
            pstmt.setString(2, join.getJumin());
            pstmt.setString(3, join.getEmail());
            pstmt.setString(4, join.getGender());
            pstmt.setString(5, join.getHobby());
            pstmt.setString(6, join.getPost());
            pstmt.setString(7, join.getAddress());
            pstmt.setString(8, join.getIntro());
            pstmt.setString(9, join.getId());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return result;
    }

    public ArrayList<Template_join> selectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String select_sql =
                            "SELECT *\n" +
                            "FROM template_join\n" +
                            "WHERE id != 'admin'\n" +
                            "ORDER BY register_date DESC";

            pstmt = conn.prepareStatement(select_sql);
            rs = pstmt.executeQuery();

            ArrayList<Template_join> list = new ArrayList<>();

            while (rs.next()) {
                Template_join temp = new Template_join();
                temp.setId(rs.getString("id"));
                temp.setGender(rs.getString("gender"));
                temp.setHobby(rs.getString("hobby"));
                temp.setIntro(rs.getString("intro"));
                temp.setRegister_date(rs.getString("register_date").substring(0, 10));

                list.add(temp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return null;
    }

    public int delete(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String insert_sql =
                    "DELETE template_join\n" +
                    "WHERE id = ?";

            pstmt = conn.prepareStatement(insert_sql);
            pstmt.setString(1, id);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return result;
    }
}
