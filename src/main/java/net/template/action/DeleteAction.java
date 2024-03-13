package net.template.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.template.db.DAO;

import java.io.IOException;

public class DeleteAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        int result = dao.delete();

        ActionForward forward = new ActionForward();
        forward.setRedirect(true);
        forward.setPath("/template/list.net");
        return forward;
    }
}
