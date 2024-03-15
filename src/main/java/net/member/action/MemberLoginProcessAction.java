package net.member.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.member.db.MemberDAO;

import java.io.IOException;

public class MemberLoginProcessAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionForward forward = new ActionForward();
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        MemberDAO mdao = new MemberDAO();
        int result = mdao.isId(id, pass);
        System.out.println("결과는 " + result);

        if (result == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("id", id);

            String IDStore = request.getParameter("remember");
            Cookie cookie = new Cookie("id", id);

            if (IDStore != null && IDStore.equals("store")) {
                cookie.setMaxAge(3 * 60);
            }
        }
        return null;
    }
}
