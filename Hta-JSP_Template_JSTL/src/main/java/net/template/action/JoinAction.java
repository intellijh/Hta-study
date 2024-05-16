package net.template.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;

import java.io.IOException;

public class JoinAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/template/join.jsp");
        return forward;
    }
}
