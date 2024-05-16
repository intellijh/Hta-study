package net.template.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;

import java.io.IOException;

public class LoginAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = "";

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    id = cookie.getValue();
                }
            }
        }

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        request.setAttribute("cookie_id", id);
        forward.setPath("/template/login.jsp");
        return forward;
    }
}
