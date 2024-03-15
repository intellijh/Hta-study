package net.member.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.member.db.MemberDAO;

import java.io.IOException;

public class MemberIdCehckAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDAO dao = new MemberDAO();
        int result = dao.isId(request.getParameter("id"));
        response.getWriter().print(result);
        System.out.println(result);
        return null;
    }
}
