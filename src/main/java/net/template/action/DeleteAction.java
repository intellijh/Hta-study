package net.template.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.common.action.Action;
import net.common.action.ActionForward;
import net.template.db.DAO;

import java.io.IOException;

public class DeleteAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = new DAO();
        String id = request.getParameter("id");
        int result = dao.delete(id);

        String message = "회원 삭제 성공입니다.";

        if (result != 1) {
            message = "회원 삭제 실패입니다.";
        }
        request.setAttribute("message", message);

        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/template/delete.jsp");
        return forward;
    }
}
