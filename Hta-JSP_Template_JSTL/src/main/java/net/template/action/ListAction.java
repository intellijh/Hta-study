package net.template.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.template.db.DAO;
import net.template.db.Template_join;

import java.io.IOException;
import java.util.ArrayList;

public class ListAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        ArrayList<Template_join> list = dao.selectAll();

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/template/list.jsp");
        request.setAttribute("list", list);
        return forward;
    }
}
