package ex8.emp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

/*
* DAO(data Access Object) 클래스
* - 데이터베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스입니다.
*/
public class DAO {
    public ArrayList<Emp> selectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Emp> list = new ArrayList<>();

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String select_sql = "SELECT * FROM emp";

            //PreparedStatement 객체 얻기
            pstmt = conn.prepareStatement(select_sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                int sal = rs.getInt("sal");
                int comm = rs.getInt("comm");
                int deptno = rs.getInt("deptno");

                Emp emp = new Emp();
                emp.setEmpno(empno);
                emp.setEname(ename);
                emp.setJob(job);
                emp.setMgr(mgr);
                emp.setHiredate(hiredate);
                emp.setSal(sal);
                emp.setComm(comm);
                emp.setDeptno(deptno);

                list.add(emp);
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

    public ArrayList<Emp> select(String menu, String input) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Emp> list = new ArrayList<>();

        try {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
            conn = ds.getConnection();

            String select_sql =
                            "SELECT * " +
                            "FROM emp " +
                            "WHERE " + menu + " LIKE ?";

            if (menu.equals("hiredate")) {
                select_sql =
                        "SELECT *\n" +
                        "FROM emp\n" +
                        "WHERE TO_CHAR(HIREDATE,'yyyy-MM-dd') LIKE ?";
            }

            //PreparedStatement 객체 얻기
            pstmt = conn.prepareStatement(select_sql);

            System.out.println(input);
            pstmt.setString(1, "%" + input + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                int mgr = rs.getInt("mgr");
                Date hiredate = rs.getDate("hiredate");
                int sal = rs.getInt("sal");
                int comm = rs.getInt("comm");
                int deptno = rs.getInt("deptno");

                Emp emp = new Emp();
                emp.setEmpno(empno);
                emp.setEname(ename);
                emp.setJob(job);
                emp.setMgr(mgr);
                emp.setHiredate(hiredate);
                emp.setSal(sal);
                emp.setComm(comm);
                emp.setDeptno(deptno);

                list.add(emp);
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
