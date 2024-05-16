package net.member.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.common.action.Action;
import net.common.action.ActionForward;

import java.io.IOException;

public class MemberLogoutAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        ActionForward forward = new ActionForward();
        forward.setPath("login.net");
        forward.setRedirect(true);
        return forward;
    }
}
