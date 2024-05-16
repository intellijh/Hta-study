package ex1._9.ServletContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ex1/_9.context/context")
public class ContextServlet2 extends HttpServlet {
    
    //Redirect 방식 : 포워딩될 때 브라우저의 주소 표시줄의 URL이 변경되므로 요청이 바뀌게 됩니다.
    //이동된 jsp 페이지에서는 서블릿에서 request 영역의 공유한 속성 값에 접근할 수 없습니다.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("food");
        request.setAttribute("food", value); //request 객체에 저장합니다.

        HttpSession session = request.getSession();
        session.setAttribute("food", value); //session 객체에 저장합니다.

        /*
        * 웹 애플리케이션이 서비스되고 있는 동안 유지하는 것을 의미합니다.
        * 생명주기가 웹 애플리케이션과 같은 객체는 ServletContext 입니다.
        * ServletContext는 웹 애플리케이션 서비스가 시작될 때 생성되고 종료될 때 소멸합니다.
        */
        ServletContext sc = request.getSession().getServletContext();
        sc.setAttribute("food", value);

        response.sendRedirect("scope.jsp");
    }
}
