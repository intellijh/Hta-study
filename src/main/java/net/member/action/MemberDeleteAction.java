package net.member.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.member.db.MemberDAO;

import java.io.IOException;
import java.io.PrintWriter;

public class MemberDeleteAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        MemberDAO mdao = new MemberDAO();
        int result = mdao.delete(id);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        if (result == 1) {
            out.println("<script>");
            out.println("   alert('삭제 성공입니다. ');");
            out.println("   location.href='memberList.net'");
            out.println("</script>");
        } else {
            out.println("<script>");
            out.println("   alert('회원 삭저 실패입니다.');");
            out.println("   history.back()");
            out.println("</script>");
        }
            out.close();
            return null;
    }
}
