package ex1._7.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ex1/_7.session/SessionServlet")
public class SessionServlet extends HttpServlet {
    
    //Redirect 방식 : 포워딩될 때 브라우저의 주소 표시줄의 URL이 변경되므로 요청이 바뀌게 됩니다.
    //이동된 jsp 페이지에서는 서블릿에서 request 영역의 공유한 속성 값에 접근할 수 없습니다.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getSession() : 세션 객체를 가져옵니다.
        HttpSession session = request.getSession();

        String value = request.getParameter("food");
        request.setAttribute("food", value); //request 객체에 저장합니다.
        session.setAttribute("food", value); //session 객체에 저장합니다.

        response.sendRedirect("redirect.jsp");
    }
}
