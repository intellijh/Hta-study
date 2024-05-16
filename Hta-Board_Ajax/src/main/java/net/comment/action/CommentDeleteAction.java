package net.comment.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.comment.db.CommentDAO;
import net.common.action.Action;
import net.common.action.ActionForward;

import java.io.IOException;
import java.io.PrintWriter;

public class CommentDeleteAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num = Integer.parseInt(request.getParameter("num"));

        CommentDAO dao = new CommentDAO();
        int result = dao.commentDelete(num);
        PrintWriter out = response.getWriter();
        out.print(result);

        return null;
    }
}
