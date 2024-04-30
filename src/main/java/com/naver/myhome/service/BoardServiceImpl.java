package com.naver.myhome.service;

import com.naver.myhome.domain.Board;
import com.naver.myhome.mybatis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper dao;

    @Autowired
    public BoardServiceImpl(BoardMapper dao) {
        this.dao = dao;
    }

    @Override
    public int getListCount() {
        return dao.getListCount();
    }

    @Override
    public List<Board> getBoardList(int page, int limit) {

        HashMap<String, Integer> map = new HashMap<>();
        int startRow = (page - 1) * limit + 1;
        int endRow = startRow + limit - 1;
        map.put("start", startRow);
        map.put("end", endRow);
        return dao.getBoardList(map);
    }

    @Override
    public int setReadCountUpdate(int num) {
        return dao.setReadCountUpdate(num);
    }

    @Override
    public Board getDetail(int num) {
        return dao.getDetail(num);
    }

    @Override
    public void insertBoard(Board board) {
        dao.insertBoard(board);
    }

    @Override
    public int boardDelete(int num) {
        int result = 0;
        Board board = dao.getDetail(num);
        if (board != null) {
            result = dao.boardDelete(board);
        }
        return result;
    }

    @Override
    public int boardModify(Board board) {
        return dao.boardModify(board);
    }

    @Override
    public boolean isBoardWriter(int num, String pass) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("num", num);
        map.put("pass", pass);

        Board result = dao.isBoardWriter(map);
        return result != null;
    }

    @Override
    @Transactional
    public int boardReply(Board board) {
        boardReplyUpdate(board);
        board.setBoard_re_lev(board.getBoard_re_lev() + 1);
        board.setBoard_re_seq(board.getBoard_re_seq() + 1);
        return dao.boardReply(board);
    }

    @Override
    public int boardReplyUpdate(Board board) {
        return dao.boardReplyUpdate(board);
    }

    @Override
    public List<String> getDeleteFileList() {
        return dao.getDeleteFileList();
    }

    @Override
    public void deleteFileList(String filename) {
        dao.deleteFileList(filename);
    }
}
