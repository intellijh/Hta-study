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
                        "            ORDER BY board_re_ref DESC , board_re_seq) j\n" +
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

    public int boardReply(BoardBean boarddata) {
        int num = 0;

        try (Connection conn = ds.getConnection()) {
            //트랜잭션을 이용하기 위해서 setAutoCommit을 false로 설정합니다.
            conn.setAutoCommit(false);

            try {
                reply_update(conn, boarddata.getBoard_re_ref(), boarddata.getBoard_re_seq());

                num = reply_insert(conn, boarddata);
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();

                if (conn != null) {
                    try {
                        conn.rollback();
                    } catch (SQLException exception) {
                        e.printStackTrace();
                    }
                }
            }
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("boardReply() 에러 : " + e);
        }
        return num;
    }

    private void reply_update(Connection conn, int re_ref, int re_seq) throws SQLException {
        //board_re_ref, board_re_seq 값을 확인하여 원문 글에 답글이 달려있다면
        //달린 답글들의 board_se_seq 값을 1씩 증가시킵니다.
        //현재 글을 이미 달린 답글보다 앞에 출력되게 하기 위해서 입니다.

        String sql = "UPDATE board\n" +
                "SET board_re_seq = board_re_seq + 1\n" +
                "WHERE board_re_ref = ?\n" +
                "AND board_re_seq > ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, re_ref);
            pstmt.setInt(2, re_seq);
            pstmt.executeUpdate();
        }
    }

    private int reply_insert(Connection conn, BoardBean boarddata) throws SQLException {
        int num = 0;

        String board_max_sql = "(SELECT MAX(board_num) + 1 FROM board)";
        try (PreparedStatement pstmt = conn.prepareStatement(board_max_sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    num = rs.getInt(1);
                }
            }
        }

        String sql = "INSERT INTO board\n" +
                "(board_num, board_name, board_pass, board_subject,\n" +
                "board_content, board_file, board_re_ref, board_re_lev,\n" +
                "board_re_seq, board_readcount)\n" +
                "VALUES(?, ?, ?, ?,\n" +
                "?, ?, ?, ?,\n" +
                "?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, num);
            pstmt.setString(2, boarddata.getBoard_name());
            pstmt.setString(3, boarddata.getBoard_pass());
            pstmt.setString(4, boarddata.getBoard_subject());
            pstmt.setString(5, boarddata.getBoard_content());
            pstmt.setString(6, "");
            pstmt.setInt(7, boarddata.getBoard_re_ref());
            pstmt.setInt(8, boarddata.getBoard_re_lev() + 1);
            pstmt.setInt(9, boarddata.getBoard_re_seq() + 1);
            pstmt.setInt(10, 0);
            pstmt.executeUpdate();
        }

        return num;
    }

    public boolean boardDelete(int num) {
        String select_sql =
                "SELECT board_re_ref, board_re_lev, board_re_seq\n" +
                "FROM board\n" +
                "WHERE board_num = ?";

        String board_delete_sql =
                "DELETE\n" +
                "FROM board\n" +
                "WHERE board_re_ref = ?\n" +
                "  AND board_re_lev >= ?\n" +
                "  AND board_re_seq >= ?\n" +
                "  AND board_re_seq <= (\n" +
                "    NVL((SELECT MIN(board_re_seq) - 1\n" +
                "         FROM board\n" +
                "         WHERE board_re_ref = ?\n" +
                "           AND board_re_lev = ?\n" +
                "           AND board_re_seq > ?),\n" +
                "        (SELECT MAX(board_re_seq)\n" +
                "         FROM board\n" +
                "         WHERE board_re_ref = ?))\n" +
                "    )";

        boolean result_check = false;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(select_sql)) {

            pstmt.setInt(1, num);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    try (PreparedStatement pstmt2 = conn.prepareStatement(board_delete_sql)) {
                        pstmt2.setInt(1, rs.getInt("board_re_ref"));
                        pstmt2.setInt(2, rs.getInt("board_re_lev"));
                        pstmt2.setInt(3, rs.getInt("board_re_seq"));
                        pstmt2.setInt(4, rs.getInt("board_re_ref"));
                        pstmt2.setInt(5, rs.getInt("board_re_lev"));
                        pstmt2.setInt(6, rs.getInt("board_re_seq"));
                        pstmt2.setInt(7, rs.getInt("board_re_ref"));
                        System.out.println(num);
                        System.out.println(rs.getInt("board_re_ref"));
                        System.out.println(rs.getInt("board_re_lev"));
                        System.out.println(rs.getInt("board_re_seq"));
                        int count = pstmt2.executeUpdate();
                        System.out.println(count);
                        if (count >= 1) {
                            result_check = true; //삭제가 안된 경우에는 false를 반환합니다.
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("boardDelete() 에러 : " + e);
            e.printStackTrace();
        }
        return result_check;
    }

    public void setReadCountUpdate(int num) {
        String sql =
                "UPDATE board\n" +
                "SET board_readcount = board_readcount + 1\n" +
                "WHERE board_num = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, num);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("조회수 증가 오류");
        }
    }
}
