package ex8;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
* DAO(data Access Object) 클래스
* - 데이터베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스입니다.
*/
public class DAO {
    public ArrayList<Dept> selectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Dept> list = new ArrayList<>();

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String select_sql = "SELECT * FROM dept";

            //PreparedStatement 객체 얻기
            pstmt = conn.prepareStatement(select_sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                list.add(dept);
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

        return list;
    }

    public ArrayList<Dept> select(int inputDeptno) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Dept> list = new ArrayList<>();

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String select_sql =
                    "SELECT * " +
                    "FROM dept " +
                    "WHERE DEPTNO = ?";

            //PreparedStatement 객체 얻기
            pstmt = conn.prepareStatement(select_sql);
            pstmt.setInt(1, inputDeptno);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                list.add(dept);
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

        return list;
    }
}
