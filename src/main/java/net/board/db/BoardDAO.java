package net.board.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private DataSource ds;

    //생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
    public BoardDAO() {
        try {
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
        } catch (Exception e) {
            System.out.println("DB 연결 실패 : " + e);
        }
    }

    //글의 갯수 구하기
    public int getListCount() {
        String sql = "SELECT COUNT(*) FROM board";
        int x = 0;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    x = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getListCount() 에러 : " + e);
        }
        return x;
    }

    //글 목록 보기
    public List<BoardBean> getBoardList(int page, int limit) {
        //page : 페이지
        //limit : 페이지 당 목록의 수
        //board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
        //조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.

        String sql =
                "SELECT *\n" +
                        "FROM (SELECT ROWNUM rnum, j.*\n" +
                        "      FROM (SELECT board.*, NVL(CNT, 0) cnt\n" +
                        "            FROM board, (SELECT COMMENT_BOARD_NUM, COUNT(num) AS CNT\n" +
                        "                         FROM comm\n" +
                        "                         GROUP BY COMMENT_BOARD_NUM)\n" +
                        "            WHERE board_num = comment_board_num(+)\n" +
                        "            ORDER BY board_re_ref DESC , board_re_ref) j\n" +
                        "      WHERE ROWNUM <= ?\n" +
                        "      )\n" +
                        "WHERE rnum >= ? AND rnum <= ?";

        List<BoardBean> list = new ArrayList<>();
        //한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ...
        int startrow = (page - 1) * limit + 1; //읽기 시작할 row 번호 (1   11  21  31 ...
        int endrow = startrow + limit - 1;     //읽을 마지막 row 번호(10   20  30  40 ...

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, endrow);
            pstmt.setInt(2, startrow);
            pstmt.setInt(3, endrow);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BoardBean board = new BoardBean();
                    board.setBoard_num(rs.getInt("board_num"));
                    board.setBoard_name(rs.getString("board_name"));
                    board.setBoard_subject(rs.getString("board_subject"));
                    board.setBoard_content(rs.getString("board_content"));
                    board.setBoard_file(rs.getString("board_file"));
                    board.setBoard_re_ref(rs.getInt("board_re_ref"));
                    board.setBoard_re_lev(rs.getInt("board_re_lev"));
                    board.setBoard_re_seq(rs.getInt("board_re_seq"));
                    board.setBoard_readcount(rs.getInt("board_readcount"));
                    board.setBoard_date(rs.getString("board_date"));
                    board.setCnt(rs.getInt("cnt"));
                    list.add(board);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getBoardList() 에러 : " + e);
        }
        return list;
    }

    public boolean boardInsert(BoardBean board) {
        int result = 0;
        String max_sql = "(SELECT NVL(MAX(board_num), 0) + 1 FROM board)";

        // 원문글의 BOARD_RE_REF 필드는 자신의 글번호 입니다.
        String sql = "INSERT INTO board "
                + "(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT,"
                + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,"
                + " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT)"
                + " VALUES (" + max_sql + ",?, ?, ?,"
                + "			 ?, ?," + max_sql + ","
                + "			 ?,?,?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            // 새로운 글을 등록하는 부분입니다.
            pstmt.setString(1, board.getBoard_name());
            pstmt.setString(2, board.getBoard_pass());
            pstmt.setString(3, board.getBoard_subject());
            pstmt.setString(4, board.getBoard_content());
            pstmt.setString(5, board.getBoard_file());

            // 원문의 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0입니다.
            pstmt.setInt(6, 0); // BOARD_RE_LEV 필드
            pstmt.setInt(7, 0); // BOARD_RE_SEQ 필드
            pstmt.setInt(8, 0); // BOARD_READCOUNT 필드

            result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("데이터 삽입이 모두 완료되었습니다.");
                return true;
            }
        } catch (Exception ex) {
            System.out.println("boardInsert() 에러: " + ex);
            ex.printStackTrace();
        }
        return false;
    }// boardInsert()메서드 end

    public BoardBean getDetail(int num) {
        BoardBean board = null;
        String sql = "SELECT * FROM board WHERE board_num = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, num);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    board = new BoardBean();
                    board.setBoard_num(rs.getInt("board_num"));
                    board.setBoard_name(rs.getString("board_name"));
                    board.setBoard_subject(rs.getString("board_subject"));
                    board.setBoard_content(rs.getString("board_content"));
                    board.setBoard_file(rs.getString("board_file"));
                    board.setBoard_re_ref(rs.getInt("board_re_ref"));
                    board.setBoard_re_lev(rs.getInt("board_re_lev"));
                    board.setBoard_re_seq(rs.getInt("board_re_seq"));
                    board.setBoard_readcount(rs.getInt("board_readcount"));
                    board.setBoard_date(rs.getString("board_date"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    public boolean isBoardWriter(int num, String pass) {
        boolean result = false;
        String sql =
                "SELECT board_pass\n" +
                "FROM board\n" +
                "WHERE board_num = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, num);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    if (pass.equals(rs.getString("board_pass"))) {
                        result = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("isBoardWriter() 에러 : " + e);
        }
        return result;
    }

    public boolean boardModify(BoardBean boarddata) {
        String sql =
                "UPDATE board\n" +
                "SET board_subject = ?, board_content = ?, board_file = ?\n" +
                "WHERE board_num = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, boarddata.getBoard_subject());
            pstmt.setString(2, boarddata.getBoard_content());
            pstmt.setString(3, boarddata.getBoard_file());
            pstmt.setInt(4, boarddata.getBoard_num());

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("성공 업데이트");
                return true;
            }
        } catch (Exception e) {
            System.out.println("boardModify() 에러 : " + e);
        }

        return false;
    }
}
