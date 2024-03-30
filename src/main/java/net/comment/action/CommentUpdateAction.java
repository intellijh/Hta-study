package net.comment.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.comment.db.Comment;
import net.comment.db.CommentDAO;
import net.common.action.Action;
import net.common.action.ActionForward;

import java.io.IOException;

public class CommentUpdateAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentDAO dao = new CommentDAO();
        Comment c = new Comment();
        c.setContent(request.getParameter("content"));
        System.out.println("content = " + c.getContent());

        c.setNum(Integer.parseInt(request.getParameter("num")));

        int ok = dao.commentUpdate(c);
        response.getWriter().print(ok);

        return null;
    }
}
