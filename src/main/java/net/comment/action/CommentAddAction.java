package net.comment.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.comment.db.Comment;
import net.comment.db.CommentDAO;
import net.common.action.Action;
import net.common.action.ActionForward;

import java.io.IOException;

public class CommentAddAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentDAO dao = new CommentDAO();

        Comment c = new Comment();
        c.setId(request.getParameter("id"));
        c.setContent(request.getParameter("content"));
        System.out.println("content = " + c.getContent());

        c.setComment_re_lev(Integer.parseInt(request.getParameter("comment_re_lev")));
        c.setComment_board_num(Integer.parseInt(request.getParameter("comment_board_num")));
        c.setComment_re_seq(Integer.parseInt(request.getParameter("comment_re_seq")));

        int ok = dao.commentInsert(c);
        response.getWriter().print(ok);
        return null;
    }
}
